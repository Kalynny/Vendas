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
@Table(name = "tb_itensorc")
@NamedQueries({
@NamedQuery(name = "Itemorc.listar", query = "SELECT itemorc FROM Itemorc itemorc" ),
@NamedQuery(name = "Itemorc.buscarPorCodigo", query = "SELECT itemorc FROM Itemorc itemorc WHERE itemorc.codigo = :codigo" )

})
public class Itemorc {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="iteorc_codigo")
	private Long codigo;

	@Column(name="iteorc_quantidade",  nullable=false )
	public Integer quantidade;

	@Column(name="iteorc_valor_parcial", nullable=false, scale=2, precision=7 )
	private BigDecimal valor_parcial;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tb_produtos_pro_codigo", referencedColumnName="pro_codigo", 
	nullable=false)
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tb_orcamentos_orc_codigo", referencedColumnName="orc_codigo", 
	nullable=false)
	private Orcamentos orcamento;

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

	public Orcamentos getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamentos orcamento) {
		this.orcamento = orcamento;
	}
	
	@Override
	public String toString() {
		return "Itemorc [codigo=" + codigo + ", quantidade=" + quantidade + ", valor_parcial=" + valor_parcial
				+ ", produto=" + produto + ", orcamento=" + orcamento +", ]";
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
		Itemorc other = (Itemorc) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
}
