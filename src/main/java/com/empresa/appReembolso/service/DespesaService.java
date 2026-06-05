package com.empresa.appReembolso.service;



import com.empresa.appReembolso.model.Despesa;
import com.empresa.appReembolso.repository.DespesaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    private final DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    public List<Despesa> listarTodas() {
        return repository.findAll();
    }

    public List<Despesa> listarPorUsuario(String usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Despesa salvar(Despesa despesa) {
        if (despesa.getStatus() == null || despesa.getStatus().isEmpty()) {
            despesa.setStatus("PENDENTE");
        }
        return repository.save(despesa);
    }

    public Optional<Despesa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Despesa atualizarStatus(Long id, String novoStatus) {
        Despesa despesa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
        despesa.setStatus(novoStatus);
        return repository.save(despesa);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}