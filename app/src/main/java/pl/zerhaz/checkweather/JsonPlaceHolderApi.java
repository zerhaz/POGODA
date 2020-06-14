package pl.zerhaz.checkweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("weather")
    Call<Post> getPosts(
            @Query("id") String cityID,
            @Query("lang") String language,
            @Query("appid") String appAPI
    );

    @GET("forecast")
    Call<Forecast> getForecast(
            @Query("id") String cityID,
            @Query("lang") String language,
            @Query("appid") String appAPI
    );
}
