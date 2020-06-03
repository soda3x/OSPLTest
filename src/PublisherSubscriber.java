import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.policy.Durability;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.DataWriterQos;
import org.omg.dds.pub.Publisher;
import org.omg.dds.sub.DataReader;
import org.omg.dds.sub.Sample.Iterator;
import org.omg.dds.sub.Subscriber;
import org.omg.dds.topic.Topic;
import org.opensplice.dds.core.policy.PolicyFactory;

public class PublisherSubscriber {
  
  private ServiceEnvironment env;
  private DomainParticipantFactory dpf;
  private DomainParticipant participant;
  private Topic<TempSensorType> tempSensorTypeTopic;
  private Publisher publisher;
  private DataWriter<TempSensorType> dataWriter;
  private short idCounter;
  private Durability durabilityPolicy;
  
  
  /**
   * Constructor configures OSPL
   */
  public PublisherSubscriber() {
    this.idCounter = 0;
    // The service environment is created for the application.
    System.setProperty(ServiceEnvironment.IMPLEMENTATION_CLASS_NAME_PROPERTY,
        "org.opensplice.dds.core.OsplServiceEnvironment");

    // Create service environment to allow for domain factory creation
    this.env = ServiceEnvironment.createInstance(PublisherSubscriber.class.getClassLoader());

    // Create domain factory to create domain
    this.dpf = DomainParticipantFactory.getInstance(env);

    // Create domain
    this.participant = dpf.createParticipant();

    // Create topic
    this.tempSensorTypeTopic = participant.createTopic("TempSensorReadings", TempSensorType.class);

    // Create publisher
    this.publisher = participant.createPublisher();
    
    // Create QoS
    this.durabilityPolicy = PolicyFactory.getPolicyFactory(env).Durability().withTransient();
    
    // Create DataWriter
    this.dataWriter = publisher.createDataWriter(tempSensorTypeTopic, publisher.getDefaultDataWriterQos().withPolicy(this.durabilityPolicy));
  }
  
  /**
   * Private Getter for participant
   * @return participant
   */
  private DomainParticipant getParticipant() {
    return this.participant;
  }
  
  /**
   * Create subscriber and return Data Reader
   * @return DataReader
   */
  public DataReader<TempSensorType> createSubscriber() {
    Subscriber s = this.getParticipant().createSubscriber();
    return s.createDataReader(tempSensorTypeTopic, s.getDefaultDataReaderQos().withPolicy(this.durabilityPolicy));
  }
  
  /**
   * Get Readings from Publisher
   * @param DataReader
   * @return temperature readings
   */
  public ArrayList<TempSensorType> getDataFromPublisher(DataReader<TempSensorType> reader) {
    ArrayList<TempSensorType> output = new ArrayList<TempSensorType>();
    Iterator<TempSensorType> result = reader.read();
    while (result.hasNext()) {
      TempSensorType t = result.next().getData();
      output.add(t);
    }
    return output;
  }
  
  /**
   * Create temperature reading and return instance handle
   * @param temperature
   * @param humidity
   * @return temperature sensor instance
   */
  public void createTemperatureReading(float temp, float hum) {
    TempSensorType t = new TempSensorType();
    t.id = ++idCounter;
    t.temp = temp;
    t.hum = hum;
    t.scale = TemperatureScale.CELSIUS;
//    System.out.println("DEBUG: Created Temperature Sensor: " + t.toString());
    try {
      this.dataWriter.write(t);
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }
}
