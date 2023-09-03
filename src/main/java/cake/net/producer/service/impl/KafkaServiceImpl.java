package cake.net.producer.service.impl;

import cake.net.producer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl {

    private final String topic;

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public KafkaServiceImpl(@Value("topic") String topic, KafkaTemplate<String, Message> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message) {
        kafkaTemplate.send(topic, message);
    }
}
