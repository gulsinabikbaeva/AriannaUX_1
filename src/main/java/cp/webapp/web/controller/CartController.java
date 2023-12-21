package cp.webapp.web.controller;

import cp.webapp.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value="/addItemToCart/{id}")
    public String addItemToOrder(@PathVariable int id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        itemService.addItemToCart(id, itemService.findUserByUsername(user.getUsername()));
        return "redirect:/cart";
    }

    @GetMapping(value="/removeItemFromCart/{id}")
    public String removeItemFromOrder(@PathVariable int id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        itemService.removeItemFromCart(id, itemService.findUserByUsername(user.getUsername()));
        return "redirect:/cart";
    }
}
