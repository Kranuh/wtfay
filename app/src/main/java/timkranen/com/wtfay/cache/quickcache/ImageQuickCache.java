package timkranen.com.wtfay.cache.quickcache;

import java.util.HashMap;
import java.util.List;

import timkranen.com.wtfay.domain.model.services.bing.Image;
import timkranen.com.wtfay.domain.model.services.bing.ImageResult;

/**
 * Created by tim on 7/10/16.
 */
public class ImageQuickCache extends BaseQuickCache<ImageResult> {

    private static ImageQuickCache instance;

    public static ImageQuickCache getInstance() {
        if(instance == null) {
            instance = new ImageQuickCache();
        }

        return instance;
    }

}
