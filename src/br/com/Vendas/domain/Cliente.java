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
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_clientes")
@NamedQueries({
@NamedQuery(name = "Cliente.listar", query = "SELECT cliente FROM Cliente cliente" ),
@NamedQuery(name = "Cliente.buscarPorCodigo", query = "SELECT cliente FROM Cliente cliente WHERE cliente.codigo = :codigo" )

})
public class Cliente {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "cli_codigo")
	private Long codigo;

	@NotEmpty(message = "Insira o Nome")
	@Column(name = "cli_nome", length = 50, nullable = false)
	private String nome;
	
	@NotEmpty(message = "Insira o Endereço")
	@Column(name="cli_endereco", length=50, nullable=false )
	private String endereco;

	@NotEmpty(message = "Insira o Bairro")
	@Column(name="cli_bairro", length=50, nullable=false )
	private String bairro;

	@NotEmpty(message = "Insira a Cidade")
	@Column(name="cli_cidade", length=50, nullable=false )
	private String cidade;

	@NotEmpty(message = "Insira o Estado")
	@Column(name="cli_estado", length=50, nullable=false )
	private String estado;

	@NotEmpty(message = "Insira o CEP")
	@Column(name="cli_cep", length=50, nullable=false )
	private String cep;

	@NotEmpty(message = "Insira o Fone")
	@Column(name="cli_fone", length=50, nullable=false )
	private String fone;

	@NotEmpty(message = "Insira o E-mail")
	@Column(name="cli_email", length=50, nullable=false )
	private String email;
	
	@CPF(message = "CPF Incorreto")
	@Column(name = "cli_cpf", length = 14, nullable = false, unique = true)
	private String cpf;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", bairro=" + bairro + ", "
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
