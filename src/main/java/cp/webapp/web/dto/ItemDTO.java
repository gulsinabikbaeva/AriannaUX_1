package cp.webapp.web.dto;

import cp.webapp.web.model.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter @Builder
public class ItemDTO {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String author;

    public static ItemDTO item2DTO(Item item){
        return ItemDTO.builder()
                .id(item.getId())
                .title(item.getTitle())
                .price(item.getPrice())
                .description(item.getDescription())
                .author(item.getAuthor().getUsername())
                .category(item.getCategory().getName())
                .build();
    }
}