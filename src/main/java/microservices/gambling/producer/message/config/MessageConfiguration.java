package microservices.gambling.producer.message.config;

import microservices.gambling.producer.message.channel.SellingOrderChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = {SellingOrderChannel.class})
public class MessageConfiguration {

}
