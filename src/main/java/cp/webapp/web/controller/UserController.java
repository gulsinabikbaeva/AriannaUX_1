package cp.webapp.web.controller;

import cp.webapp.web.model.User;
import cp.webapp.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/myProfile")
    public String getUserProfile(Model model) {
        User user = itemService.findUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        model.addAttribute("user", user);
        switch (user.getRole().getName()) {
            case "ROLE_BUYER":
                return "client";
            case "ROLE_SELLER":
                return "seller";
            case "ROLE_ADMIN":
                model.addAttribute("sellers", itemService.getAllUserByRole("ROLE_SELLER"));
                model.addAttribute("buyers", itemService.getAllUserByRole("ROLE_BUYER"));
                return "admin";
        }
        return "error";
    }
    @GetMapping("/buyers")
    public String getBuyers(Model model) {
        User user = itemService.findUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        model.addAttribute("user", user);
        if (user.getRole().getName()=="ROLE_ADMIN") {
                return "buyers";
        }
        return "error";
    }
    @GetMapping("/sellers")
    public String getSellers(Model model) {
        User user = itemService.findUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        model.addAttribute("user", user);
        if (user.getRole().getName()=="ROLE_ADMIN") {
            System.out.println("role is"+user.getRole().getName());
                return "sellers";
        }
        System.out.println(user.getRole().getName());
        return "error";
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        User user = itemService.findUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        model.addAttribute("user", user);
        switch (user.getRole().getName()) {
            case "ROLE_BUYER":
                return "ordersBuyer";
            case "ROLE_SELLER":
                return "ordersSeller";
            case "ROLE_ADMIN":
                model.addAttribute("ordersSeller", itemService.getAllUserByRole("ROLE_SELLER"));
                model.addAttribute("ordersBuyer", itemService.getAllUserByRole("ROLE_BUYER"));
                return "admin";
        }
        return "error";
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        User user = itemService.findUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        model.addAttribute("user", user);
        model.addAttribute("favorites", itemService.findUserByUsername(user.getUsername()).getFavorites());

        return "favorites";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        User user = itemService.findUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        model.addAttribute("user", user);
        model.addAttribute("cart", itemService.findUserByUsername(user.getUsername()).getCart());

        return "cart";
    }


}
