package microservices.gambling.producer.web.rest;

import microservices.gambling.producer.service.SellingOrderService;
import microservices.gambling.producer.service.dto.SellingOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GamblingProducerResource {
    private final Logger log = LoggerFactory.getLogger(GamblingProducerResource.class);

    @Autowired
    SellingOrderService sellingOrderService;

    public GamblingProducerResource(final SellingOrderService sellingOrderService) {
        this.sellingOrderService = sellingOrderService;
    }

    @PostMapping("/selling-order")
    public ResponseEntity<?> sellOrder(@RequestBody SellingOrderDTO sellingOrderDTO) {

        if (sellingOrderService.send(sellingOrderDTO)) {
            return new ResponseEntity<>(sellingOrderDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to produce selling order to kafka", HttpStatus.BAD_REQUEST);
    }
}
