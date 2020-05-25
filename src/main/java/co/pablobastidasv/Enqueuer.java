package co.pablobastidasv;

import com.amazonaws.services.sqs.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import static co.pablobastidasv.AmazonLocalConfig.QUEUE_NAME;

@Component
public class Enqueuer {

    private static final Logger log = LoggerFactory.getLogger(Enqueuer.class);

    private final QueueMessagingTemplate messagingTemplate;

    public Enqueuer(QueueMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(String message){
        log.info("Message {} will be send.", message);
        messagingTemplate.convertAndSend(QUEUE_NAME, message);
        log.info("Message {} was sent.", message);
    }
}
