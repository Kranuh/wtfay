package timkranen.com.wtfay.cache.quickcache;

import android.support.annotation.Nullable;

import timkranen.com.wtfay.domain.model.base.ServiceResult;

/**
 * Created by tim on 7/10/16.
 */
public interface StaticQuickCache<T extends ServiceResult> {
    void put(String key, T dataObject);
    @Nullable T get(String key);
}
