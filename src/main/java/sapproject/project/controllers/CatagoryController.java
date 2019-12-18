package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Catagory;
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
    public List<Catagory> getAllCatagories() {
       // log.debug("REST request to get all Catagory.");
        return catagoryService.findAll();
    }

    @GetMapping("/get-{id}")
    @ResponseStatus(HttpStatus.OK)
    public Catagory getCatagory(@PathVariable(name = "id") int id){
        //log.debug("REST request to get Catagory : {}", id);
        return  catagoryService.getOne(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Catagory createCatagory(@Valid @RequestBody Catagory orderDetails) {
        //log.debug("REST request to save Catagory : {}", orderDetails);
        return catagoryService.createOne(orderDetails);
    }

    @PutMapping("/edit-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public Catagory updateCatagory(@PathVariable(value = "ID") Integer ID,
                                         @Valid @RequestBody Catagory orderDetails){
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
