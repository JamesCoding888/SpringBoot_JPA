package entity.embed;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerItemQty {
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_qty")
	private Integer itemQty;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	@Override
	public String toString() {
		return "CustomerItemQty [itemName=" + itemName + ", itemQty=" + itemQty + "]";
	}	
	
	
}
