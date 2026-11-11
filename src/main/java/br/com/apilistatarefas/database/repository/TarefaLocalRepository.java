package br.com.apilistatarefas.database.repository;

import br.com.apilistatarefas.DTO.TarefaDTO;
import br.com.apilistatarefas.DTO.TarefaDTOCompleta;
import br.com.apilistatarefas.database.model.TarefaModel;
import br.com.apilistatarefas.database.model.TarefaStatus;
import br.com.apilistatarefas.exceptions.ExcecaoTarefaNaoEncontrada;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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

    public Optional<TarefaModel> procurarTarefa(Predicate<TarefaModel> p) {
        return listaTarefas.stream()
                .filter(p)
                .findFirst();
    }

    @Override
    public List<TarefaModel> listaTarefa() {
        return listaTarefas;
    }

    @Override
    public TarefaModel pegarTarefa(int id) {
        return listaTarefas.stream().filter(tarefa -> tarefa.getId() == id).findFirst().orElseThrow(() -> new ExcecaoTarefaNaoEncontrada(id));
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
        Optional<TarefaModel> tarefaDaLista = procurarTarefa(t -> t.getId() == id);

        if(tarefa.titulo() == null || tarefa.descricao() == null) {
            throw new RuntimeException("Falta campos para atualizar Tarefa");
        }

        tarefaDaLista.get().setTitulo(tarefa.titulo());
        tarefaDaLista.get().setDescricao(Optional.ofNullable(tarefa.descricao()));

        return tarefaDaLista.orElseThrow(() -> new ExcecaoTarefaNaoEncontrada(id));
    }

    @Override
    public void deletarTarefa(int id) {
        listaTarefas.removeIf(tarefa -> tarefa.getId() == id);
    }

    @Override
    public TarefaModel atualizarTituloTarefa(int id, TarefaDTOCompleta tarefa) {
        Optional<TarefaModel> tarefaDaLista = procurarTarefa(t -> t.getId() == id);

        tarefaDaLista.get().setTitulo(tarefa.titulo());
        return tarefaDaLista.orElseThrow(() -> new ExcecaoTarefaNaoEncontrada(id));
    }

    @Override
    public TarefaModel atualizarTarefaDescricao(int id, TarefaDTOCompleta tarefa) {
        Optional<TarefaModel> tarefaDaLista = procurarTarefa(t -> t.getId() == id);

        tarefaDaLista.get().setDescricao(Optional.ofNullable(tarefa.descricao()));
        return tarefaDaLista.orElseThrow(() -> new ExcecaoTarefaNaoEncontrada(id));
    }

    @Override
    public TarefaModel atualizarTarefaStatus(int id, TarefaDTOCompleta tarefa) {
        Optional<TarefaModel> tarefaDaLista = procurarTarefa(t -> t.getId() == id);

        tarefaDaLista.get().setStatus(tarefa.status());
        return tarefaDaLista.orElseThrow(() -> new ExcecaoTarefaNaoEncontrada(id));
    }
}
