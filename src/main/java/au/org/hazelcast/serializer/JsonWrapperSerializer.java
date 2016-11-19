package au.org.hazelcast.serializer;

import au.org.hazelcast.model.JsonWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import org.boon.json.JsonFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by proy on 19/11/16.
 */
@Component
public class JsonWrapperSerializer implements StreamSerializer<JsonWrapper> {
    private static org.boon.json.ObjectMapper MAPPER;

    static {
        MAPPER = JsonFactory.create();
    }

    @Override
    public void write(ObjectDataOutput objectDataOutput, JsonWrapper jsonWrapper) throws IOException {
        objectDataOutput.writeUTF(MAPPER.writeValueAsString(jsonWrapper.getRawJson()));
    }

    @Override
    public JsonWrapper read(ObjectDataInput objectDataInput) throws IOException {
        return new JsonWrapper(MAPPER.readValue(objectDataInput.readUTF(), Map.class));
    }

    @Override
    public int getTypeId() {
        return 3;
    }

    @Override
    public void destroy() {

    }
}
