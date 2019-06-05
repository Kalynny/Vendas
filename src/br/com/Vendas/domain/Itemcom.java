package br.com.Vendas.domain;


import java.math.BigDecimal;
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

@Entity
@Table(name = "tb_itenscom")
@NamedQueries({
@NamedQuery(name = "Itemcom.listar", query = "SELECT itemcom FROM Itemcom itemcom" ),
@NamedQuery(name = "Itemcom.buscarPorCodigo", query = "SELECT itemcom FROM Itemcom itemcom WHERE itemcom.codigo = :codigo" )

})
public class Itemcom {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="itecom_codigo")
	private Long codigo;

	@Column(name="itecom_quantidade",  nullable=false )
	public Integer quantidade;

	@Column(name="itecom_valor_parcial", nullable=false, scale=2, precision=7 )
	private BigDecimal valor_parcial;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tb_produtos_pro_codigo", referencedColumnName="pro_codigo", 
	nullable=false)
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tb_compras_com_codigo", referencedColumnName="com_codigo", 
	nullable=false)
	private Compras compra;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor_parcial() {
		return valor_parcial;
	}

	public void setValor_parcial(BigDecimal valor_parcial) {
		this.valor_parcial = valor_parcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Compras getCompra() {
		return compra;
	}

	public void setCompra(Compras compra) {
		this.compra = compra;
	}
	
	@Override
	public String toString() {
		return "Itemcom [codigo=" + codigo + ", quantidade=" + quantidade + ", valor_parcial=" + valor_parcial
				+ ", produto=" + produto + ", compra=" + compra +", ]";
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
		Itemcom other = (Itemcom) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
}
