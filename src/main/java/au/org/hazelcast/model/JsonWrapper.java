package au.org.hazelcast.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Created by proy on 19/11/16.
 */
@ToString
@NoArgsConstructor
public class JsonWrapper {
    @Getter @Setter
    private Map<String, Object> rawJson;

    public JsonWrapper(Map rawJson) {
        this.rawJson = rawJson;
    }

    public Object getAttribute(String key) {
        return rawJson.get(key);
    }

}
