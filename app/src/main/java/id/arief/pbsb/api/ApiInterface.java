package id.arief.pbsb.api;


import java.util.List;

import id.arief.pbsb.model.News;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;


/**
 * Created by arief on 02/01/16.
 */
public interface ApiInterface {


    @GET("/wp-json/wp/v2/posts")
    Call<List<News>> getLatestPost();

    String BASE_URL = "http://stmikbjb-nirzaq.rhcloud.com/";



    class Factory {
        private static ApiInterface service;

        public static ApiInterface getInstance() {
            if (service == null) {

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(ApiInterface.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
