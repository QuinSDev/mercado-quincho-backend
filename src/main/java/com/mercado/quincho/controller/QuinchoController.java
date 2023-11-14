package com.mercado.quincho.controller;

import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.request.RegisterQuinchoRequest;
import com.mercado.quincho.response.QuinchoResponse;
import com.mercado.quincho.service.QuinchoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<QuinchoResponse> registerQuincho(RegisterQuinchoRequest
            request) {
        try {
            quinchoService.validQuincho(request);
        return ResponseEntity.ok(quinchoService.registerQuincho(request));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(new QuinchoResponse("Error al registrar el quincho: " + e.getMessage()));
    }
    }
    
    @GetMapping("/lista-quinchos")
    public ResponseEntity<List<Quincho>> getListaQuinchos() {
        List<Quincho> quinchos = quinchoService.listQuincho();
        return new ResponseEntity<>(quinchos, HttpStatus.OK);
    }
    
}
