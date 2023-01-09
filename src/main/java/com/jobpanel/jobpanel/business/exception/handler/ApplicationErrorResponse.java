package com.jobpanel.jobpanel.business.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ApplicationErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
