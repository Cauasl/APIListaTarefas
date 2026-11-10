package br.com.apilistatarefas.database.repository;

import br.com.apilistatarefas.DTO.TarefaDTO;
import br.com.apilistatarefas.database.model.TarefaModel;
import br.com.apilistatarefas.database.model.TarefaStatus;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TarefaLocalRepository implements TarefasRepository {
    private static final List<TarefaModel> listaTarefas = new ArrayList<>();

    static {
        SecureRandom random = new SecureRandom();
        listaTarefas.add(TarefaModel.builder()
                .id(random.nextInt(1_000_000))
                .titulo("Comprar pizza")
                .descricao(Optional.of("Alguma coisa"))
                .status(TarefaStatus.PENDENTE)
                .build());
        listaTarefas.add(TarefaModel.builder()
                .id(random.nextInt(1_000_000))
                .titulo("Lavar roupa")
                .descricao(Optional.of("Roupas"))
                .status(TarefaStatus.PENDENTE)
                .build());
    }

    @Override
    public List<TarefaModel> listaTarefa() {
        return listaTarefas;
    }

    @Override
    public TarefaModel pegarTarefa(int id) {
        return listaTarefas.stream().filter(tarefa -> tarefa.getId() == id).findFirst().orElse(null);
    }

    @Override
    public TarefaModel criarTarefa(TarefaDTO tarefa) {
        SecureRandom random = new SecureRandom();
        int id = random.nextInt(1_000_000);
        TarefaModel novaTerefa = TarefaModel.builder()
                .id(id)
                .titulo(tarefa.titulo())
                .descricao(Optional.ofNullable(tarefa.descricao()))
                .status(TarefaStatus.PENDENTE)
                .build();

        listaTarefas.add(novaTerefa);
        return novaTerefa;
    }

    @Override
    public TarefaModel atualizarTarefa(int id, TarefaDTO tarefa) {
        TarefaModel tarefaAtualizada = listaTarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);


        return null;
    }
}
