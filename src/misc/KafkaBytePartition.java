package misc;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class KafkaBytePartition implements Partitioner {
    public KafkaBytePartition (VerifiableProperties props) {

    }

    @Override
	public int partition(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int partition(byte[] key, int a_numPartitions) {
        int partition = 0;
        return partition;
    }

}
