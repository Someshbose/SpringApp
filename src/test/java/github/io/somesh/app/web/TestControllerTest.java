package github.io.somesh.app.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import github.io.somesh.app.message.MessagePublisher;

/**
 * 
 * @author iamso
 *
 */
@WebMvcTest(TestController.class)
@ExtendWith(SpringExtension.class)
public class TestControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private MessagePublisher publisher;

  /**
   * 
   * @throws Exception
   */
  @Test
  public void validateWelcomeResponse() throws Exception {
    // mvc.perform(GET())
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/hello");
    mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());

  }

  /**
   * 
   * @throws Exception
   */
  @Test
  public void inValidResponse() throws Exception {
    // mvc.perform(GET())
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/temp");
    mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNotFound());

  }

  /**
   * 
   * @throws Exception
   */
  @Test
  public void publish() throws Exception {
    // mvc.perform(GET())
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/publish/message");
    mvc.perform(builder);
    Mockito.verify(publisher).publish("message");

  }
}
