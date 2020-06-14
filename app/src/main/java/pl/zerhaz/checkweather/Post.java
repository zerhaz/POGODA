package pl.zerhaz.checkweather;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("id")
    private int cityId;
    @SerializedName("name")
    private String cityName;
    @SerializedName("main")
    private Weather weather;
    @SerializedName("wind")
    private Wind wind;

    public Weather getWeather() {
        return weather;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public Wind getWind() {
        return wind;
    }
}
