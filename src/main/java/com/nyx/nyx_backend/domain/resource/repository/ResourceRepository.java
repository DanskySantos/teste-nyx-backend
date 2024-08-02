package com.nyx.nyx_backend.domain.resource.repository;

import com.nyx.nyx_backend.domain.resource.entities.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query(value = "SELECT mes_movimentacao, SUM(valor_pago) as total_despesas " +
            "FROM tb_resource " +
            "GROUP BY mes_movimentacao " +
            "ORDER BY mes_movimentacao",
            nativeQuery = true)
    List<Object[]> getTotalDespesasPorMes();

    @Query(value = "SELECT categoria_economica_nome, SUM(valor_pago) as total_despesas " +
            "FROM tb_resource " +
            "GROUP BY categoria_economica_nome " +
            "ORDER BY total_despesas DESC",
            nativeQuery = true)
    List<Object[]> getTotalDespesasPorCategoria();

    @Query(value = "SELECT fonte_recurso_nome, SUM(valor_pago) as total_fonte " +
            "FROM tb_resource " +
            "GROUP BY fonte_recurso_nome " +
            "ORDER BY total_fonte DESC",
            nativeQuery = true)
    List<Object[]> getTotalFonteRecurso();
}
