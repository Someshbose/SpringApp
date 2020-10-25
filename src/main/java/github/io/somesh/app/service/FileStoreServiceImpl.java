package github.io.somesh.app.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import github.io.somesh.app.dto.FileStoreDto;
import github.io.somesh.domain.model.FileStore;
import github.io.somesh.domain.repo.FileStoreRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation class for FileStoreService.
 * 
 * @author sombose
 */
@Service
@Slf4j
public class FileStoreServiceImpl implements FileStoreService {

  private FileStoreRepository repository;

  /**
   * Constructor for FileStoreRepository.
   * 
   * @param repository FileStoreRepository
   */
  public FileStoreServiceImpl(FileStoreRepository repository) {
    this.repository = repository;
  }

  @Override
  public String saveFile(FileStoreDto dto) {
    validatePayload(dto);
    FileStore entity = createEntityFromDto(dto);
    repository.save(entity);
    return entity.getFileReferenceId();
  }

  /**
   * Generate Entity from dto.
   * 
   * @param dto FileStoreDto
   * @return FileStore
   */
  private FileStore createEntityFromDto(FileStoreDto dto) {
    // TODO Auto-generated method stub
    String fileExtension = getFileExtension(dto.getFileName());
    FileStore entity = null;
    try {
      entity = new FileStore.Builder().charSet(dto.getCharSet())
          .fileContent(new String(Base64.decodeBase64(dto.getFileContent()),
              dto.getCharSet() == null ? "UTF-8" : dto.getCharSet()))
          .fileReferenceId().fieldSeparator(dto.getFieldSeparator()).fileName(dto.getFileName())
          .fileExtension(fileExtension).correlationId(dto.getCorrelationId()).submitterEmail(dto.getSubmitterEmail())
          .fileTypeCode(dto.getFileTypeCode()).build();
    } catch (UnsupportedEncodingException e) {
      log.error("Unable to create file out of payload");
      throw new FileUploadException("File creation error!");
    }
    return entity;
  }

  /**
   * Validation of Payload.
   * 
   * @param fs FileStoreDto
   */
  private void validatePayload(FileStoreDto fs) {
    if (fs.getFileContent() == null) {
      throw new FileUploadException("No File Content Found!");
    }
    if (fs.getFileName() == null) {
      throw new FileUploadException("File Name is Null!");
    }
    String fileExtension = getFileExtension(fs.getFileName());
    if (fileExtension == null) {
      throw new FileUploadException("File extension not found!");
    }
  }

  /**
   * method for getting fileExtension.
   * 
   * @param fileName String
   * @return String
   */
  private String getFileExtension(String fileName) {
    if (fileName.lastIndexOf('.') != -1) {
      return fileName.substring(fileName.lastIndexOf('.'));
    } else {
      return null;
    }
  }

  @Override
  public Optional<FileStore> getFile(String fileRefId) {
    return repository.findByFileReferenceId(fileRefId);
  }
}