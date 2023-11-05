package com.mercado.quincho.controller;

import com.mercado.quincho.request.RegisterQuinchoRequest;
import com.mercado.quincho.response.QuinchoResponse;
import com.mercado.quincho.service.QuinchoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author QuinSDev
 */
@RestController
@RequestMapping("/quincho")
@RequiredArgsConstructor
public class QuinchoController {
    
    @Autowired
    private QuinchoService quinchoService;
    
    @PostMapping(value = "register")
    public ResponseEntity<QuinchoResponse> registerQuincho(@RequestBody RegisterQuinchoRequest
            request) {
        return ResponseEntity.ok(quinchoService.registerQuincho(request));
    }
    
}
