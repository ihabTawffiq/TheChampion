package com.example.theChampion.data.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ResponseWrapper<T> {
    private T body;
    private String message;
    private Boolean success;
}
