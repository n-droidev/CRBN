package samir.alakbarov.crbn.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.widget.ANImageView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import samir.alakbarov.crbn.R;
import samir.alakbarov.crbn.entity.Weather;
import samir.alakbarov.crbn.helper.WeatherDataParser;


public class WeatherActivity extends AppCompatActivity {
    
    // Weather Api
    private final String WEATHER_API = "https://api.openweathermap.org/data/2.5/weather";
    // Weather icon api
    private final String WEATHER_ICON_API = "https://openweathermap.org/img/wn/";
    
    private TextView cityTV, tempTV, weatherTypeTV, pressureTV, humidityTV, windSpeedTV, windDegreeTV, sunriseTV, sunsetTV;
    private ANImageView iconIV;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(this);
        setContentView(R.layout.activity_weather);
        
        Button weatherBT = findViewById(R.id.getWeatherBT);
        cityTV = findViewById(R.id.cityTV);
        tempTV = findViewById(R.id.tempTV);
        weatherTypeTV = findViewById(R.id.weatherTypeTV);
        pressureTV = findViewById(R.id.pressureTV);
        humidityTV = findViewById(R.id.humidityTV);
        windSpeedTV = findViewById(R.id.windSpeedTV);
        windDegreeTV = findViewById(R.id.windDegreeTV);
        sunriseTV = findViewById(R.id.sunriseTV);
        sunsetTV = findViewById(R.id.sunsetTV);
        iconIV = findViewById(R.id.weatherIcon);
        
        
        weatherBT.setOnClickListener(v -> {
            
            // Sending weather network request
            AndroidNetworking.get(WEATHER_API)
                    .addQueryParameter("appid", getResources().getString(R.string.weather_api_key))
                    .addQueryParameter("q", "baku")
                    .build().getAsJSONObject(new JSONObjectRequestListener() {
                @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
                @Override
                public void onResponse(JSONObject jsonObject) {
                    
                    // Parsing json via WeatherDataParser custom class
                    Weather weather = WeatherDataParser.parseWeather(jsonObject);
                    if (weather == null) {
                        Toast.makeText(WeatherActivity.this, "Weather object null!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // Setting data to views
                    cityTV.setText("City: " + weather.getCity());
                    tempTV.setText("Temperature: " + weather.getTemp() + " \u2103");
                    weatherTypeTV.setText("Condition : " + weather.getWeatherType());
                    pressureTV.setText("Pressure: " + weather.getPressure() * 100 + " Pa");
                    humidityTV.setText("Humidity: " + weather.getHumidity() + "%");
                    windSpeedTV.setText("Wind speed: " + weather.getWindSpeed() + " m/s");
                    windDegreeTV.setText("Wind direction: " + weather.getWindDeg() + "\u00B0");
                    sunriseTV.setText("Sunrise: " + new SimpleDateFormat("HH:mm").format(new Date(weather.getSunrise())));
                    sunsetTV.setText("Sunset: " + new SimpleDateFormat("HH:mm").format(new Date(weather.getSunset())));
                    
                    iconIV.setErrorImageResId(R.drawable.ic_baseline_error_24);
                    iconIV.setImageUrl(WEATHER_ICON_API + weather.getIcon() + "@2x.png");
                    Log.e("RESPONSE", jsonObject.toString());
                    
                }
                
                
                @Override
                public void onError(ANError anError) {
                    Toast.makeText(WeatherActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            });
            
        });
        
    }
    
}