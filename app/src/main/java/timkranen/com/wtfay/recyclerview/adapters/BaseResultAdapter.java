package timkranen.com.wtfay.recyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.List;
import java.util.Random;

import timkranen.com.wtfay.R;
import timkranen.com.wtfay.domain.model.base.ServiceResult;
import timkranen.com.wtfay.recyclerview.viewholders.ServiceResultViewHolder;
import timkranen.com.wtfay.recyclerview.viewholders.ViewHolderGenerator;
import timkranen.com.wtfay.recyclerview.viewholders.bing.LargeBingImageViewHolder;
import timkranen.com.wtfay.recyclerview.viewholders.bing.MediumBingImageViewHolder;
import timkranen.com.wtfay.recyclerview.viewholders.bing.SmallBingImageViewHolder;
import timkranen.com.wtfay.recyclerview.viewholders.bing.ViewType;

/**
 * Created by tim on 7/10/16.
 */
public class BaseResultAdapter extends RecyclerView.Adapter<ServiceResultViewHolder> {

    private List<? extends ServiceResult> resultList;
    private Context context;
    private final Random random = new Random();

    public BaseResultAdapter(Context context, List<? extends ServiceResult> resultList) {
        this.resultList = resultList;
        this.context = context;
    }

    @Override
    public ServiceResultViewHolder onCreateViewHolder(ViewGroup parent, int viewTypePosition) {
        ViewType viewType = ViewType.values()[viewTypePosition];
        switch (viewType) {
            case BING_IMAGE_SMALL:
                View smallBingItemView = LayoutInflater.from(context).inflate(R.layout.bing_image_small, parent, false);
                return new SmallBingImageViewHolder(smallBingItemView);
            case BING_IMAGE_MEDIUM:
                View mediumBingItemView = LayoutInflater.from(context).inflate(R.layout.bing_image_medium, parent, false);
                return new MediumBingImageViewHolder(mediumBingItemView);
            case BING_IMAGE_LARGE:
                View largeBingItemView = LayoutInflater.from(context).inflate(R.layout.bing_image_large, parent, false);
                return new LargeBingImageViewHolder(largeBingItemView);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (ViewHolderGenerator.getInstance().hasViewType(position)) {
            return ViewHolderGenerator.getInstance().getItemViewType(position).ordinal();
        } else {
            return ViewHolderGenerator.getInstance().createItemViewType(position, resultList.get(position));
        }
    }

    @Override
    public void onBindViewHolder(ServiceResultViewHolder holder, int position) {
        ViewType viewType = ViewHolderGenerator.getInstance().getItemViewType(position);
        switch (viewType) {
            case BING_IMAGE_SMALL:
                onBindBingImageSmall((SmallBingImageViewHolder) holder, position);
                break;
            case BING_IMAGE_MEDIUM:
                onBindBingImageMedium((MediumBingImageViewHolder) holder, position, getRandomPosition());
                break;
            case BING_IMAGE_LARGE:
                onBindBingImageLarge((LargeBingImageViewHolder) holder, position);
                break;
        }
    }

    private int getRandomPosition() {
        int max = resultList.size();
        return random.nextInt(max);
    }

    private void onBindBingImageSmall(SmallBingImageViewHolder holder, int position) {
        holder.update(context, resultList.get(position));
    }

    private void onBindBingImageMedium(MediumBingImageViewHolder holder, int firstPosition, int secondPosition) {
        holder.update(context, resultList.get(firstPosition), resultList.get(secondPosition));
    }

    private void onBindBingImageLarge(LargeBingImageViewHolder holder, int position) {
        holder.update(context, resultList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
