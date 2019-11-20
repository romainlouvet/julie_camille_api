package fr.mobileagri.julie_camille_api.service;

import fr.mobileagri.julie_camille_api.entity.Message;
import fr.mobileagri.julie_camille_api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  public List<Message> list() {
    return messageRepository.findAll();
  }

  public Message create(Message message) {
    //On insère que les messages avec une ip non existante en bdd
    if (!messageRepository.existsByIp(message.getIp())) {
      return messageRepository.save(message);
    }
    return null;
  }

  public Optional<Message> findById(Long id) {
    return messageRepository.findById(id);
  }
}
