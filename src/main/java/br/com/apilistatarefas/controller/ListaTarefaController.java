package br.com.apilistatarefas.controller;

import br.com.apilistatarefas.DTO.TarefaDTO;
import br.com.apilistatarefas.database.model.TarefaModel;
import br.com.apilistatarefas.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/listatarefas/")
@RequiredArgsConstructor
public class ListaTarefaController {
    private final TarefaService serviceTarefa;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TarefaModel> listarTarefa() {
        return serviceTarefa.listarTarefas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TarefaModel buscarTarefaPorId(@PathVariable int id) {
        return serviceTarefa.buscarTarefaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaModel criarTarefa(@RequestBody TarefaDTO dto) {
        return serviceTarefa.criarTarefa(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TarefaModel atualizarTarefa(
            @PathVariable int id,
            @RequestBody TarefaDTO dto) {
        return serviceTarefa.atualizarTarefa(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTarefa(@PathVariable int id) {
        serviceTarefa.deletarTarefa(id);
    }
}
