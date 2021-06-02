package github.io.somesh.app.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class FileUploadEventListener {

    @Autowired
    private FileUploadedMessagePublisher publisher;

    /**
     * event listener
     *
     * @param fileUploadEvent FileUploadEvent
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void doPublishOnKafka(FileUploadEvent fileUploadEvent) {
        publisher.publish(fileUploadEvent.getFileObject());
    }
}
