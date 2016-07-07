package timkranen.com.wtfay.services.bing.image_search;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;
import timkranen.com.wtfay.domain.model.services.bing.ImageResult;

/**
 * Created by tim on 7/6/16.
 *
 * Service class that handles the REST API for Bing
 * ImageResult Search
 */
public interface ImageSearchService {
    String BASE_URL = "https://api.cognitive.microsoft.com/bing/v5.0/images/";
    String API_KEY = "6f4037bcb27f4cb78498b0d9c9ebace4"; //todo: move this to a secure location!

    @GET("search")
    Observable<ImageResult> search(@Header("Ocp-Apim-Subscription-Key") String apiKey, @Query("q") String query);
}
