package entity.embed;

import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_embedded")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@ElementCollection
	@CollectionTable(
			name = "customer_item_qty_map",
			joinColumns = @JoinColumn(name = "customer_id")			
	)	
	private Map<String, Integer> itemQtyMap;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Integer> getItemQtyMap() {
		return itemQtyMap;
	}

	public void setItemQtyMap(Map<String, Integer> itemQtyMap) {
		this.itemQtyMap = itemQtyMap;
	}
	
	
	
}
