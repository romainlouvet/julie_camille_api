package fr.mobileagri.julie_camille_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @RequestMapping("/getDateAndTime")
  public String getDateAndTime() {

    //

    return "Coucou";
  }
}
