package timkranen.com.wtfay.services.bing.image_search;

import java.util.List;

import timkranen.com.wtfay.domain.model.services.bing.Image;

/**
 * Created by tim on 7/10/16.
 */
public interface OnImageFetchedListener {
    void onImagesFetched(List<Image> result, String query);
    void onNoResult();
    void onImageFetchFailed(Throwable e);
}
