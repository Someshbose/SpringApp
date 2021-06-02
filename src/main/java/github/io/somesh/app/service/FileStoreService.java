package github.io.somesh.app.service;

import github.io.somesh.app.dto.FileStoreDto;
import github.io.somesh.domain.model.FileStore;
import github.io.somesh.domain.model.FileUploadedStatus;
import org.springframework.http.ResponseEntity;

/**
 * FileStoreService interface.
 * 
 * @author sombose
 */
public interface FileStoreService {

  /**
   * method for saving content.
   * 
   * @param dto FileStoreDto
   * @return String
   */
  ResponseEntity saveFile(FileStoreDto dto);

  /**
   * method for fetching file.
   * 
   * @param fileRefId String.
   * @return FileStore
   */
  FileStore getFile(String fileRefId);

  /**
   * update FileStore Status.
   * 
   * @param fileReference FileStatusMessageEvent
   * @param status FileStatus
   */
  void updateFileStatus(String fileReference, FileUploadedStatus status);
}
