package github.io.somesh.app.service.messaging;

import java.time.Instant;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import github.io.somesh.app.shared.MessageEvent;
import github.io.somesh.domain.model.FileUploadedStatus;
import github.io.somesh.infra.messaging.InstantSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FileStatusMessageEvent event.
 * 
 * @author sombose
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileStatusMessageEvent implements MessageEvent {

  private String eventName = FileStatusMessageEvent.class.getSimpleName();

  @JsonSerialize(using = InstantSerializer.class)
  private Instant eventDate;

  private String fileLocation;

  private String fileName;

  private String fileTypeCode;

  @Enumerated(EnumType.STRING)
  private FileUploadedStatus status;

  private String serviceName;
}
