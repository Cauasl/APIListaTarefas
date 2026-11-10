package br.com.apilistatarefas.service;

import br.com.apilistatarefas.DTO.TarefaDTO;
import br.com.apilistatarefas.database.model.TarefaModel;
import br.com.apilistatarefas.database.repository.TarefaLocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaLocalRepository repository;

    public List<TarefaModel> listarTarefas() {
        return repository.listaTarefa();
    }

    public TarefaModel buscarTarefaPorId(int id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id invalido");
        }
        return repository.pegarTarefa(id);
    }

    public TarefaModel criarTarefa(TarefaDTO tarefaDTO) {
        return repository.criarTarefa(tarefaDTO);
    }

    public TarefaModel atualizarTarefa(int id, TarefaDTO tarefaDTO) {
        return repository.atualizarTarefa(id, tarefaDTO);
    }

    public void deletarTarefa(int id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id invalido");
        }
        repository.deletarTarefa(id);
    }
}
