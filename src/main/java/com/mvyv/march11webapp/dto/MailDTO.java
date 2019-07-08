package com.mvyv.march11webapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDTO {
  String mailTo;
  String mailText;
  String mailSubject;
}
