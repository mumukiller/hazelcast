package com.hazelcast.dataset;

import com.hazelcast.config.Config;
import com.hazelcast.config.DataSetConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spi.properties.GroupProperty;
import com.hazelcast.test.HazelcastTestSupport;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataSetTest extends HazelcastTestSupport {

    @Test
    public void test() {
        Config config = new Config();
        config.setProperty(GroupProperty.PARTITION_COUNT.getName(), "1");
        config.addDataSetConfig(new DataSetConfig("foo").setKeyClass(Long.class).setValueClass(Employee.class));

        HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);

        DataSet<Long, Employee> dataSet = hz.getDataSet("foo");
        for (int k = 0; k < 5; k++) {
            dataSet.set((long) k, new Employee(k, k, k));
        }
    }

    @Test
    public void testMemoryConsumption() {
        Config config = new Config();
        config.addDataSetConfig(new DataSetConfig("foo").setKeyClass(Long.class).setValueClass(Employee.class));

        HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);

        DataSet<Long, Employee> dataSet = hz.getDataSet("foo");
        for (int k = 0; k < 5; k++) {
            dataSet.set((long) k, new Employee(k, k, k));
        }

        assertEquals(20 * 5, dataSet.consumedMemory());
    }
}
