package com.example.Ceylon_Mansala_Restaurant_Spring.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ResponseUtil {
    private int statusCode;
    private String message;
    private Object data;
}
