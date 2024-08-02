package com.nyx.nyx_backend.domain.resource.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_resource")
@SequenceGenerator(name = "default_gen", sequenceName = "role_seq", allocationSize = 1)
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ano_movimentacao")
    private Integer anoMovimentacao;

    @Column(name = "mes_movimentacao")
    private Integer mesMovimentacao;

    @Column(name = "orgao_codigo")
    private Integer orgaoCodigo;

    @Column(name = "orgao_nome")
    private String orgaoNome;

    @Column(name = "unidade_codigo")
    private Double unidadeCodigo;

    @Column(name = "unidade_nome")
    private String unidadeNome;

    @Column(name = "categoria_economica_codigo")
    private Integer categoriaEconomicaCodigo;

    @Column(name = "categoria_economica_nome")
    private String categoriaEconomicaNome;

    @Column(name = "grupo_despesa_codigo")
    private Integer grupoDespesaCodigo;

    @Column(name = "grupo_despesa_nome")
    private String grupoDespesaNome;

    @Column(name = "modalidade_aplicacao_codigo")
    private Integer modalidadeAplicacaoCodigo;

    @Column(name = "modalidade_aplicacao_nome")
    private String modalidadeAplicacaoNome;

    @Column(name = "elemento_codigo")
    private Integer elementoCodigo;

    @Column(name = "elemento_nome")
    private String elementoNome;

    @Column(name = "subelemento_codigo")
    private Integer subelementoCodigo;

    @Column(name = "subelemento_nome")
    private String subelementoNome;

    @Column(name = "funcao_codigo")
    private Integer funcaoCodigo;

    @Column(name = "funcao_nome")
    private String funcaoNome;

    @Column(name = "subfuncao_codigo")
    private Integer subfuncaoCodigo;

    @Column(name = "subfuncao_nome")
    private String subfuncaoNome;

    @Column(name = "programa_codigo")
    private Integer programaCodigo;

    @Column(name = "programa_nome")
    private String programaNome;

    @Column(name = "acao_codigo")
    private Integer acaoCodigo;

    @Column(name = "acao_nome")
    private String acaoNome;

    @Column(name = "fonte_recurso_codigo")
    private Integer fonteRecursoCodigo;

    @Column(name = "fonte_recurso_nome")
    private String fonteRecursoNome;

    @Column(name = "empenho_ano")
    private Integer empenhoAno;

    @Column(name = "empenho_modalidade_nome")
    private String empenhoModalidadeNome;

    @Column(name = "empenho_modalidade_codigo")
    private Integer empenhoModalidadeCodigo;

    @Column(name = "empenho_numero")
    private Integer empenhoNumero;

    @Column(name = "subempenho")
    private Integer subempenho;

    @Column(name = "indicador_subempenho")
    private String indicadorSubempenho;

    @Column(name = "credor_codigo")
    private Integer credorCodigo;

    @Column(name = "credor_nome")
    private String credorNome;

    @Column(name = "modalidade_licitacao_codigo")
    private Integer modalidadeLicitacaoCodigo;

    @Column(name = "modalidade_licitacao_nome")
    private String modalidadeLicitacaoNome;

    @Column(name = "valor_empenhado")
    private Double valorEmpenhado;

    @Column(name = "valor_liquidado")
    private Double valorLiquidado;

    @Column(name = "valor_pago")
    private Double valorPago;
}
