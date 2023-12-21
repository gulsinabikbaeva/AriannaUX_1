package cp.webapp.web.controller;

import cp.webapp.web.dto.ItemDTO;
import cp.webapp.web.dto.Success;
import cp.webapp.web.exceptions.InvalidCategoryException;
import cp.webapp.web.model.Item;
import cp.webapp.web.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {
    public final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<ItemDTO> list(){
        return service.findAll().stream().map(item -> ItemDTO.item2DTO(item)).collect(Collectors.toList());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ItemDTO> get(@PathVariable int id){
        if (!service.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Item item = service.get(id);
        return item != null ? new ResponseEntity<>(ItemDTO.item2DTO(item), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="")
    public ResponseEntity<ItemDTO> post(@RequestBody Item item){
        item = service.post(item);
        return new ResponseEntity<>(ItemDTO.item2DTO(item), HttpStatus.CREATED);
    }
    @PostMapping(value = "/from/file")
    public String postItemsFromFile(@RequestParam("csvFile") MultipartFile csvFile, Model model) {
        // validate file
        if (csvFile.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {
            try {
                service.importFromFile(csvFile);
            } catch (InvalidCategoryException e1) {
                model.addAttribute("message", e1.getMessage());
                model.addAttribute("status", false);
                e1.printStackTrace();
            } catch (Exception e2) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
                e2.printStackTrace();
            }
        }
        return "fileUploaded";
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ItemDTO> put(@PathVariable int id, @RequestBody Item item){
        if (!service.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        item.setId(id);
        service.put(item);
        return new ResponseEntity<>(ItemDTO.item2DTO(item), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Success> delete(@PathVariable int id){
        if (!service.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        boolean remove = service.delete(id);
        if(remove) return new ResponseEntity<>(new Success(true), HttpStatus.OK);
        else return new ResponseEntity<>(new Success(false), HttpStatus.OK);
    }



}