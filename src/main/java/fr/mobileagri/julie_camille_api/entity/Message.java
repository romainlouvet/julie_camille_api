package fr.mobileagri.julie_camille_api.entity;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String text;
  @NotBlank
  private String admin;
  private String ip;
  private String date;
  private Date dateCreated;
  private Boolean isValid;

  public Message() {
  }

  @PrePersist
  protected void onCreate() {
    dateCreated = DateTime.now().toDate();
    isValid = Boolean.FALSE;
  }

}
