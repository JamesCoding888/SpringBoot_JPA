package entity.embed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	
	@Column(name = "purchased_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchasedDate; 
	
	
	@Column(name = "received_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date receivedDate;
	
	
	
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

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	
	public static Date convertToLocalDateToDate(LocalDate localDate) {
        // Step 1: Convert LocalDate to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTime = localDate.atStartOfDay();
        
        // Step 2: Convert LocalDateTime to Date
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        return date;
    }
	
	
	
}
