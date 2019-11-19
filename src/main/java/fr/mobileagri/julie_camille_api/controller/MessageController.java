package fr.mobileagri.julie_camille_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @RequestMapping(value = "/getDateAndTime")
  public String getDateAndTime() {

    //

    return "Coucou";
  }
}
