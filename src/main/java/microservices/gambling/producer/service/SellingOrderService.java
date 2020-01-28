package microservices.gambling.producer.service;

import lombok.extern.slf4j.Slf4j;
import microservices.gambling.producer.message.channel.SellingOrderChannel;
import microservices.gambling.producer.message.helper.MessageHeaderBuilder;
import microservices.gambling.producer.service.dto.MessageDTO;
import microservices.gambling.producer.service.dto.SellingOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class SellingOrderService extends MessageSenderAbstractService{

    @Autowired
    public SellingOrderService(final SellingOrderChannel sellingOrderChannel) {
        super(sellingOrderChannel.sellingOrderOutputChannel());
    }

    public void publishMessage(SellingOrderDTO sellingOrderDTO) {
        Map<String, Object> header = MessageHeaderBuilder.getInstance().set("action", "action").build();
        MessageDTO msg = MessageDTO.builder()
                                   .header(header)
                                   .data(sellingOrderDTO)
                                   .messageKey(UUID.randomUUID().toString()).build();
        log.info("{} message is publishing.", msg.toString());
        this.sendMessage(msg);
    }
}
