package com.app.controller.kafka;

import com.app.dto.kafka.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
@RequiredArgsConstructor
public class KafkaMessageController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping()
    public void publish(@RequestBody MessageRequest messageRequest) {
        kafkaTemplate.send("testTopic", messageRequest.message());
    }
}
