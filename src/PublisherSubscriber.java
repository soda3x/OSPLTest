import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.policy.Durability;
import org.omg.dds.core.policy.History;
import org.omg.dds.core.policy.QosPolicy.ForPublisher;
import org.omg.dds.core.policy.Reliability;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.Publisher;
import org.omg.dds.pub.PublisherQos;
import org.omg.dds.sub.DataReader;
import org.omg.dds.sub.Sample.Iterator;
import org.omg.dds.sub.Subscriber;
import org.omg.dds.topic.Topic;
import org.omg.dds.topic.TopicQos;
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
  private History historyPolicy;
  private Reliability reliabilityPolicy;

  private static final Logger log = LogManager.getLogger(PublisherSubscriber.class);


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

    // Create QoS policies
    // The following settings will allow late-joiners to receive all new data and all data
    // previously sent from publishers
    this.durabilityPolicy = PolicyFactory.getPolicyFactory(env).Durability().withTransientLocal();
    this.reliabilityPolicy = PolicyFactory.getPolicyFactory(env).Reliability().withReliable();
    this.historyPolicy = PolicyFactory.getPolicyFactory(env).History().withKeepAll();

    // Create DataWriter
    this.dataWriter =
        publisher.createDataWriter(tempSensorTypeTopic, publisher.getDefaultDataWriterQos()
            .withPolicies(this.durabilityPolicy, this.reliabilityPolicy, this.historyPolicy));
  }

  /**
   * Private Getter for participant
   * 
   * @return participant
   */
  private DomainParticipant getParticipant() {
    return this.participant;
  }

  /**
   * Create subscriber and return Data Reader
   * 
   * @return DataReader
   */
  public DataReader<TempSensorType> createSubscriber() {
    Subscriber s = this.getParticipant().createSubscriber();
    return s.createDataReader(tempSensorTypeTopic, s.getDefaultDataReaderQos()
        .withPolicies(this.durabilityPolicy, this.reliabilityPolicy, this.historyPolicy));
  }

  /**
   * Get Readings from Publisher
   * 
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
   * 
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
    try {
      this.dataWriter.write(t);
    } catch (TimeoutException e) {
      log.error("Error trying to create temperature reading - Timeout", e);
    }
  }
}
