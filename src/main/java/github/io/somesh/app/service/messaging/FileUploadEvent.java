package github.io.somesh.app.service.messaging;

import github.io.somesh.domain.model.FileStore;
import org.springframework.context.ApplicationEvent;

public class FileUploadEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */

    private FileStore fileObject;

    /**
     * constructor
     * @param source sample
     * @param fileObject file
     */
    public FileUploadEvent(Object source, FileStore fileObject) {
        super(source);
        this.fileObject = fileObject;
    }

    /**
     *
     * @return FileStore
     */
    public FileStore getFileObject() {
        return fileObject;
    }
}
