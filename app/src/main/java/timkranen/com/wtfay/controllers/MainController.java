package timkranen.com.wtfay.controllers;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timkranen.com.wtfay.R;
import timkranen.com.wtfay.services.test.SimpleTestService;
import timkranen.com.wtfay.services.test.TestAdapter;
import timkranen.com.wtfay.services.test.model.Post;

/**
 * Created by tim on 6/29/16.
 */
public class MainController extends Controller {

    @BindView(R.id.test_text)
    TextView testText;

    private Unbinder unbinder;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View contentView = inflater.inflate(R.layout.test_controller, container, false);
        unbinder = ButterKnife.bind(this, contentView);
        onViewBound(contentView);
        return contentView;
    }

    private void onViewBound(View view) {
        SimpleTestService testService = TestAdapter.createService();
        testService.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<List<Post>, Observable<Post>>() {
                    @Override
                    public Observable<Post> call(List<Post> posts) {
                        return Observable.from(posts);
                    }
                })
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Post post) {
                        String text = testText.getText().toString();
                        text += post.getBody();
                        testText.setText(text);
                    }
                });
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);

        unbinder.unbind();
    }
}
