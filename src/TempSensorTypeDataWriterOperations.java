public interface TempSensorTypeDataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            TempSensorType instance_data);

    long register_instance_w_timestamp(
            TempSensorType instance_data, 
            DDS.Time_t source_timestamp);

    int unregister_instance(
            TempSensorType instance_data, 
            long handle);

    int unregister_instance_w_timestamp(
            TempSensorType instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            TempSensorType instance_data, 
            long handle);

    int write_w_timestamp(
            TempSensorType instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            TempSensorType instance_data, 
            long instance_handle);

    int dispose_w_timestamp(
            TempSensorType instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            TempSensorType instance_data, 
            long instance_handle);

    int writedispose_w_timestamp(
            TempSensorType instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            TempSensorTypeHolder key_holder, 
            long handle);
    
    long lookup_instance(
            TempSensorType instance_data);

}
