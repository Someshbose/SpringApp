package github.io.somesh.app.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.io.somesh.app.dto.FileStoreDto;
import github.io.somesh.app.service.FileStoreService;
import github.io.somesh.domain.model.FileStore;

/**
 * Test class for FileStoreController.
 * 
 * @author sombose
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FileStoreController.class)
public class FileStoreControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private FileStoreService service;

  private FileStore fileStore;

  private final String FILE_REF = "1234";
  private final String FILE_TYPE_CODE = "DRM";
  private final String FILE_NAME = "Example.txt";
  
  private HttpHeaders request ;

  @BeforeEach
  public void init() {
    fileStore = new FileStore.Builder().fileTypeCode(FILE_TYPE_CODE).fileName(FILE_NAME).build();
    request=new HttpHeaders();
    request.add("x-corrId", "S-123");
    request.add("x-appCode", "flupldr");
  }

  @Test
  public void testPostController() throws Exception {
    FileStoreDto fileDto = FileStoreDto.builder().fileTypeCode(FILE_TYPE_CODE).build();
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/filestore").headers(request);
    mvc.perform(builder.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(fileDto)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testPostControllerWithNoDto() throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/filestore").headers(request);
    mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void testGetController() throws Exception {
    Mockito.when(service.getFile(FILE_REF)).thenReturn(fileStore);
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/findbyId/" + FILE_REF).headers(request);
    mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }
}
