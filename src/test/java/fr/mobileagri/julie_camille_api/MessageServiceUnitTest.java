package fr.mobileagri.julie_camille_api;

import fr.mobileagri.julie_camille_api.entity.Message;
import fr.mobileagri.julie_camille_api.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceUnitTest {

  @Autowired
  private MessageService messageService;

  @Test
  public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
    List<Message> messages = messageService.list();

    //Assert.assertEquals(books.size(), 3);
  }
}
