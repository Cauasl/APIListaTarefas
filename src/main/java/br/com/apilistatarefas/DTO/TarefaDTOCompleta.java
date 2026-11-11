package br.com.apilistatarefas.DTO;

import br.com.apilistatarefas.database.model.TarefaStatus;

public record TarefaDTOCompleta(String titulo, String descricao, TarefaStatus status) {
}
