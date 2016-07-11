package timkranen.com.wtfay.cache.quickcache;

import java.util.HashMap;
import java.util.List;

import timkranen.com.wtfay.domain.model.base.ServiceResult;

/**
 * Created by tim on 7/10/16.
 */
public class BaseQuickCache<T extends ServiceResult> implements StaticQuickCache<T> {
    protected final HashMap<String, T> cacheMap = new HashMap<>();

    private static BaseQuickCache cache;

    @Override
    public void put(String key, T dataObject) {
        cacheMap.put(key, dataObject);
    }

    @Override
    public T get(String key) {
        if(cacheMap.containsKey(key)) {
            return cacheMap.get(key);
        } else {
            return null;
        }
    }
}
