package br.com.esteroliver.auth.c_infra.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.esteroliver.auth.b_application.dto.UsuarioPostDTO;
import br.com.esteroliver.auth.b_application.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("")
    public ResponseEntity<Void> criar(@RequestBody UsuarioPostDTO dto) {
        try{
            usuarioService.criar(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(BadRequestException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
