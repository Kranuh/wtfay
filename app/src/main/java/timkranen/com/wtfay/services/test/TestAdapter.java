package timkranen.com.wtfay.services.test;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tim on 6/29/16.
 */
public class TestAdapter {
   public static SimpleTestService createService() {
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(SimpleTestService.endpoint)
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       SimpleTestService service = retrofit.create(SimpleTestService.class);
       return service;
   }
}
