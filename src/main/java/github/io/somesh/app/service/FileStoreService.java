package github.io.somesh.app.service;

import github.io.somesh.app.dto.FileStoreDto;
import github.io.somesh.app.service.messaging.FileStatusMessageEvent;
import github.io.somesh.domain.model.FileStore;

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
  String saveFile(FileStoreDto dto);

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
   * @param messageEvent FileStatusMessageEvent
   */
  void updateFileStatus(FileStatusMessageEvent messageEvent);
}
