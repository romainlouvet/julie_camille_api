package fr.mobileagri.julie_camille_api.controller;

import fr.mobileagri.julie_camille_api.entity.Message;
import fr.mobileagri.julie_camille_api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

  @Autowired
  private MessageService messageService;

  @RequestMapping("/messages")
  public List<Message> getMessages() {
    return messageService.list();
  }

  @RequestMapping("/create")
  public void createMessage() {
    Message message = new Message("t" + UUID.randomUUID().toString(), "toto");
    messageService.create(message);
  }
}
