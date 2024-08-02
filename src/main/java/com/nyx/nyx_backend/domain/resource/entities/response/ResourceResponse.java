package com.nyx.nyx_backend.domain.resource.entities.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceResponse {

    private Long id;

    private Integer anoMovimentacao;

    private Integer mesMovimentacao;

    private Integer orgaoCodigo;

    private String orgaoNome;

    private Double unidadeCodigo;

    private String unidadeNome;

    private Integer categoriaEconomicaCodigo;

    private String categoriaEconomicaNome;

    private Integer grupoDespesaCodigo;

    private String grupoDespesaNome;

    private Integer modalidadeAplicacaoCodigo;

    private String modalidadeAplicacaoNome;

    private Integer elementoCodigo;

    private String elementoNome;

    private Integer subelementoCodigo;

    private String subelementoNome;

    private Integer funcaoCodigo;

    private String funcaoNome;

    private Integer subfuncaoCodigo;

    private String subfuncaoNome;

    private Integer programaCodigo;

    private String programaNome;

    private Integer acaoCodigo;

    private String acaoNome;

    private Integer fonteRecursoCodigo;

    private String fonteRecursoNome;

    private Integer empenhoAno;

    private String empenhoModalidadeNome;

    private Integer empenhoModalidadeCodigo;

    private Integer empenhoNumero;

    private Integer subempenho;

    private String indicadorSubempenho;

    private Integer credorCodigo;

    private String credorNome;

    private Integer modalidadeLicitacaoCodigo;

    private String modalidadeLicitacaoNome;

    private Double valorEmpenhado;

    private Double valorLiquidado;

    private Double valorPago;
}
