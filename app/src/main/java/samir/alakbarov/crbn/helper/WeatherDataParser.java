package samir.alakbarov.crbn.helper;

import org.json.JSONObject;

import samir.alakbarov.crbn.entity.Weather;


public class WeatherDataParser {
    
    public static Weather parseWeather(JSONObject weatherJson) {
        Weather weather = new Weather();
        // Parsing weather api response and returning Weather object
        try {
            weather.setCity("Bakı");
            weather.setSunrise(weatherJson.getJSONObject("sys").getLong("sunrise")*1000);
            weather.setSunset(weatherJson.getJSONObject("sys").getLong("sunset")*1000);
            weather.setHumidity(weatherJson.getJSONObject("main").getInt("humidity"));
            weather.setIcon(weatherJson.getJSONArray("weather").getJSONObject(0).getString("icon"));
            weather.setPressure(weatherJson.getJSONObject("main").getInt("pressure"));
            weather.setWeatherType(weatherJson.getJSONArray("weather").getJSONObject(0).getString("main"));
            weather.setWindSpeed(weatherJson.getJSONObject("wind").getDouble("speed"));
            weather.setWindDeg(weatherJson.getJSONObject("wind").getInt("deg"));
            weather.setTemp(weatherJson.getJSONObject("main").getDouble("temp")-273.15);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return weather;
    }
    
    
}
