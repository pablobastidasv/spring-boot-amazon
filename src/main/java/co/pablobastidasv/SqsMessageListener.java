package co.pablobastidasv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static co.pablobastidasv.AmazonLocalConfig.QUEUE_NAME;

@Component
public class SqsMessageListener {
    private static final Logger log = LoggerFactory.getLogger(SqsMessageListener.class);

    @SqsListener(QUEUE_NAME)
    public void listener(String message) {
        log.info("Message {} received from the queue.", message);
        var sleepTime = 2_000;
        log.info("Sleeping {} millisecond", sleepTime);
        sleep(sleepTime);
        log.info("Waking up after {} millisecond", sleepTime);
    }

    public static void sleep(int time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
