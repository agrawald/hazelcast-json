package au.org.hazelcast.serializer;

import au.org.hazelcast.model.JsonWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by proy on 19/11/16.
 */
@Component
public class JsonWrapperSerializer implements StreamSerializer<JsonWrapper> {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectReader READER = MAPPER.readerFor(JsonNode.class);
    private static final ObjectWriter WRITER = MAPPER.writerFor(JsonNode.class);

    @Override
    public void write(ObjectDataOutput objectDataOutput, JsonWrapper jsonWrapper) throws IOException {
        objectDataOutput.writeUTF(WRITER.writeValueAsString(jsonWrapper.getRawJson()));
    }

    @Override
    public JsonWrapper read(ObjectDataInput objectDataInput) throws IOException {
        return new JsonWrapper((JsonNode) READER.readValue(objectDataInput.readUTF()));
    }

    @Override
    public int getTypeId() {
        return 3;
    }

    @Override
    public void destroy() {

    }
}
