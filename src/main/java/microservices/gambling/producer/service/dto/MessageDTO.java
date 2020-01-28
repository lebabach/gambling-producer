package microservices.gambling.producer.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class MessageDTO<T> {
    private String messageKey;
    private Map<String, Object> header;
    private T data;
}
