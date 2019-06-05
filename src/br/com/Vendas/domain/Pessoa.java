package br.com.Vendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_pessoas")
@NamedQueries({
@NamedQuery(name = "Pessoa.listar", query = "SELECT pessoa FROM Pessoa pessoa" ),
@NamedQuery(name = "Pessoa.buscarPorCodigo", query = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.codigo = :codigo" )

})
public class Pessoa {


@GeneratedValue(strategy = GenerationType.AUTO)
@Id
@Column(name="pes_codigo")
private Long codigo;

@NotEmpty(message = "Tipo de Pessoa")
@Column(name="pes_tpessoa", length=50, nullable=false )
private String tpessoa;

@NotEmpty(message = "Insira o Nome")
@Column(name="pes_nome", length=50, nullable=false )
private String nome;

@NotEmpty(message = "Insira o CPF")
@Column(name="pes_cpf", length=50, nullable=false )
private String cpf;

@NotEmpty(message = "Insira o RG")
@Column(name="pes_rg", length=50, nullable=false )
private String rg;

@NotEmpty(message = "Insira o CNPJ")
@Column(name="pes_cnpj", length=50, nullable=false )
private String cnpj;

@NotEmpty(message = "Insira o IE")
@Column(name="pes_ie", length=50, nullable=false )
private String ie;

public Long getCodigo() {
	return codigo;
}

public void setCodigo(Long codigo) {
	this.codigo = codigo;
}

public String getTpessoa() {
	return tpessoa;
}

public void setTpessoa(String tpessoa) {
	this.tpessoa = tpessoa;
}
	
public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCpf() {
	return cpf;
}

public void setCpf(String cpf) {
	this.cpf = cpf;
}

public String getRg() {
	return rg;
}

public void setRg(String rg) {
	this.rg = rg;
}

public String getCnpj() {
	return cnpj;
}

public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
}

public String getIe() {
	return ie;
}

public void setIe(String ie) {
	this.ie = ie;
}

@Override
public String toString() {
	return "Pessoa [codigo=" + codigo + ", tpessoa=" + tpessoa + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", cnpj=" + cnpj
			+ ", ie=" + ie + "]";
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
	Pessoa other = (Pessoa) obj;
	if (codigo == null) {
		if (other.codigo != null)
			return false;
	} else if (!codigo.equals(other.codigo))
		return false;
	return true;
}




}



