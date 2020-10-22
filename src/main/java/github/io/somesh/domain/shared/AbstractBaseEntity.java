package github.io.somesh.domain.shared;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * AbstractBaseEntity class
 * 
 * @author sombose.
 *
 * @param <T>
 */
@Getter
@MappedSuperclass
public class AbstractBaseEntity<T> implements Entity<T> {

  @Column(name = "CREATED_BY", /* nullable = false, */length = 240)
  @CreatedBy
  private String createdBy;

  @Column(name = "UPDATED_BY", /* ,nullable = false, */length = 240)
  @LastModifiedBy
  private String lastUpdatedBy;

  @Column(name = "CREATION_DATE" /* ,nullable = false */)
  @CreatedDate
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss''Z", timezone = "UTC")
  private Instant creationDate;

  @Column(name = "UPDATION_DATE"/* ,nullable = false */)
  @LastModifiedDate
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss''Z", timezone = "UTC")
  private Instant updationDate;
}
