package github.io.somesh.domain.repo;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import github.io.somesh.domain.model.FileStore;

/**
 * Repository class for FileStore.
 * 
 * @author sombose
 */
@Repository
public interface FileStoreRepository extends CrudRepository<FileStore, Long> {

  /**
   * find By File Content by fileReference Id.
   * 
   * @param fileRefId String
   * @return Optional<FileStore>
   */
  Optional<FileStore> findByFileReferenceId(String fileRefId);

}
