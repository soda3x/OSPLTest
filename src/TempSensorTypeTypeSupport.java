public class TempSensorTypeTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private static final long serialVersionUID = 1L;

    private long copyCache;

    public TempSensorTypeTypeSupport()
    {
        super("TempSensorType",
              "",
              "id",
              null,
              TempSensorTypeMetaHolder.metaDescriptor);
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new TempSensorTypeDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new TempSensorTypeDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new TempSensorTypeDataReaderViewImpl(this);
    }
}
