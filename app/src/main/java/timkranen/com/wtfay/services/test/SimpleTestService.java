package timkranen.com.wtfay.services.test;


import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import timkranen.com.wtfay.services.test.model.Post;

/**
 * Created by tim on 6/29/16.
 */
public interface SimpleTestService {
    String endpoint = "http://jsonplaceholder.typicode.com";

    @GET("/posts")
    Observable<List<Post>> getPosts();
}
