package cake.net.producer;

import cake.net.producer.model.Message;
import cake.net.producer.service.impl.KafkaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplication implements CommandLineRunner {

	public static int MESSAGE_COUNT;

	public static String MESSAGE;

	@Value("${message}")
	public void setMessage(String message) {
		this.MESSAGE = message;
	}

	@Value("${messages}")
	public void setMessageCount(int messageCount) {
		JavaApplication.MESSAGE_COUNT = messageCount;
	}

	Logger logger = LoggerFactory.getLogger(JavaApplication.class);
	private final KafkaServiceImpl kafkaService;

	@Autowired
	public JavaApplication(KafkaServiceImpl kafkaService) {
		this.kafkaService = kafkaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaApplication.class, args);
	}

	public void produceMessage() {
		Message message = new Message(MESSAGE);
		kafkaService.sendMessage(message);
	}

	@Override
	public void run(String... args) throws Exception {

		long startTime = System.currentTimeMillis();

		for (int cou = 0 ; cou < MESSAGE_COUNT ; cou++) {
			produceMessage();
		}

		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		logger.info("Time taken for {} messages {} milliseconds", MESSAGE_COUNT, timeTaken);
	}
}
