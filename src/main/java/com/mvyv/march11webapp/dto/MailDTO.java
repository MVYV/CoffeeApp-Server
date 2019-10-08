package com.mvyv.march11webapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDTO {
  private Long mailTo;
  private String mailToAddress;
  private String mailText;
  private String mailSubject;
}
