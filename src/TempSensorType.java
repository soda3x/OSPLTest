public final class TempSensorType {

    public short id;
    public float temp;
    public float hum;
    public TemperatureScale scale = TemperatureScale.from_int(0);

    public TempSensorType() {
    }

    public TempSensorType(
        short _id,
        float _temp,
        float _hum,
        TemperatureScale _scale)
    {
        id = _id;
        temp = _temp;
        hum = _hum;
        scale = _scale;
    }
    
    @Override
    public String toString() {
      String strScale = "";
       if (scale == TemperatureScale.CELSIUS) {
         strScale = "Celsius";
       } else if (scale == TemperatureScale.FAHRENHEIT) {
         strScale = "Fahrenheit";
       } else {
         strScale = "Kelvin";
       }
      return "ID: " + this.id + " Temperature: " + this.temp + " degrees " + strScale + " Humidity: " + this.hum;
    }

}
