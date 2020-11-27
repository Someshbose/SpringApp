package github.io.somesh.app.web;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import github.io.somesh.app.dto.FileStoreDto;
import github.io.somesh.app.service.FileStoreService;
import github.io.somesh.domain.model.FileStore;

/**
 * FileStoreController class.
 * 
 * @author sombose
 */
@RestController
public class FileStoreController {

  private FileStoreService service;

  /**
   * constructor for FileStoreController.
   * 
   * @param service FileStoreService
   */
  public FileStoreController(FileStoreService service) {
    this.service = service;
  }

  /**
   * FileUpload Method.
   * 
   * @param dto FileStoreDto
   * @return ResponseEntity
   */
  @PostMapping("/filestore")
  public ResponseEntity upload(@RequestBody final FileStoreDto dto) {
    URI url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(service.saveFile(dto))
        .toUri();
    return ResponseEntity.created(url).build();
  }

  /**
   * getFileContent Method.
   * 
   * @param id String
   * @return {@link ResponseEntity}
   */
  @GetMapping("/findbyId/{id}")
  public ResponseEntity getFile(@PathVariable String id) {
      FileStore response = service.getFile(id);
      return ResponseEntity.ok(response);
  }
}
