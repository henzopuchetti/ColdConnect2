package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.dto.AbrigoDTO;
import com.fiap.ColdConnect.model.filter.AbrigoFilter;
import com.fiap.ColdConnect.service.AbrigoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
@RequiredArgsConstructor
public class AbrigoController {

    private final AbrigoService abrigoService;

    // POST - cria abrigo
    @PostMapping
    public ResponseEntity<AbrigoDTO> criar(@RequestBody @Valid AbrigoDTO dto) {
        AbrigoDTO criado = abrigoService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    // GET SEM FILTRO (lista todos)
    @GetMapping
    public ResponseEntity<List<AbrigoDTO>> listarTodos() {
        return ResponseEntity.ok(abrigoService.listarTodos());
    }

    // GET COM FILTROS, PAGINAÇÃO, ORDENAÇÃO
    @GetMapping("/filtro")
    public ResponseEntity<Page<AbrigoDTO>> listarComFiltros(
            AbrigoFilter filtro,
            Pageable pageable
    ) {
        return ResponseEntity.ok(abrigoService.listarComFiltros(filtro, pageable));
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<AbrigoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(abrigoService.buscarPorId(id));
    }

    // PUT - atualiza
    @PutMapping("/{id}")
    public ResponseEntity<AbrigoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AbrigoDTO dto) {
        return ResponseEntity.ok(abrigoService.atualizar(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        abrigoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
