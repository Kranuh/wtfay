package timkranen.com.wtfay.services.bing.image_search;

import android.content.Context;
import android.util.Log;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timkranen.com.wtfay.cache.CacheController;
import timkranen.com.wtfay.cache.quickcache.ImageQuickCache;
import timkranen.com.wtfay.domain.model.services.bing.Image;
import timkranen.com.wtfay.domain.model.services.bing.ImageResult;
import timkranen.com.wtfay.services.RestAdapter;

/**
 * Created by tim on 7/10/16.
 */
public class ImageSearchController {

    public static final String LATEST_RESULT = "cachedLatestResult";

    public static synchronized void fetchImages(final OnImageFetchedListener fetchListener, final String query) {
        ImageSearchService imageSearchService = RestAdapter.getDebugAdapter(ImageSearchService.class, ImageSearchService.BASE_URL);
        imageSearchService.search(ImageSearchService.API_KEY, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        fetchListener.onImageFetchFailed(e);
                    }

                    @Override
                    public void onNext(ImageResult imageResult) {
                        if(imageResult != null && imageResult.getImages() != null && !imageResult.getImages().isEmpty()) {
                            fetchListener.onImagesFetched(imageResult.getImages(), query);
                            CacheController.cacheImageResult(LATEST_RESULT, imageResult, CacheController.CacheType.QUICK_CACHE);
                        } else {
                            fetchListener.onNoResult();
                    }
                    }
                });
    }

}
