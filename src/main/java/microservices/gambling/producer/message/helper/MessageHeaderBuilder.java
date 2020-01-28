package microservices.gambling.producer.message.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for building message header as a Map
 */
public final class MessageHeaderBuilder {

    private Map<String, Object> messageHeader;

    private MessageHeaderBuilder() {
        messageHeader = new HashMap<>();
    }

    public static MessageHeaderBuilder getInstance() {
        return new MessageHeaderBuilder();
    }

    public MessageHeaderBuilder set(String key, Object value) {
        messageHeader.put(key, value);
        return this;
    }

    public MessageHeaderBuilder setIfAbsent(String key, Object value) {
        messageHeader.putIfAbsent(key, value);
        return this;
    }

    public MessageHeaderBuilder setAll(Map<String, Object> map) {
        messageHeader.putAll(map);
        return this;
    }

    public Map<String, Object> build() {
        return messageHeader;
    }

}
