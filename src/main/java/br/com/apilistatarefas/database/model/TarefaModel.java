package br.com.apilistatarefas.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.Random;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaModel {
    private int id;
    private String titulo;
    private Optional<String> descricao;
    private TarefaStatus status;
}
