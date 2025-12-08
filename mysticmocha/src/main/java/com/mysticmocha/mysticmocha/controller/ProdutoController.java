package com.mysticmocha.mysticmocha.controller;

import com.mysticmocha.mysticmocha.domain.Produto;
import com.mysticmocha.mysticmocha.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.criar(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.atualizar(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/adicionar")
    public ResponseEntity<Produto> adicionarQuantidade(
            @PathVariable Long id,
            @RequestParam Integer quantidade
    ) {
        return ResponseEntity.ok(produtoService.adicionarQuantidade(id, quantidade));
    }

    @PostMapping("/{id}/remover")
    public ResponseEntity<Produto> removerQuantidade(
            @PathVariable Long id,
            @RequestParam Integer quantidade
    ) {
        return ResponseEntity.ok(produtoService.removerQuantidade(id, quantidade));
    }
}
