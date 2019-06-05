package br.com.Vendas.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "tb_itensusu")
@NamedQueries({
@NamedQuery(name = "Itemusu.listar", query = "SELECT itemusu FROM Itemusu itemusu" ),
@NamedQuery(name = "Itemusu.buscarPorCodigo", query = "SELECT itemusu FROM Itemusu itemusu WHERE itemusu.codigo = :codigo" )

})
public class Itemusu {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="iteusu_codigo")
	private Long codigo;

	@Column(name="iteusu_login",  nullable=false )
	private String login;

	@Column(name = "iteusu_senha", nullable = false)
	private String senha;

	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

		@Override
	public String toString() {
		return "Itemusu [codigo=" + codigo + ", login=" + login + ", senha=" + senha
				+ ",]";
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
		Itemusu other = (Itemusu) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
}
