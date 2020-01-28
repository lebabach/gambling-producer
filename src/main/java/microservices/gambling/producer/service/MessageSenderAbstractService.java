package microservices.gambling.producer.service;

import lombok.extern.slf4j.Slf4j;
import microservices.gambling.producer.service.dto.MessageDTO;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;

@Slf4j
public abstract class MessageSenderAbstractService {
    private final MessageChannel messageChannel;

    public MessageSenderAbstractService(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    public void sendMessage(MessageDTO msg) {
        Message message = MessageBuilder.withPayload(msg.getData())
                                           .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                                           .setHeader(KafkaHeaders.MESSAGE_KEY,msg.getMessageKey().getBytes())
                                           .copyHeaders(msg.getHeader())
                                           .build();

        boolean sent = messageChannel.send(message);
        if (sent) {
            log.info("{} message is sent successfully.", msg.toString());
        } else {
            String errorMessage = String.format("Unable to send %s message.", msg.toString());
            log.error(errorMessage);
            throw new MessageDeliveryException(errorMessage);
        }
    }

}
