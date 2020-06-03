import org.opensplice.dds.dcps.Utilities;

public final class TempSensorTypeDataReaderViewHelper
{

    public static TempSensorTypeDataReaderView narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof TempSensorTypeDataReaderView) {
            return (TempSensorTypeDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static TempSensorTypeDataReaderView unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof TempSensorTypeDataReaderView) {
            return (TempSensorTypeDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
