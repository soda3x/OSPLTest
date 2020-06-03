import org.opensplice.dds.dcps.Utilities;

public final class TempSensorTypeDataWriterHelper
{

    public static TempSensorTypeDataWriter narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof TempSensorTypeDataWriter) {
            return (TempSensorTypeDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static TempSensorTypeDataWriter unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof TempSensorTypeDataWriter) {
            return (TempSensorTypeDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
