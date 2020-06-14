package pl.zerhaz.checkweather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String APIKEY = "0e292b30628880ee9ba43e8125a98d12";
    private String LANG = "pl";

    private String cityID = "idKey";
    private String cityName = "nameKey";
    private String spinnerPosition = "positionKey";

    private String[] citiesList = new String[]{"Białystok", "Bydgoszcz", "Gdańsk", "Katowice", "Kielce", "Kraków", "Lublin", "Łódź", "Olsztyn", "Opole"
            , "Poznań", "Rzeszów", "Szczecin", "Warszawa", "Wrocław", "Zielona Góra"};

    private TextView textViewError;
    private TextView textViewCity;
    private TextView textViewTemperature;
    private TextView textViewWind;
    private TextView textViewPressure;
    private TextView textViewTomorrowMax;
    private TextView textViewTomorrowMin;
    private TextView textViewAfterTomorrowMax;
    private TextView textViewAfterTomorrowMin;

    private AdView mAdView;

    private String urlCurrent = "http://api.openweathermap.org/data/2.5/";

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences getPref = getPreferences(Context.MODE_PRIVATE);
        cityID = getPref.getString("cityID",null);
        cityName = getPref.getString("cityName",null);
        spinnerPosition = getPref.getString("spinnerPosition","13");

        Spinner citiesSpinner = findViewById(R.id.spinner_cities);
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, citiesList);
        citiesAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        citiesSpinner.setAdapter(citiesAdapter);
        citiesSpinner.setSelection(Integer.parseInt(spinnerPosition));
        citiesSpinner.setOnItemSelectedListener(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewBaner_1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        textViewError = findViewById(R.id.textview_error_msg);
        textViewCity = findViewById(R.id.textview_city);
        textViewTemperature = findViewById(R.id.textview_temperature);
        textViewWind = findViewById(R.id.textview_wind);
        textViewPressure = findViewById(R.id.textview_pressure);
        textViewTomorrowMax = findViewById(R.id.textview_tomorrow_temp_max);
        textViewTomorrowMin = findViewById(R.id.textview_tomorrow_temp_min);
        textViewAfterTomorrowMax = findViewById(R.id.textview_aftertomorrow_temp_max);
        textViewAfterTomorrowMin = findViewById(R.id.textview_aftertomorrow_temp_min);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        SharedPreferences sharedpreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        if (citiesList[position].equals("Białystok")) {
            editor.putString("spinnerPosition", "0");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "776069");
            callRequest("776069");
        }
        if (citiesList[position].equals("Bydgoszcz")) {
            editor.putString("spinnerPosition", "1");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "3102014");
            callRequest("3102014");
        }
        if (citiesList[position].equals("Gdańsk")) {
            editor.putString("spinnerPosition", "2");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "3099434");
            callRequest("3099434");
        }
        if (citiesList[position].equals("Katowice")) {
            editor.putString("spinnerPosition", "3");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "3096472");
            callRequest("3096472");
        }
        if (citiesList[position].equals("Kielce")) {
            editor.putString("spinnerPosition", "4");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "769250");
            callRequest("769250");
        }
        if (citiesList[position].equals("Kraków")) {
            editor.putString("spinnerPosition", "5");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "3094802");
            callRequest("3094802");
        }
        if (citiesList[position].equals("Lublin")) {
            editor.putString("spinnerPosition", "6");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "765876");
            callRequest("765876");
        }
        if (citiesList[position].equals("Łódź")) {
            editor.putString("spinnerPosition", "7");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "3093133");
            callRequest("3093133");
        }
        if (citiesList[position].equals("Olsztyn")) {
            editor.putString("spinnerPosition", "8");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "763166");
            callRequest("763166");
        }
        if (citiesList[position].equals("Opole")) {
            editor.putString("spinnerPosition", "9");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "3090048");
            callRequest("3090048");
        }
        if (citiesList[position].equals("Poznań")) {
            editor.putString("spinnerPosition", "10");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "7530858");
            callRequest("7530858");
        }
        if (citiesList[position].equals("Rzeszów")) {
            editor.putString("spinnerPosition", "11");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "7532474");
            callRequest("7532474");
        }
        if (citiesList[position].equals("Szczecin")) {
            editor.putString("spinnerPosition", "12");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "7530840");
            callRequest("7530840");
        }
        if (citiesList[position].equals("Warszawa")) {
            editor.putString("spinnerPosition", "13");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "7531926");
            callRequest("7531926");
        }
        if (citiesList[position].equals("Wrocław")) {
            editor.putString("spinnerPosition", "14");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "3081368");
            callRequest("3081368");
        }
        if (citiesList[position].equals("Zielona Góra")) {
            editor.putString("spinnerPosition", "15");
            editor.putString("cityName", citiesList[position]);
            editor.putString("cityID", "7530991");
            callRequest("7530991");
        }

        editor.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void callRequest(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlCurrent)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Post> call = jsonPlaceHolderApi.getPosts(id, LANG, APIKEY);
        Call<Forecast> forecast_call = jsonPlaceHolderApi.getForecast(id, LANG, APIKEY);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewError.setText("Code: " + response.code());
                    textViewCity.setText("");
                    textViewTemperature.setText("-");
                    textViewWind.setText("-");
                    textViewPressure.setText("-");
                    return;
                }
                textViewError.setText("");
                textViewCity.setText(response.body().getCityName());
                textViewTemperature.setText(response.body().getWeather().getTemperature() + " °C");
                textViewWind.setText(response.body().getWind().getWindSpeed() + " m/s");
                textViewPressure.setText(response.body().getWeather().getPressure() + " hPa");

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewError.setText(t.getMessage());
                textViewCity.setText("");
                textViewTemperature.setText("-");
                textViewWind.setText("-");
                textViewPressure.setText("-");
            }
        });

        forecast_call.enqueue(new Callback<Forecast>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (!response.isSuccessful()) {
                    textViewError.setText("Code: " + response.code());
                    textViewTomorrowMax.setText("-");
                    textViewTomorrowMin.setText("-");
                    textViewAfterTomorrowMax.setText("-");
                    textViewAfterTomorrowMin.setText("-");
                    return;
                }

                Date currentDate = new Date();
                Calendar localDateTime1 = Calendar.getInstance();
                Calendar localDateTime2 = Calendar.getInstance();
                localDateTime1.setTime(currentDate);
                localDateTime2.setTime(currentDate);
                localDateTime1.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
                localDateTime2.add(Calendar.DATE, 2); //same with c.add(Calendar.DAY_OF_MONTH, 1);
                Date currentDatePlusOneDay = localDateTime1.getTime();
                Date currentDatePlusTwoDays = localDateTime2.getTime();
                String tomorrowDateMAX = dateFormat.format(currentDatePlusOneDay) + " 15:00:00";
                String tomorrowDateMIN = dateFormat.format(currentDatePlusOneDay) + " 03:00:00";
                String afterTomorrowDateMAX = dateFormat.format(currentDatePlusTwoDays) + " 15:00:00";
                String afterTomorrowDateMIN = dateFormat.format(currentDatePlusTwoDays) + " 03:00:00";

                for (int i = 0; i < 30; i++) {
                    if (response.body().getForecastList(i).contains(tomorrowDateMAX)) {
                        String[] words = response.body().getForecastList(i).split(" ");
                        for (String word : words) {
                            if (word.startsWith("main={temp=")) {
                                int t = Integer.parseInt(word.substring(11, 14)) - 273;
                                String s = "";
                                if (t >= 0) {
                                    s = "+" + t + " °C";
                                    textViewTomorrowMax.setText(s);
                                } else {
                                    s = t + " °C";
                                    textViewTomorrowMax.setText(s);
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < 30; i++) {
                    if (response.body().getForecastList(i).contains(tomorrowDateMIN)) {
                        String[] words = response.body().getForecastList(i).split(" ");
                        for (String word : words) {
                            if (word.startsWith("main={temp=")) {
                                int t = Integer.parseInt(word.substring(11, 14)) - 273;
                                String s = "";
                                if (t >= 0) {
                                    s = "+" + t + " °C";
                                    textViewTomorrowMin.setText(s);
                                } else {
                                    s = t + " °C";
                                    textViewTomorrowMin.setText(s);
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < 30; i++) {
                    if (response.body().getForecastList(i).contains(afterTomorrowDateMAX)) {
                        String[] words = response.body().getForecastList(i).split(" ");
                        for (String word : words) {
                            if (word.startsWith("main={temp=")) {
                                int t = Integer.parseInt(word.substring(11, 14)) - 273;
                                String s = "";
                                if (t >= 0) {
                                    s = "+" + t + " °C";
                                    textViewAfterTomorrowMax.setText(s);
                                } else {
                                    s = t + " °C";
                                    textViewAfterTomorrowMax.setText(s);
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < 30; i++) {
                    if (response.body().getForecastList(i).contains(afterTomorrowDateMIN)) {
                        String[] words = response.body().getForecastList(i).split(" ");
                        for (String word : words) {
                            if (word.startsWith("main={temp=")) {
                                int t = Integer.parseInt(word.substring(11, 14)) - 273;
                                String s = "";
                                if (t >= 0) {
                                    s = "+" + t + " °C";
                                    textViewAfterTomorrowMin.setText(s);
                                } else {
                                    s = t + " °C";
                                    textViewAfterTomorrowMin.setText(s);
                                }
                            }
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                textViewError.setText(t.getMessage());
                textViewTomorrowMax.setText("-");
                textViewTomorrowMin.setText("-");
                textViewAfterTomorrowMax.setText("-");
                textViewAfterTomorrowMin.setText("-");
            }

        });

    }

}
