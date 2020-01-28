package microservices.gambling.producer.message.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SellingOrderChannel {
    String OUTPUT = "sellingOrderOutputChannel";

    @Output(OUTPUT)
    MessageChannel sellingOrderOutputChannel();
}
