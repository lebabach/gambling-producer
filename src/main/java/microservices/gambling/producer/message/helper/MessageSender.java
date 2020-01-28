package microservices.gambling.producer.message.helper;

import lombok.extern.slf4j.Slf4j;
import microservices.gambling.producer.service.dto.AbstractEntityDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;

import java.util.Map;

@Slf4j
public class MessageSender <T extends AbstractEntityDTO>{
    private final MessageChannel messageChannel;

    public MessageSender(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    /**
     * @param messageBody the entity that need to be sent as a payload
     * @param messageHeaderMap the map that will be sent as a message header
     */
    public boolean sendMessage(T messageBody, Map<String, Object> messageHeaderMap) {

        Message<T> message = MessageBuilder.withPayload(messageBody)
                                           .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                                           .copyHeaders(messageHeaderMap)
                                           .build();

        boolean sent = messageChannel.send(message);

        if (sent) {
            log.info("{} event message is sent successfully.", messageBody);
        } else {
            String errorMessage = String.format("Unable to send %s message.", messageBody);
            log.error(errorMessage);
            throw new MessageDeliveryException(errorMessage);
        }
        return true;
    }

}
