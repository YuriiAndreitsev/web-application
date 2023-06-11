package com.app.controller.kafka;

import com.app.dto.kafka.MessageRequest;
import com.app.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
@RequiredArgsConstructor
public class KafkaMessageController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConsumerService consumerService;

    @PostMapping()
    public void publish(@RequestBody MessageRequest messageRequest, @RequestParam String topic) {
        kafkaTemplate.send(topic, messageRequest.message());
    }

    @GetMapping()
    public List<String> receive(@RequestParam String topic){
        return consumerService.receiveUnreadMessages(topic);
    }

}
