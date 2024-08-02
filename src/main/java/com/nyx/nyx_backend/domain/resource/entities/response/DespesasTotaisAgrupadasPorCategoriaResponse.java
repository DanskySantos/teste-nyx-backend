package com.nyx.nyx_backend.domain.resource.entities.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DespesasTotaisAgrupadasPorCategoriaResponse {

    private String categoria_economica_nome;
    private Double total_despesas;
}
