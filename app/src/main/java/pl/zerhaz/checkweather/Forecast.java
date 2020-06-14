package pl.zerhaz.checkweather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    @SerializedName("list")
    private List forecastList;

    public String getForecastList(int i) {
        return forecastList.get(i).toString();
    }

}
