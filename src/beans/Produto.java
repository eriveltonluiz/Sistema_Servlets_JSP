package beans;

public class Produto {
	private Long id;
	private String nomeProd;
	private Double qtd;
	private Double preco;
	
	public Produto() {
	}

	public Produto(Long id, String nomeProd, Double qtd, Double preco) {
		this.id = id;
		this.nomeProd = nomeProd;
		this.qtd = qtd;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProd() {
		return nomeProd;
	}

	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}

	public Double getQtd() {
		return qtd;
	}

	public void setQtd(Double qtd) {
		this.qtd = qtd;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
