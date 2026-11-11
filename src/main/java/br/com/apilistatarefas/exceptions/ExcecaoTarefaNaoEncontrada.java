package br.com.apilistatarefas.exceptions;

public class ExcecaoTarefaNaoEncontrada extends RuntimeException {
    public ExcecaoTarefaNaoEncontrada(int id) {
        super("A tarefa de id " + id + " não encontrada");
    }
}
