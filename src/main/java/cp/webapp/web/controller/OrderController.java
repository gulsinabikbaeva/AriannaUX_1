package cp.webapp.web.controller;

import cp.webapp.web.dto.ItemDTO;
import cp.webapp.web.dto.OrderDTO;
import cp.webapp.web.model.CustomOrder;
import cp.webapp.web.model.Item;
import cp.webapp.web.repository.CustomOrderRepository;
import cp.webapp.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    ItemService service;

    @Autowired
    private ItemService itemService;

    /*@GetMapping(value="/{id}")
    public ResponseEntity<OrderDTO> get(@PathVariable int id){
        if (!service.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CustomOrder order = service.getOrder(id);
        return order != null ? new ResponseEntity<>(OrderDTO.order2DTO(order), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/




}
