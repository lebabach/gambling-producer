package microservices.gambling.producer.service;

import microservices.gambling.producer.message.channel.SellingOrderChannel;
import microservices.gambling.producer.message.helper.MessageSender;
import microservices.gambling.producer.service.dto.SellingOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class SellingOrderService {
    @Autowired
    private SellingOrderChannel sellingOrderChannel;

    public SellingOrderService(final SellingOrderChannel sellingOrderChannel) {
        this.sellingOrderChannel = sellingOrderChannel;
    }

    public boolean send(SellingOrderDTO sellingOrderDTO) {
        MessageChannel channel = sellingOrderChannel.sellingOrderOutputChannel();
        MessageSender sender = new MessageSender(channel);
        return sender.sendMessage(sellingOrderDTO, null);
    }
}
