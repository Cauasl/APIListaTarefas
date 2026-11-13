package br.com.apilistatarefas.exceptions;

public class ExcecaoTarefaCampoInvalido extends RuntimeException {
    public ExcecaoTarefaCampoInvalido(String message) {
        super(message);
    }
    public ExcecaoTarefaCampoInvalido() {
        super("O corpo da requisição veio sem titulo");
    }
}
