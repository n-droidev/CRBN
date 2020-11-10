package samir.alakbarov.crbn.entity;

public class Weather {
    
    String city, weatherType, icon;
    double temp, windSpeed;
    int pressure, humidity, windDeg;
    long sunset, sunrise;
    
    
    
    public Weather() {
    }
    
    
    
    public String getCity() {
        return city;
    }
    
    
    public void setCity(String city) {
        this.city = city;
    }
    
    
    public String getWeatherType() {
        return weatherType;
    }
    
    
    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }
    
    
    public String getIcon() {
        return icon;
    }
    
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    
    public double getTemp() {
        return temp;
    }
    
    
    public void setTemp(double temp) {
        this.temp = temp;
    }
    
    
    public double getWindSpeed() {
        return windSpeed;
    }
    
    
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    
    
    public int getPressure() {
        return pressure;
    }
    
    
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
    
    
    public int getHumidity() {
        return humidity;
    }
    
    
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    
    
    public int getWindDeg() {
        return windDeg;
    }
    
    
    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }
    
    
    public long getSunset() {
        return sunset;
    }
    
    
    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
    
    
    public long getSunrise() {
        return sunrise;
    }
    
    
    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }
}