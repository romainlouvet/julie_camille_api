package fr.mobileagri.julie_camille_api;

import fr.mobileagri.julie_camille_api.entity.Message;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = JulieCamilleApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JulieCamilleApiApplicationTests {


  @Autowired
  private TestRestTemplate restTemplate;


  @LocalServerPort
  private int port;

  private String getRootUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void contextLoads() {
  }


  @Test
  public void should_get_all_messages() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/messages",
        HttpMethod.GET, entity, String.class);
    Assert.assertNotNull(response.getBody());
  }

  @Test
  @Ignore
  public void should_get_message_by_id() {
//      when(messageRepository.findById(any())).thenReturn(Optional.empty());
//    Message message = restTemplate.getForObject(getRootUrl() + "/message/1", Message.class);
//    Assert.assertNotNull(message);
  }


  @Test
  public void should_create_message() {
    Message message = new Message();
    message.setAdmin("adminFromTest");
    message.setText("Text from test");
    ResponseEntity<Message> postResponse = restTemplate.postForEntity(getRootUrl() + "/message", message, Message.class);
    Assert.assertNotNull(postResponse);
  }

}
