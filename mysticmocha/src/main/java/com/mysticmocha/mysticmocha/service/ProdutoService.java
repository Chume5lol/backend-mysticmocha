package com.mysticmocha.mysticmocha.service;

import com.mysticmocha.mysticmocha.domain.Produto;
import com.mysticmocha.mysticmocha.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Cacheable(value = "produtos")
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // BUSCAR POR ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // CRIAR
    public Produto criar(Produto produto) {
        Produto novoProduto = new Produto();

        novoProduto.setNome(produto.getNome());
        novoProduto.setDescricao(produto.getDescricao());
        novoProduto.setCodigoProduto(produto.getCodigoProduto());
        novoProduto.setPreco(produto.getPreco());
        novoProduto.setQuantidade(produto.getQuantidade());
        novoProduto.setAtivo(true);

        return produtoRepository.save(novoProduto);
    }

    @CacheEvict(value = "produtos", allEntries = true)
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto existente = buscarPorId(id);

        existente.setNome(produtoAtualizado.getNome());
        existente.setDescricao(produtoAtualizado.getDescricao());
        existente.setPreco(produtoAtualizado.getPreco());
        existente.setQuantidade(produtoAtualizado.getQuantidade());
        existente.setAtivo(produtoAtualizado.getAtivo());

        return produtoRepository.save(existente);
    }

    // DELETAR 
    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    // ADICIONAR QUANTIDADE
    public Produto adicionarQuantidade(Long id, Integer quantidade) {
        if (quantidade <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero.");
        }

        Produto produto = buscarPorId(id);
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        return produtoRepository.save(produto);
    }

    // REMOVER QUANTIDADE
    public Produto removerQuantidade(Long id, Integer quantidade) {
        if (quantidade <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero.");
        }

        Produto produto = buscarPorId(id);

        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente para remover essa quantidade.");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return produtoRepository.save(produto);
    }
}
