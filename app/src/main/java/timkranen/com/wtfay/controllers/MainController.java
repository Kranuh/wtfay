package timkranen.com.wtfay.controllers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timkranen.com.wtfay.R;
import timkranen.com.wtfay.domain.model.services.bing.Image;
import timkranen.com.wtfay.recyclerview.adapters.BaseResultAdapter;
import timkranen.com.wtfay.services.bing.image_search.ImageSearchController;
import timkranen.com.wtfay.services.bing.image_search.OnImageFetchedListener;
import timkranen.com.wtfay.views.main_page.SearchLocationView;

/**
 * Created by tim on 6/29/16.
 */
public class MainController extends Controller {

    private Unbinder unbinder;

    protected
    @BindView(R.id.result_recycler_view)
    RecyclerView resultRecyclerView;
    protected
    @BindView(R.id.search_location_view)
    SearchLocationView searchLocationView;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View contentView = inflater.inflate(R.layout.main_controller, container, false);
        unbinder = ButterKnife.bind(this, contentView);
        onViewBound(contentView);
        return contentView;
    }

    private void onViewBound(View view) {
        setupResultView();
        startImageQuery("Los Angeles");

        resultRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                searchLocationView.scrollBottom(dy);
            }
        });
        searchLocationView.setLatchView(resultRecyclerView);
    }

    private void setupResultView() {
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        resultRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void startImageQuery(String query) {
        ImageSearchController.fetchImages(new OnImageFetchedListener() {
            @Override
            public void onImagesFetched(List<Image> result, String query) {
                onBingImagesFetched(result);
            }

            @Override
            public void onNoResult() {
                //todo do onResult stuff for images
            }

            @Override
            public void onImageFetchFailed(Throwable e) {
                //todo if no cache, image fetch failed, if cache display cache
            }
        }, query);
    }

    private void onBingImagesFetched(List<Image> images) {
        this.resultRecyclerView.setAdapter(new BaseResultAdapter(getApplicationContext(), images));
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);

        unbinder.unbind();
    }
}
