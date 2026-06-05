package com.empresa.appReembolso.controller;



import com.empresa.appReembolso.model.Despesa;
import com.empresa.appReembolso.service.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/despesas")
@CrossOrigin(origins = "*")
public class DespesaController {

    private final DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Despesa> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Despesa> listarPorUsuario(@PathVariable String usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @PostMapping
    public Despesa criar(@RequestBody Despesa despesa) {
        return service.salvar(despesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizar(@PathVariable Long id, @RequestBody Despesa despesa) {
        return service.buscarPorId(id).map(existente -> {
            despesa.setId(id);
            return ResponseEntity.ok(service.salvar(despesa));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Despesa> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String novoStatus = body.get("status");
        Despesa atualizada = service.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}