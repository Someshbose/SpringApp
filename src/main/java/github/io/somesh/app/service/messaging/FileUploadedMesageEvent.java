package github.io.somesh.app.service.messaging;

import java.time.Instant;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import github.io.somesh.app.shared.MessageEvent;
import github.io.somesh.infra.messaging.InstantSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FileUploadedMesageEvent event.
 * 
 * @author sombose
 *
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public final class FileUploadedMesageEvent implements MessageEvent {

  private String eventName = FileUploadedMesageEvent.class.getSimpleName();

  @JsonSerialize(using = InstantSerializer.class)
  private Instant eventDate;

  private String fileLocation;

  private String fileName;

  private String fileTypeCode;

  private String uploadedBy;

}
