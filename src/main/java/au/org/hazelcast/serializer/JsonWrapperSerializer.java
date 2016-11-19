package au.org.hazelcast.serializer;

import au.org.hazelcast.model.JsonWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.smile.SmileGenerator;
import com.fasterxml.jackson.dataformat.smile.SmileParser;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by proy on 19/11/16.
 */
@Component
public class JsonWrapperSerializer implements StreamSerializer<JsonWrapper> {
    ObjectReader reader;
    ObjectWriter writer;
    private static ObjectMapper MAPPER;

    static {
        SmileFactory smileFactory = new SmileFactory();
        smileFactory.configure(SmileParser.Feature.REQUIRE_HEADER, false);
        smileFactory.configure(SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT, true);
        MAPPER = new ObjectMapper(smileFactory);
    }

    private JsonWrapperSerializer() {
        reader = MAPPER.readerFor(JsonNode.class);
        writer = MAPPER.writerFor(JsonNode.class);
    }

    @Override
    public void write(ObjectDataOutput objectDataOutput, JsonWrapper jsonWrapper) throws IOException {
        objectDataOutput.write(writer.writeValueAsBytes(jsonWrapper.getRawJson()));
    }

    @Override
    public JsonWrapper read(ObjectDataInput objectDataInput) throws IOException {
        return new JsonWrapper((JsonNode) reader.readValue(objectDataInput.readByteArray()));
    }

    @Override
    public int getTypeId() {
        return 3;
    }

    @Override
    public void destroy() {

    }
}
