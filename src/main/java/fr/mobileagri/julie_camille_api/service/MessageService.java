package fr.mobileagri.julie_camille_api.service;

import com.google.gson.Gson;
import fr.mobileagri.julie_camille_api.entity.Message;
import fr.mobileagri.julie_camille_api.repository.MessageRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

  private static final String INDEX_MESSAGE = "julie_camille_messages";
  private static final int NB_MAX_REVIEW_BY_DAY = 50;
  private static Logger logger = LoggerFactory.getLogger(MessageService.class);

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private ElasticSearchService elasticSearchService;

  public List<Message> list() {
    return messageRepository.findAllByIsValidTrueOrderByDateDesc();
  }

  public Message create(Message message) {
    //On insère que les messages avec une ip non existante en bdd
    // if (!messageRepository.existsByIp(message.getIp())) {
    Date dateBefore = new Date();
    Date dateAfter = new Date();

    dateBefore.setHours(0);
    dateBefore.setMinutes(0);

    dateAfter.setHours(23);
    dateAfter.setMinutes(59);

    int nbMessageToReview = messageRepository.countMessagesByDateCreatedBetweenAndIsValidFalse(dateBefore, dateAfter);

    if (nbMessageToReview > NB_MAX_REVIEW_BY_DAY) {
      logger.error("il y a trop de message à review ->" + new Gson().toJson(message));
      throw new IllegalStateException("Il y a trop de message à review");
    }


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
