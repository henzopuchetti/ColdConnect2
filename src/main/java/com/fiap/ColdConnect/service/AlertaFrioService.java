package com.fiap.ColdConnect.service;

import com.fiap.ColdConnect.model.enums.GrauAlerta;
import com.fiap.ColdConnect.model.AlertaFrio;
import com.fiap.ColdConnect.repository.AlertaFrioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlertaFrioService {

    private final AlertaFrioRepository repository;

    public AlertaFrio salvar(AlertaFrio alerta) {
        return repository.save(alerta);
    }

    public List<AlertaFrio> listarTodos() {
        return repository.findAll();
    }

    public Optional<AlertaFrio> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public AlertaFrio atualizar(Long id, AlertaFrio alertaAtualizado) {
        return repository.findById(id)
                .map(alerta -> {
                    alerta.setGrau(alertaAtualizado.getGrau());
                    alerta.setRegiao(alertaAtualizado.getRegiao());
                    return repository.save(alerta);
                })
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public GrauAlerta calcularGrau(Double temperatura) {
        if (temperatura < 0) return GrauAlerta.GRAVE;
        else if (temperatura < 5) return GrauAlerta.MODERADO;
        else if (temperatura < 10) return GrauAlerta.LEVE;
        else return null;
    }

    public List<AlertaFrio> buscarPorRegiao(String regiao) {
    return repository.findByRegiaoIgnoreCase(regiao);
}
}