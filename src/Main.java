import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    PublisherSubscriber ps = new PublisherSubscriber();

    // Publish some data as publishers
    ps.createTemperatureReading(14, 3);
    ps.createTemperatureReading(11, 2);
    ps.createTemperatureReading(20, 4);

    // Read some data as a subscriber
    ArrayList<TempSensorType> readings = ps.getDataFromPublisher(ps.createSubscriber());
    System.out.println("Found " + readings.size() + " readings.");
    for (TempSensorType t : readings) {
      System.out.println(t);
    }
  }
}
