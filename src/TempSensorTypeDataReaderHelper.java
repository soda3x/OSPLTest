import org.opensplice.dds.dcps.Utilities;

public final class TempSensorTypeDataReaderHelper
{

    public static TempSensorTypeDataReader narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof TempSensorTypeDataReader) {
            return (TempSensorTypeDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static TempSensorTypeDataReader unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof TempSensorTypeDataReader) {
            return (TempSensorTypeDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
