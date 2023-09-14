package entity.jpql_function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id; 
	
	@Column(name = "symbol")
	private String symbol;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Double price;
	
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Stock(String symbol, String name, Double price) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.price = price;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Stock [id=" + id + ", symbol=" + symbol + ", name=" + name + ", price=" + price + "]";
	}

	
	
	
}
