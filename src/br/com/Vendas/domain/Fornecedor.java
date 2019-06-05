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
@Table(name = "tb_fornecedores")
@NamedQueries({
@NamedQuery(name = "Fornecedor.listar", query = "SELECT fornecedor FROM Fornecedor fornecedor" ),
@NamedQuery(name = "Fornecedor.buscarPorCodigo", query = "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.codigo = :codigo" )

})
public class Fornecedor {

@GeneratedValue(strategy = GenerationType.AUTO)
@Id
@Column(name="for_codigo")
private Long codigo;

@NotEmpty(message = "Insira a Razão Social")
@Column(name="for_rsocial", length=50, nullable=false )
private String rsocial;

@NotEmpty(message = "Insira o Nome Fantasia")
@Column(name="for_nfantasia", length=50, nullable=false )
private String nfantasia;

@NotEmpty(message = "CNPJ Incorreto Favor Digite Novamente")
@Column(name="for_cnpj", length = 18, nullable = false, unique = true )
private String cnpj;

@NotEmpty(message = "Insira o IE")
@Column(name="for_ie", length=50, nullable=false )
private String ie;

@NotEmpty(message = "Insira o Endereço")
@Column(name="for_endereco", length=50, nullable=false )
private String endereco;

@NotEmpty(message = "Insira o Bairro")
@Column(name="for_bairro", length=50, nullable=false )
private String bairro;

@NotEmpty(message = "Insira a Cidade")
@Column(name="for_cidade", length=50, nullable=false )
private String cidade;

@NotEmpty(message = "Insira o Estado")
@Column(name="for_estado", length=50, nullable=false )
private String estado;

@NotEmpty(message = "Insira o CEP")
@Column(name="for_cep", length=50, nullable=false )
private String cep;

@NotEmpty(message = "Insira o Fone")
@Column(name="for_fone", length=50, nullable=false )
private String fone;

@NotEmpty(message = "Insira o E-mail")
@Column(name="for_email", length=50, nullable=false )
private String email;

public Long getCodigo() {
	return codigo;
}
public void setCodigo(Long codigo) {
	this.codigo = codigo;
}

public String getRsocial() {
	return rsocial;
}
public void setRsocial(String rsocial) {
	this.rsocial = rsocial;
}

public String getNfantasia() {
	return nfantasia;
}
public void setNfantasia(String nfantasia) {
	this.nfantasia = nfantasia;
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

public String getEndereco() {
	return endereco;
}

public void setEndereco(String endereco) {
	this.endereco = endereco;
}
public String getBairro() {
	return bairro;
}

public void setBairro(String bairro) {
	this.bairro = bairro;
}
public String getCidade() {
	return cidade;
}

public void setCidade(String cidade) {
	this.cidade = cidade;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}
public String getCep() {
	return cep;
}

public void setCep(String cep) {
	this.cep = cep;
}

public String getFone() {
	return fone;
}

public void setFone(String fone) {
	this.fone = fone;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "Fornecedor [codigo=" + codigo + ", rsocial=" + rsocial + ", nfantasia=" + nfantasia + ", cnpj=" + cnpj + ", ie=" + ie + ",endereco=" + endereco + ", bairro=" + bairro + ", "
			+ "cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", fone=" + fone + ", email=" + email + ",]";
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
	Fornecedor other = (Fornecedor) obj;
	if (codigo == null) {
		if (other.codigo != null)
			return false;
	} else if (!codigo.equals(other.codigo))
		return false;
	return true;
}



}

