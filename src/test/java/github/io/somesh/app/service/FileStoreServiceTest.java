package github.io.somesh.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import github.io.somesh.app.dto.FileStoreDto;
import github.io.somesh.app.service.messaging.FileStatusMessageEvent;
import github.io.somesh.app.service.messaging.FileUploadedMessagePublisher;
import github.io.somesh.app.shared.exception.FileUploadException;
import github.io.somesh.app.shared.exception.ResourceNotFoundException;
import github.io.somesh.domain.model.FileStore;
import github.io.somesh.domain.model.FileUploadedStatus;
import github.io.somesh.domain.repo.FileStoreRepository;

/**
 * FileStoreService Test class.
 * 
 * @author sombose
 */
@ExtendWith(MockitoExtension.class)
public class FileStoreServiceTest {

  @InjectMocks
  private FileStoreServiceImpl service;

  @Mock
  private FileStoreRepository fileStoreRepo;

  @Mock
  private FileUploadedMessagePublisher publisher;

  private final String FILE_CONTENT = "ASA";
  private final String FILE_TYPE_CODE = "DRM";
  private final String FILE_NAME = "Example.txt";

  @Test
  public void testSaveEntity() {

    FileStoreDto fileDto =
        FileStoreDto.builder().fileTypeCode(FILE_TYPE_CODE).fileContent(FILE_CONTENT).fileName(FILE_NAME).build();
    service.saveFile(fileDto);

    Mockito.verify(fileStoreRepo).save(Mockito.any(FileStore.class));
    Mockito.verify(publisher).publish(Mockito.any(FileStore.class));

  }

  @Test
  public void testSaveEntityWithIncorrectDto() {

    FileStoreDto fileDto = FileStoreDto.builder().build();
    assertThrows(FileUploadException.class, () -> {
      service.saveFile(fileDto);
    });

    Mockito.verify(fileStoreRepo, Mockito.times(0)).save(Mockito.any(FileStore.class));
    Mockito.verify(publisher, Mockito.times(0)).publish(Mockito.any(FileStore.class));

  }

  @Test
  public void testUpdateStatusMethod() {
    FileStatusMessageEvent event =
        FileStatusMessageEvent.builder().fileLocation("1234").status(FileUploadedStatus.IN_PROGRESS).build();
    FileStore entity = new FileStore.Builder().fileName("Sample.txt").fileTypeCode("DRM").build();
    Mockito.when(fileStoreRepo.findByFileReferenceId("1234")).thenReturn(Optional.of(entity));
    service.updateFileStatus(event);
    Mockito.verify(fileStoreRepo).save(entity);
  }

  @Test
  public void testGetFileMethod() {
    Mockito.when(fileStoreRepo.findByFileReferenceId("1234")).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class, () -> {
      service.getFile("1234");
    });
  }

  @Test
  public void testGetFileMethod2() {
    FileStore store = new FileStore.Builder().fileName("Sample.txt").fileTypeCode("DRM").build();
    Mockito.when(fileStoreRepo.findByFileReferenceId("1234")).thenReturn(Optional.of(store));
    FileStore response = service.getFile("1234");
    assertEquals(response.getFileName(), store.getFileName());
  }
}
