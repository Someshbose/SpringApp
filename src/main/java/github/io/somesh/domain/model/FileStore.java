package github.io.somesh.domain.model;

import java.util.UUID;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.Validate;
import github.io.somesh.domain.shared.AbstractBaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 * @author sombose
 */
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Cacheable(value = false)
@Getter
@ToString
@Table(name = "CORE_FILE_STORE",
    indexes = {@Index(name = "CORE_FILE_STORE_U1", columnList = "FILE_STORE_ID", unique = true),
        @Index(name = "CORE_FILE_STORE_U2", columnList = "FILE_REFERENCE_ID", unique = true)})
public class FileStore extends AbstractBaseEntity<FileStore> {

  @Column(name = "FILE_STORE_ID", nullable = false)
  @SequenceGenerator(name = "CORE_FILE_STORE_SEQ", sequenceName = "CORE_FILE_STORE_SEQ", allocationSize = 100)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CORE_FILE_STORE_SEQ")
  @Id
  private Long id;

  @Column(name = "FILE_REFERENCE_ID", nullable = false, length = 240)
  private String fileReferenceId;

  @Column(name = "FILE_TYPE_CODE", nullable = false, length = 240)
  private String fileTypeCode;

  @Column(name = "FILE_NAME", nullable = false, length = 240)
  private String fileName;

  @Column(name = "CHAR_SET", nullable = false, length = 100)
  private String charSet;

  @Column(name = "SUBMITTER_EMAIL", nullable = false, length = 240)
  private String submitterEmail;

  @Column(name = "FILE_CONTENT", nullable = false)
  @Lob
  private String fileContent;

  @Column(name = "STATUS", length = 240)
  @Enumerated(EnumType.STRING)
  private FileUploadedStatus status;

  @Column(name = "CORRELATION_ID", length = 240, unique = true)
  private String correlationId;

  @Column(name = "FILE_EXTENSION", length = 100)
  private String fileExtension;

  @Column(name = "FIELD_SEPARATOR", length = 100)
  private String fieldSeparator;

  /**
   * Default constructor;
   * 
   */
  protected FileStore() {

  }

  /**
   * private constructor for FileStore.
   * 
   * @param builder Builder.
   */
  private FileStore(Builder builder) {
    this.charSet = builder.charSet;
    this.correlationId = builder.correlationId;
    this.fileName = builder.fileName;
    this.submitterEmail = builder.submitterEmail;
    this.fileContent = builder.fileContent;
    this.status = builder.status;
    this.fileReferenceId = builder.fileReferenceId;
    this.fileTypeCode = builder.fileTypeCode;
    this.fileExtension = builder.fileExtension;
    this.fieldSeparator = builder.fieldSeparator;
  }

  @SuppressWarnings("JavadocMethod")
  public static class Builder {

    private String fileReferenceId;
    private String fileTypeCode;
    private String fileName;
    private String charSet;
    private String submitterEmail;
    private String fileContent;
    private FileUploadedStatus status;
    private String correlationId;
    private String fileExtension;
    private String fieldSeparator;

    public Builder fileReferenceId() {
      this.fileReferenceId = UUID.randomUUID().toString().replaceAll("-", "");
      return this;
    }

    public Builder fileTypeCode(String fileTypeCode) {
      this.fileTypeCode = fileTypeCode;
      return this;
    }

    public Builder fileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    public Builder charSet(String charSet) {
      this.charSet = charSet;
      return this;
    }

    public Builder submitterEmail(String submitterEmail) {
      this.submitterEmail = submitterEmail;
      return this;
    }

    public Builder fileContent(String fileContent) {
      this.fileContent = fileContent;
      return this;
    }

    public Builder status(FileUploadedStatus status) {
      this.status = status;
      return this;
    }

    public Builder correlationId(String correlationId) {
      this.correlationId = correlationId;
      return this;
    }

    public Builder fileExtension(String fileExtension) {
      this.fileExtension = fileExtension;
      return this;
    }

    public Builder fieldSeparator(String fieldSeparator) {
      this.fieldSeparator = fieldSeparator;
      return this;
    }

    private void validate() {
      //Validate.notBlank(fileReferenceId, "file ref must not be blank");
      Validate.notBlank(fileTypeCode, "file Type must not be blacnk");
    }

    public FileStore build() {
      validate();
      return new FileStore(this);
    }

  }

  /**
   * Update the status of FileStore Entity.
   * 
   * @param status FileUploadedStatus
   */
  public void updateStatus(FileUploadedStatus status) {
    this.status = status;
  }
}
