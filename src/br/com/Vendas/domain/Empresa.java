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
@Table(name = "tb_empresas")
@NamedQueries({
@NamedQuery(name = "Empresa.listar", query = "SELECT empresa FROM Empresa empresa" ),
@NamedQuery(name = "Empresa.buscarPorCodigo", query = "SELECT empresa FROM Empresa empresa WHERE empresa.codigo = :codigo" )

})
public class Empresa {

@GeneratedValue(strategy = GenerationType.AUTO)
@Id
@Column(name="emp_codigo")
private Long codigo;

@NotEmpty(message = "Insira a Razão Social")
@Column(name="emp_rsocial", length=50, nullable=false )
private String rsocial;

@NotEmpty(message = "Insira o Nome Fantasia")
@Column(name="emp_nfantasia", length=50, nullable=false )
private String nfantasia;

@NotEmpty(message = "Insira o CNPJ")
@Column(name="emp_cnpj", length=50, nullable=false )
private String cnpj;

@NotEmpty(message = "Insira o Endereço")
@Column(name="emp_endereco", length=50, nullable=false )
private String endereco;

@NotEmpty(message = "Insira o Bairro")
@Column(name="emp_bairro", length=50, nullable=false )
private String bairro;

@NotEmpty(message = "Insira a Cidade")
@Column(name="emp_cidade", length=50, nullable=false )
private String cidade;

@NotEmpty(message = "Insira o Estado")
@Column(name="emp_estado", length=50, nullable=false )
private String estado;

@NotEmpty(message = "Insira o CEP")
@Column(name="emp_cep", length=50, nullable=false )
private String cep;

@NotEmpty(message = "Insira o Fone")
@Column(name="emp_fone", length=50, nullable=false )
private String fone;

@NotEmpty(message = "Insira o E-mail")
@Column(name="emp_email", length=50, nullable=false )
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
	return "Empresa [codigo=" + codigo + ", rsocial=" + rsocial + ", cnpj=" + cnpj + ", nfantasia=" + nfantasia + ", endereco=" + endereco + ", bairro=" + bairro + ", "
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
	Empresa other = (Empresa) obj;
	if (codigo == null) {
		if (other.codigo != null)
			return false;
	} else if (!codigo.equals(other.codigo))
		return false;
	return true;
}



}

