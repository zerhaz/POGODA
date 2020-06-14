package pl.zerhaz.checkweather;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

class Weather {

    @SerializedName("temp")
    private Double temperature;
    @SerializedName("pressure")
    private Double pressure;

    private static DecimalFormat df = new DecimalFormat("0.0");

    public String getTemperature() {
        temperature = temperature - 273.15;
        return df.format(temperature);
    }

    public String getPressure() {
        int p = (int) Math.round(pressure);
        return Integer.toString(p);
    }

}


