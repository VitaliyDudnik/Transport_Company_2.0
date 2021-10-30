package com.example.transport_company.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String timestamp;
    private StringBuffer request_URL;
    private String message;
    private String error;
}
