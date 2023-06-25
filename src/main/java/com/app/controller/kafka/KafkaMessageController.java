package com.app.controller.kafka;

import com.app.dto.kafka.MessageRequest;
import com.app.kafka.consumers.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/messages")
@RequiredArgsConstructor
public class KafkaMessageController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConsumer consumerService;

    @PostMapping()
    public void publish(@RequestBody MessageRequest messageRequest, @RequestParam String topic) {
        kafkaTemplate.send(topic, messageRequest.message());
    }

    @GetMapping()
    public void receive(@RequestParam String topic) {
        consumerService.consumeMessagesOnTopic(topic);
    }

}
