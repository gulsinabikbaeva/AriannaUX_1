package cp.webapp.web.controller;

import cp.webapp.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private ItemService itemService;


    @GetMapping(value="/addFavorite/{id}")
    public String addFavorite(@PathVariable int id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        itemService.addFavoriteToUser(id, itemService.findUserByUsername(user.getUsername()));
        return "redirect:/";
    }

    @GetMapping(value="/removeFavorite/{id}")
    public String removeFavorite(@PathVariable int id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        itemService.removeFavoriteFromUser(id, itemService.findUserByUsername(user.getUsername()));
        return "redirect:/favorites";
    }
}
