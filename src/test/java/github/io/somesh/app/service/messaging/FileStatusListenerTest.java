package github.io.somesh.app.service.messaging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import github.io.somesh.app.service.FileStoreService;

/**
 * Junit class for FileStatusListener.
 * 
 * @author sombose
 */
@ExtendWith(MockitoExtension.class)
public class FileStatusListenerTest {

  @InjectMocks
  private FileStatusMessageEventListener listener;

  @Mock
  private FileStoreService service;

  @Test
  public void name() {
    FileStatusMessageEvent event = FileStatusMessageEvent.builder().build();
    listener.consumeMessage(event);
    Mockito.verify(service).updateFileStatus(event);
  }
}
