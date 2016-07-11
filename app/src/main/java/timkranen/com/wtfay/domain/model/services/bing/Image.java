package timkranen.com.wtfay.domain.model.services.bing;

import com.google.gson.annotations.SerializedName;

import timkranen.com.wtfay.domain.model.base.ResultType;
import timkranen.com.wtfay.domain.model.base.ServiceResult;

/**
 * Created by tim on 7/6/16.
 */
public class Image implements ServiceResult {
    @SerializedName("contentUrl")
    private String imageUrl;
    private String thumbnailUrl;

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int getViewCompat() {
        return 3;
    }

    @Override
    public ResultType getResultType() {
        return ResultType.BING_IMAGE;
    }
}
