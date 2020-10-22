package github.io.somesh.app.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FileStoreDto class.
 * 
 * @author sombose
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileStoreDto {

  private String fileTypeCode;
  private String fileName;
  private String charSet;
  private String fileContent;
  private String fieldSeparator;
  private String correlationId;
  private String submitterEmail;
}
