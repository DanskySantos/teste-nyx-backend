package com.nyx.nyx_backend.domain.resource.service;

import com.nyx.nyx_backend.domain.resource.entities.response.DespesasTotaisAgrupadasPorCategoriaResponse;
import com.nyx.nyx_backend.domain.resource.entities.response.DespesasTotaisPorMesResponse;
import com.nyx.nyx_backend.domain.resource.entities.response.DinheiroAgrupadoPorFonteResponse;
import com.nyx.nyx_backend.domain.resource.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public List<DespesasTotaisPorMesResponse> findByMesMovimentacao() {
        List<Object[]> results = resourceRepository.getTotalDespesasPorMes();
        List<DespesasTotaisPorMesResponse> responseList = new ArrayList<>();
        for (Object[] row : results) {
            Integer mesMovimentacao = (Integer) row[0];
            Double totalDespesas = ((Number) row[1]).doubleValue();
            DespesasTotaisPorMesResponse response = new DespesasTotaisPorMesResponse(
                    mesMovimentacao,
                    totalDespesas
            );
            responseList.add(response);
        }
        return responseList;
    }

    public List<DespesasTotaisAgrupadasPorCategoriaResponse> findByCategoria() {
        List<Object[]> results = resourceRepository.getTotalDespesasPorCategoria();
        List<DespesasTotaisAgrupadasPorCategoriaResponse> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String categoria_economica_nome = (String) row[0];
            Double totalDespesas = ((Number) row[1]).doubleValue();
            DespesasTotaisAgrupadasPorCategoriaResponse response = new DespesasTotaisAgrupadasPorCategoriaResponse(
                    categoria_economica_nome,
                    totalDespesas
            );
            responseList.add(response);
        }
        return responseList;
    }

    public List<DinheiroAgrupadoPorFonteResponse> findByFonte() {
        List<Object[]> results = resourceRepository.getTotalFonteRecurso();
        List<DinheiroAgrupadoPorFonteResponse> responseList = new ArrayList<>();

        for (Object[] row : results) {
            String fonte_recurso_nome = (String) row[0];
            Double total_fonte = ((Number) row[1]).doubleValue();

            DinheiroAgrupadoPorFonteResponse response = new DinheiroAgrupadoPorFonteResponse(
                    fonte_recurso_nome,
                    total_fonte
            );
            responseList.add(response);
        }

        return responseList;
    }
}
