package timkranen.com.wtfay.recyclerview.viewholders;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import timkranen.com.wtfay.domain.model.base.ResultType;
import timkranen.com.wtfay.domain.model.base.ServiceResult;
import timkranen.com.wtfay.recyclerview.viewholders.bing.ViewType;

/**
 * Created by tim on 7/10/16.
 */
public class ViewHolderGenerator {
    private static final int BING_VIEWHOLDER_COUNT = 3;

    private static ViewHolderGenerator instance;

    public static ViewHolderGenerator getInstance() {
        if (instance == null) {
            instance = new ViewHolderGenerator();
        }

        return instance;
    }

    private final Random random = new Random();

    private final ViewType[] bingViewTypes = new ViewType[]{ViewType.BING_IMAGE_SMALL, ViewType.BING_IMAGE_MEDIUM, ViewType.BING_IMAGE_LARGE};
    private final List<Integer> createdViewHolderTypes = new ArrayList<>();

    public int createItemViewType(int position, ServiceResult serviceResult) {
        int randomType = getRandomViewHolderIndex(serviceResult.getViewCompat(), getViewHolderCount(serviceResult.getResultType()));
        createdViewHolderTypes.add(position, randomType);
        return randomType;
    }

    public boolean hasViewType(int position) {
        return createdViewHolderTypes.size() > position;
    }

    public ViewType getItemViewType(int position) {
        //todo make this compatible with other types
        Log.d("itemviewtype", "Getting itemViewType: " + bingViewTypes[createdViewHolderTypes.get(position)].toString());
        return bingViewTypes[createdViewHolderTypes.get(position)];
    }

    private int getRandomViewHolderIndex(int compat, int max) {
        int rand = random.nextInt(max);
        if (rand > compat) {
            return getRandomViewHolderIndex(compat, max - 1);
        } else {
            return rand;
        }
    }

    private int getViewHolderCount(ResultType resultType) {
        switch (resultType) {
            case BING_IMAGE:
                return BING_VIEWHOLDER_COUNT;
            default:
                return 0;
        }
    }

}
