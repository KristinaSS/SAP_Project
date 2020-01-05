package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Category;
import sapproject.project.services.classes.CatagoryService;
import sapproject.project.services.interfaces.ICatagoryService;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("catagory")
public class CatagoryController {
    @Autowired
    CatagoryService catagoryService;

}
