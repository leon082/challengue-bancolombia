package com.app;

import com.app.config.Configuration;
import com.app.service.TableService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(Configuration.class);
    context.refresh();
    TableService service = (TableService) context.getBean("tableService");
    service.runProcess();
  }


}
