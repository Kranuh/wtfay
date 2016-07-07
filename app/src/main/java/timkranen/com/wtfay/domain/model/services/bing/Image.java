package timkranen.com.wtfay.domain.model.services.bing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tim on 7/6/16.
 */
public class Image {
    @SerializedName("contentUrl")
    private String imageUrl;

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
