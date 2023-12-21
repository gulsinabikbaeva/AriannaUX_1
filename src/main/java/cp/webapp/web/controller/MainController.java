package cp.webapp.web.controller;

import cp.webapp.web.dto.ItemDTO;
import cp.webapp.web.model.Category;
import cp.webapp.web.model.Item;
import cp.webapp.web.model.ItemType;
import cp.webapp.web.model.SubCategory;
import cp.webapp.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("supplyItems", itemService.findAll());
        return "index";
    }
    @GetMapping("/specialization")
    public String specialization(Model model){
        model.addAttribute("supplyItems", itemService.findAll());
        return "specialization";
    }
    @GetMapping("/map")
    public String map(Model model){

        return "map";
    }
    @GetMapping("/htw")
    public String htw(Model model){

        return "htw";
    }

    @GetMapping("/item/{id}")
    public String detail(@PathVariable int id, Model model){
        model.addAttribute("item", itemService.get(id));
        return "detail";
    }
    /*@GetMapping("/{id}")
    public String dettaglioOrdini(@PathVariable int id, Model model){
        model.addAttribute("order", itemService.getOrder(id));
        return "dettaglioOrdini";
    }*/

    @GetMapping("/item/new")
    public String newPost(Model model){
        List<Category> categories = itemService.getCategories();
        model.addAttribute("item", new Item());
        model.addAttribute("categories", itemService.getCategories());
        model.addAttribute("authors", itemService.getAuthors());
        model.addAttribute("subcategories", itemService.getSubCategories(categories.get(0).getName()));
        return "edit";

    }

    @PostMapping("/item/new")
    public String post(Item item, @RequestParam("file") MultipartFile file) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        item.setAuthor(itemService.findUserByUsername(user.getUsername()));
        if(!file.isEmpty()){
            if (file.getBytes().length > 1000000)
                return "redirect:/errorImage";
            else
                item.setImage(file.getBytes());
        }
        itemService.post(item);
        return "redirect:/";
    }

    @GetMapping(value = "/item/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] image(@PathVariable int id) {
        Item item = itemService.get(id);
        return item.getImage();
    }

    @GetMapping("/item/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        Item item = itemService.get(id);
        List<Category> categories = itemService.getCategories();
        model.addAttribute("item", item);
        model.addAttribute("categories", itemService.getCategories());
        model.addAttribute("categories", categories);
        model.addAttribute("subcategories", itemService.getSubCategories(item.getCategory().getName()));
        model.addAttribute("authors", itemService.getAuthors());
        return "edit";

    }

    @PostMapping("/item/{id}/edit")
    public String put(@PathVariable int id, Item newItem, @RequestParam("file") MultipartFile file) throws IOException {
        Item item = itemService.get(id);
        item.setType(newItem.getType());
        item.setTitle(newItem.getTitle());
        item.setCategory(newItem.getCategory());
        item.setSubCategory(newItem.getSubCategory());
        item.setDescription(newItem.getDescription());
        if(!file.isEmpty())
            item.setImage(file.getBytes());
        itemService.put(item);
        return "redirect:/item/{id}";
    }

    @GetMapping(value = "/item/{id}/delete")
    public String detail(@PathVariable int id){
        itemService.delete(id);
        return "redirect:/";
    }

    @GetMapping(value = "/filter/{category}")
    public String filter(@PathVariable String category, Model model){
        model.addAttribute("supplyItems", itemService.searchByCategoryAndType(category, ItemType.Offerta));
        return "index";
    }

    @GetMapping(value = "/category/{category}/sub")
    @ResponseBody
    public List<SubCategory> subCategories(@PathVariable String category, Model model){
        return itemService.getSubCategories(category);
    }


    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(){
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(cp.webapp.web.model.User user){
        itemService.postUser(user);
        return "redirect:/login";
    }

    @GetMapping("/item/search")
    @ResponseBody
    public List<ItemDTO> search(@RequestParam("q") String search){
        return itemService.list(search).stream().map(item -> ItemDTO.item2DTO(item)).collect(Collectors.toList());
    }

    @GetMapping(value = "/checkout")
    public String checkout(){
        return "checkout";
    }

    @GetMapping(value = "/orderConfirmation")
    public String orderConfirmation(cp.webapp.web.model.User user, cp.webapp.web.model.CustomOrder order){

        if(itemService.placeOrder(user,order)) return "orderConfirmation";
        return "orderNotConfirmed";
    }

    @GetMapping(value = "/forgotPassword")
    public String forgotPassword(){
        return "forgotPassword";
    }

    @GetMapping(value = "/passwordSent")
    public String passwordSent(){
        return "passwordSent";
    }
}