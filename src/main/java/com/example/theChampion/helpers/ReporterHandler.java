package com.example.theChampion.helpers;


import com.example.theChampion.data.responses.ResponseWrapper;
import org.springframework.http.ResponseEntity;


public class ReporterHandler<T> {

    public ResponseEntity<ResponseWrapper<T>> reportSuccess(ResponseWrapper body){
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<ResponseWrapper<T>> reportFailure(ResponseWrapper body){
        return ResponseEntity.badRequest().body(body);
    }
}
