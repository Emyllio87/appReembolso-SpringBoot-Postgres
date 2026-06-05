package com.empresa.appReembolso.repository;



import com.empresa.appReembolso.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuarioId(String usuarioId);
}