package timkranen.com.wtfay.cache;

import timkranen.com.wtfay.cache.quickcache.ImageQuickCache;
import timkranen.com.wtfay.domain.model.services.bing.ImageResult;

/**
 * Created by tim on 7/10/16.
 */
public class CacheController {

    public enum CacheType {
        QUICK_CACHE;
    }

    public static void cacheImageResult(String key, ImageResult result, CacheType cacheType) {
        switch(cacheType) {
            case QUICK_CACHE:
                cacheImageResultQuickCache(key, result);
                break;
        }
    }

    private static void cacheImageResultQuickCache(String key, ImageResult result) {
        ImageQuickCache.getInstance().put(key, result);
    }
}
