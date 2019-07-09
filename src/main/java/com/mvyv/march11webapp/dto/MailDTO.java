package com.mvyv.march11webapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDTO {
  private String mailTo;
  private String mailText;
  private String mailSubject;
}
