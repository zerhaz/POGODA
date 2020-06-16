package pl.zerhaz.checkweather;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

public class Wind {

    @SerializedName("speed")
    private Double windspeed;

    private static DecimalFormat df = new DecimalFormat("0.0");

    public String getWindSpeed() {
        return df.format(windspeed);
    }

}
