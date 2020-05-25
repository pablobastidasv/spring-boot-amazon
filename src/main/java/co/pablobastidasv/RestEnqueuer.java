package co.pablobastidasv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEnqueuer {

    private static final Logger log = LoggerFactory.getLogger(RestEnqueuer.class);

    private final Enqueuer enqueuer;

    public RestEnqueuer(Enqueuer enqueuer) {
        this.enqueuer = enqueuer;
    }

    @GetMapping("queue")
    public void queueMessage(@RequestParam("message") String message){
        log.info("Http query param to be processed: {}.", message);
        enqueuer.send(message);
    }
}
