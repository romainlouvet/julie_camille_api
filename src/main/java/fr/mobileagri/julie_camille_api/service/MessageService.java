package fr.mobileagri.julie_camille_api.service;

import fr.mobileagri.julie_camille_api.entity.Message;
import fr.mobileagri.julie_camille_api.repository.MessageRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

  public static final String INDEX_MESSAGE = "julie_camille_messages";

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private ElasticSearchService elasticSearchService;

  public List<Message> list() {
    return messageRepository.findAllByOrderByDateDesc();
  }

  public Message create(Message message) {
    //On insère que les messages avec une ip non existante en bdd
    // if (!messageRepository.existsByIp(message.getIp())) {
    sendToEs(message);
    messageRepository.save(message);
    return message;
    //}
    //return null;
  }

  public Optional<Message> findById(Long id) {
    return messageRepository.findById(id);
  }

  public void deleteById(Long id) {
    Message message = findById(id).get();
    messageRepository.delete(message);
  }

  private void sendToEs(Message message) {
    SimpleDateFormat indexFormatES = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    DateTime dateTime = new DateTime(new Date());
    message.setDate(indexFormatES.format(dateTime.minusHours(1).toDate()));

    elasticSearchService.send(INDEX_MESSAGE, message);
  }
}
