package com.sdacodecoolproject.usersmanager.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse {

    private Integer statusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;
}
