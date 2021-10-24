package com.app.facade.imp;

import com.app.facade.IDecrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DecryptImpl implements IDecrypt {


  private final String evalartappURL = "https://test.evalartapp.com/";


  private final String decryptURL = "extapiquest/code_decrypt/";

  private RestTemplate restTemplate;

  public DecryptImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String decrypt(String field) {
    //delete double comillas in response
    return restTemplate.getForObject(this.buildDecryptURL(field), String.class).replaceAll("^\"|\"$", "");
  }

  private String buildDecryptURL(String field) {
    return evalartappURL.concat(decryptURL).concat(field);
  }


}
