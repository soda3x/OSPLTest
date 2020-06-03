import org.omg.dds.core.event.DataAvailableEvent;
import org.omg.dds.sub.DataReaderAdapter;

public class TempSensorDataReaderListener extends DataReaderAdapter<TempSensorType> {
  
  public TempSensorDataReaderListener() {
    super();
  }
  
  @Override
  public void onDataAvailable(DataAvailableEvent<TempSensorType> status) {
    
  }
}
