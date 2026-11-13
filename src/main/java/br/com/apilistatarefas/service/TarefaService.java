package br.com.apilistatarefas.service;

import br.com.apilistatarefas.DTO.TarefaDTO;
import br.com.apilistatarefas.DTO.TarefaDTOCompleta;
import br.com.apilistatarefas.database.model.TarefaModel;
import br.com.apilistatarefas.database.model.TarefaStatus;
import br.com.apilistatarefas.database.repository.TarefaLocalRepository;
import br.com.apilistatarefas.exceptions.ExcecaoTarefaCampoInvalido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        if(tarefaDTO.titulo() == null) {
            throw new ExcecaoTarefaCampoInvalido();
        }
        String desrcicao = "";
        if(tarefaDTO.descricao() != null) {
            desrcicao = tarefaDTO.descricao();
        }
        return repository.criarTarefa(new TarefaDTO(tarefaDTO.titulo(), desrcicao));
    }

    public TarefaModel atualizarTarefa(int id, TarefaDTO tarefaDTO) {
        TarefaModel tarefaAntiga = repository.pegarTarefa(id);
        String titulo = tarefaAntiga.getTitulo(), descricao = tarefaAntiga.getDescricao().orElse("");

        if(tarefaDTO.titulo() != null) {
            titulo = tarefaDTO.titulo();
        }

        if(tarefaDTO.descricao() != null) {
            descricao = tarefaDTO.descricao();
        }
        return repository.atualizarTarefa(id, new TarefaDTO(titulo, descricao));
    }

    public void deletarTarefa(int id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id invalido");
        }
        repository.deletarTarefa(id);
    }

    public TarefaModel atualizarTituloTarefa(int id, TarefaDTOCompleta tarefaDTO) {
        if(tarefaDTO.titulo() == null) throw new ExcecaoTarefaCampoInvalido();
        return repository.atualizarTituloTarefa(id, tarefaDTO);
    }

    public TarefaModel atualizarDescricaoTarefa(int id, TarefaDTOCompleta tarefaDTO) {
        if(tarefaDTO.descricao() == null) throw new ExcecaoTarefaCampoInvalido("O campo descricao deve ser preenchido");
        return repository.atualizarTarefaDescricao(id, tarefaDTO);
    }

    public TarefaModel atualizarStatusTarefa(int id, TarefaDTOCompleta tarefaDTO) {
        if(tarefaDTO.status() == null) throw new ExcecaoTarefaCampoInvalido("O campo status deve ser preenchido.");
        if(!Arrays.stream(TarefaStatus.values()).anyMatch(status -> status.name().equals(tarefaDTO.status()))) {
            throw new ExcecaoTarefaCampoInvalido("O campo status do corpo não corresponde ao valores definidos. Campos definidos: PENDENTE e CONCLUIDA");
        }
        return repository.atualizarTarefaStatus(id, tarefaDTO);
    }
}
