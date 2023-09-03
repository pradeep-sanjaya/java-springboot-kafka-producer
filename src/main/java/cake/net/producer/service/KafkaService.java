package cake.net.producer.service;

import cake.net.producer.model.Message;

public interface KafkaService {
    void sendMessage(Message message);
}
