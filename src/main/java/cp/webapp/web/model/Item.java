package cp.webapp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of = {"id"}) @Builder
@Entity
public class Item {
	@Id
	@GeneratedValue
	private int id;

	private String title;

	private double price;
	private boolean available;


	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	private Category category;

	@ManyToOne
	private SubCategory subCategory;


	@ManyToOne
	private User author;

	@Enumerated(EnumType.STRING)
	private ItemType type;


	@Lob
	@JsonIgnore

	private byte[] image; //
	@ElementCollection
	private List<String> imagesLinks;

	public Item(String title, double price, boolean available, String description, Category category, SubCategory subCategory, User author, ItemType type, List<String> imagesLinks) {
		this.title = title;
		this.price = price;
		this.available = available;
		this.description = description;
		this.category = category;
		this.subCategory = subCategory;
		this.author = author;
		this.type = type;
		this.imagesLinks = imagesLinks;
	}



}