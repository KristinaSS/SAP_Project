package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Category;
import sapproject.project.services.interfaces.ICatagoryService;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("catagory")
public class CatagoryController {
    @Autowired
    ICatagoryService catagoryService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCatagories() {
       // log.debug("REST request to get all Catagory.");
        return catagoryService.findAll();
    }

    @GetMapping("/get-{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getCatagory(@PathVariable(name = "id") int id){
        //log.debug("REST request to get Catagory : {}", id);
        return  catagoryService.getOne(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCatagory(@Valid @RequestBody Category orderDetails) {
        //log.debug("REST request to save Catagory : {}", orderDetails);
        return catagoryService.createOne(orderDetails);
    }

    @PutMapping("/edit-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCatagory(@PathVariable(value = "ID") Integer ID,
                                   @Valid @RequestBody Category orderDetails){
       // log.debug("REST request to update Catagory : {}", ID);
        return catagoryService.updateByID(ID,orderDetails);
    }

    @DeleteMapping("/delete-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCatagory(@PathVariable(value = "ID") Integer ID) {
        //log.debug("REST request to delete Catagory : {}", ID);
        catagoryService.deleteByID(ID);
    }
}
