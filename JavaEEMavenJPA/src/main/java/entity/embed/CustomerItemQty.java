package entity.embed;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerItemQty {
	
	private String itemName;
	
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
