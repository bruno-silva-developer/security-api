package com.dev.securityapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.securityapi.model.Produto;
import com.dev.securityapi.model.dto.ProdutoRequestDTO;
import com.dev.securityapi.model.response.ResponseHandler;
import com.dev.securityapi.repository.ProdutoRepository;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<Object> Post(@RequestBody ProdutoRequestDTO data) {

        try {
            Produto produto = new Produto(data.nome(), data.preco());
            produtoRepository.save(produto);
            return ResponseHandler.generateResponse("Produto: " + produto.getNome() + " criado com sucesso",
                    HttpStatus.OK,
                    produto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, data);
        }
    }

    // Mapeamento do método de consulta
    @GetMapping
    public ResponseEntity<Object> Get() {
        try {
            List<Produto> produto = produtoRepository.findAll();
            return ResponseHandler.generateResponse("Sucesso", HttpStatus.OK, produto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Object> Get(@PathVariable Long Id) {
        try {
            Optional<Produto> produto = produtoRepository.findById(Id);
            return ResponseHandler.generateResponse("Sucesso", HttpStatus.OK, produto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
