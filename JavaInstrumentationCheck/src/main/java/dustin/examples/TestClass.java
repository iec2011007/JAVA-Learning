package dustin.examples;

import com.aerospike.client.Value;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aman.verma on 20/10/16.
 */
public class TestClass {

    private static final Logger LOG = Logger.getLogger(TestClass.class);

    public static int estimateRowSize(Map<String, Object> columns, String key) {
        LOG.debug("estimating row size");
        int estimatedSize = 0;
        for (Map.Entry<String, Object> entry : columns.entrySet()) {
            LOG.debug("Bin Key :: " + entry.getKey());
            if (entry.getValue() instanceof List) {
                estimatedSize = estimatedSize + Value.get((List) entry.getValue()).estimateSize();
            } else if (entry.getValue() instanceof Map) {
                estimatedSize = estimatedSize + Value.get((Map) entry.getValue()).estimateSize();
            } else {
                estimatedSize = estimatedSize + Value.get(entry.getValue()).estimateSize();
            }
        }
        LOG.debug("Key :: " + key + " Estimated size :: " + estimatedSize);
        return estimatedSize;
    }


    public static void main(String[] args) {
        Map<String, Object> mymap = new HashMap<>();

        List<List<Object>> obj = new ArrayList<>();
        List<Object> obj2 = new ArrayList<>();
        obj2.add(Long.valueOf(Long.MAX_VALUE));obj2.add(Long.valueOf(Long.MAX_VALUE));
        obj2.add(Long.valueOf(Long.MAX_VALUE));
        obj2.add(Long.valueOf(Long.MAX_VALUE));obj2.add(Long.valueOf(Long.MAX_VALUE));obj2.add(Long.valueOf(Long.MAX_VALUE));
        obj.add(obj2);
        List<Object> obj3 = new ArrayList<>();
        obj3.add(Long.valueOf(Long.MIN_VALUE));obj3.add(Long.valueOf(Long.MAX_VALUE));obj3.add(Long.valueOf(Long.MIN_VALUE));obj3.add(Long.valueOf(Long.MAX_VALUE));
        obj3.add(Long.valueOf(Long.MAX_VALUE));obj3.add(Long.valueOf(Long.MAX_VALUE));
        obj.add(obj3);
        mymap.put("1", obj);
        System.out.println(estimateRowSize(mymap, "2"));
    }
}
