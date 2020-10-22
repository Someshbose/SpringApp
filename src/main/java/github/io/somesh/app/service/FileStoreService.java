package github.io.somesh.app.service;

import java.util.Optional;
import github.io.somesh.app.dto.FileStoreDto;
import github.io.somesh.domain.model.FileStore;

/**
 * FileStoreService interface.
 * 
 * @author iamso
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
  Optional<FileStore> getFile(String fileRefId);
}
