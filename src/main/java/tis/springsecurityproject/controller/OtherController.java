package tis.springsecurityproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class OtherController {

  @GetMapping
  public String admin() {
    return "admin";
  }


  @GetMapping("hello")
  public String hello() {
    return "hello";
  }
}
