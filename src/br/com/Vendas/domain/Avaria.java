package br.com.Vendas.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_avarias")
@NamedQueries({
@NamedQuery(name = "Avaria.listar", query = "SELECT avaria FROM Avaria avaria" ),
@NamedQuery(name = "Avaria.buscarPorCodigo", query = "SELECT avaria FROM Avaria avaria WHERE avaria.codigo = :codigo" )

})
public class Avaria {


@GeneratedValue(strategy = GenerationType.AUTO)
@Id
@Column(name="ava_codigo")
private Long codigo;

@NotEmpty(message = "Insira o Cógido de Barras")
@Column(name="ava_cbarras", length=50, nullable=false )
private String cbarras;

@NotEmpty(message = "Insira a Descrição")
@Column(name="ava_descricao", length=50, nullable=false )
private String descricao;

@NotNull(message = "Insira a Quantidade")
@Min(value = 0 , message = "O valor não pode ser menor que 0")
@Column(name="ava_quantidade",  nullable=false )
private Integer quantidade;

@NotNull(message = "Insira o Motivo")
@Min(value = 0 , message = "Digite o Motivo da Avaria")
@Column(name="ava_motivo",  nullable=false )
private String motivo;

@NotNull(message = "Insira um Fornecedor")
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="tb_fornecedores_for_codigo", referencedColumnName="for_codigo", 
nullable=false)
private Fornecedor fornecedor;

public Long getCodigo() {
	return codigo;
}

public void setCodigo(Long codigo) {
	this.codigo = codigo;
}

public String getCbarras() {
	return cbarras;
}

public void setCbarras(String cbarras) {
	this.cbarras = cbarras;
}

public String getDescricao() {
	return descricao;
}

public void setDescricao(String descricao) {
	this.descricao = descricao;
}

public Integer getQuantidade() {
	return quantidade;
}

public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
}

public String getMotivo() {
	return motivo;
}

public void setMotivo(String motivo) {
	this.motivo = motivo;
}

public Fornecedor getFornecedor() {
	return fornecedor;
}

public void setFornecedor(Fornecedor fornecedor) {
	this.fornecedor = fornecedor;
}

@Override
public String toString() {
	return "Produto [codigo=" + codigo + ", cbarras=" + cbarras + ", descricao=" + descricao + ", quantidade=" + quantidade
			+ ",motivo=" + motivo + ", fornecedor=" + fornecedor + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Avaria other = (Avaria) obj;
	if (codigo == null) {
		if (other.codigo != null)
			return false;
	} else if (!codigo.equals(other.codigo))
		return false;
	return true;
}



}



