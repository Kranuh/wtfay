package timkranen.com.wtfay.domain.model.services.bing;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import timkranen.com.wtfay.domain.model.base.ResultType;
import timkranen.com.wtfay.domain.model.base.ServiceResult;

/**
 * Created by tim on 7/6/16.
 */

public class ImageResult
        implements ServiceResult {
    @SerializedName("value")
    private List<Image> images;

    public ImageResult(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public int getViewCompat() {
        return -1;
    }

    @Override
    public ResultType getResultType() {
        return ResultType.NO_VIEW;
    }
}
