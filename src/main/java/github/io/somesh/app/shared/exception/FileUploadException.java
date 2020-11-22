package github.io.somesh.app.shared.exception;

/**
 * FileUploadException class.
 * 
 * @author sombose
 */
public class FileUploadException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor for FileUploadException.
   * 
   * @param message String.
   */
  public FileUploadException(final String message) {
    super(message);
  }
}
