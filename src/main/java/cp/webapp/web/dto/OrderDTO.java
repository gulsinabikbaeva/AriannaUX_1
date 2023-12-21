package cp.webapp.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cp.webapp.web.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter @Getter @Builder
public class OrderDTO {
    private int id;
    private double total;
    private Date date;
    private User customerUsername;
    private User sellerUsername;

    public static OrderDTO order2DTO(CustomOrder order){
        return OrderDTO.builder()
                .id(order.getId())
                .total(order.getTotal())
                .date(order.getDate())
                .customerUsername(order.getCustomerUsername())
                .sellerUsername(order.getSellerUsername())
                .build();
    }




}