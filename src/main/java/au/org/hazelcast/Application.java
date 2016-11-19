package au.org.hazelcast;

import au.org.hazelcast.model.JsonWrapper;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by proy on 19/11/16.
 */
@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    HazelcastInstance instance;
    @Autowired
    org.boon.json.ObjectMapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        IMap<String, JsonWrapper> imap = instance.getMap("json-map-store");


        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            imap.put("json" + i, generate("dheeraj" + i));
        }
        log.info("Time to save: {}ms", System.currentTimeMillis() - start);

         start = System.currentTimeMillis();
        Collection<JsonWrapper> nodes = imap.values(new SqlPredicate("attribute[name]=dheeraj999999"));
        log.info("Time to find: {}ms {}", System.currentTimeMillis() - start, nodes);

    }

    private JsonWrapper generate(String value) throws IOException {
        return new JsonWrapper(mapper.readValue("{\"name\":\"" + value + "\"}", Map.class));
    }
}
