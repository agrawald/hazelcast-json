package au.org.hazelcast.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by proy on 19/11/16.
 */
@ToString
@NoArgsConstructor
public class JsonWrapper {
    @Getter @Setter
    private JsonNode rawJson;

    public JsonWrapper(JsonNode rawJson) {
        this.rawJson = rawJson;
    }

    public JsonNode getAttribute(String key) {
        return rawJson.get(key);
    }

}
