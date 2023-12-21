package cp.webapp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode(of = {"id"}) @Builder
@Entity
public class CustomOrder {
	@Id
	@GeneratedValue
	private int id;

	private double total;

	private Date date;

	@ManyToOne
	private User customerUsername;

	@ManyToOne
	private User sellerUsername;

	@ManyToMany
	private List<Item> items = new ArrayList<>();

	public CustomOrder() {
		this.id=this.getId();
		this.date = new Date();
	}

	public void addItemToOrder(Item i) {
		this.items.add(i);
	}


}