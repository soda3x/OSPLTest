import org.opensplice.dds.dcps.Utilities;

public final class TempSensorTypeTypeSupportHelper
{

    public static TempSensorTypeTypeSupport narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof TempSensorTypeTypeSupport) {
            return (TempSensorTypeTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static TempSensorTypeTypeSupport unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof TempSensorTypeTypeSupport) {
            return (TempSensorTypeTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
