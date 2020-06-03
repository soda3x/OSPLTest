import org.opensplice.dds.dcps.Utilities;

public class TemperatureScale {

    private int __value;
    private static int __size = 3;
    private static TemperatureScale[] __array = new TemperatureScale[__size];

    public static final int _CELSIUS = 0;
    public static final TemperatureScale CELSIUS = new TemperatureScale(_CELSIUS);

    public static final int _FAHRENHEIT = 1;
    public static final TemperatureScale FAHRENHEIT = new TemperatureScale(_FAHRENHEIT);

    public static final int _KELVIN = 2;
    public static final TemperatureScale KELVIN = new TemperatureScale(_KELVIN);

    public int value ()
    {
        return __value;
    }

    public static TemperatureScale from_int (int value)
    {
        if (value >= 0 && value < __size) {
            return __array[value];
        }
        throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_OPERATION, null);
    }

    protected TemperatureScale (int value)
    {
        __value = value;
        __array[__value] = this;
    }
}
