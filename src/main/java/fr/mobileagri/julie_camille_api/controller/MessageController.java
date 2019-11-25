package fr.mobileagri.julie_camille_api.controller;

import fr.mobileagri.julie_camille_api.entity.Message;
import fr.mobileagri.julie_camille_api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.list();
    }

    /**
     * Gets message by id.
     *
     * @param messageId the message id
     * @return the message by id
     */
    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getUMessageById(@PathVariable(value = "id") Long messageId) {
        Message message = messageService.findById(messageId)
            .orElse(null);

        if (message != null) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete message by id.
     *
     * @param messageId the message id
     */
    @DeleteMapping("/message/{id}")
    public ResponseEntity<Message> deleteUMessageById(@PathVariable(value = "id") Long messageId) {
        Message message = messageService.findById(messageId)
            .orElse(null);

        if (message != null) {
            messageService.deleteById(messageId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create Message message.
     *
     * @param message the message
     * @return the message
     */
    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message, HttpServletRequest request) {
        message.setIp(request.getRemoteAddr());
        Message messageCreated = messageService.create(message);

        if (messageCreated != null) {
            return ResponseEntity.ok().body(messageCreated);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/version")
    public String getVersion() {
        return "1.0";
    }
}
