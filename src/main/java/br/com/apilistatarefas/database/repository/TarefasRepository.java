package br.com.apilistatarefas.database.repository;

import br.com.apilistatarefas.DTO.TarefaDTO;
import br.com.apilistatarefas.database.model.TarefaModel;

import java.util.List;

public interface TarefasRepository {

    public List<TarefaModel> listaTarefa();

    public TarefaModel pegarTarefa(int id);

    public TarefaModel criarTarefa(TarefaDTO tarefa);
    public TarefaModel atualizarTarefa(int id, TarefaDTO tarefa);

    public void deletarTarefa(int id);

    public TarefaModel atualizarTituloTarefa(int id, TarefaDTO tarefa);

    public TarefaModel atualizarDescricaoTarefa(int id, TarefaDTO tarefa);
}
