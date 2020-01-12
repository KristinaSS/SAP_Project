package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.ListSizeIsZero;
import sapproject.project.models.Campaign;
import sapproject.project.models.Category;
import sapproject.project.repository.CatagoryRepository;
import sapproject.project.services.interfaces.ICatagoryService;

import java.util.List;

@Log4j2
@Service
public class CatagoryService implements ICatagoryService{

    @Autowired
    CatagoryRepository catagoryRepository;

    @Override
    public Category findCategoryByName(String name) {
        for (Category category : catagoryRepository.findAll()) {
            if (category.getName().equals(name))
                return category;
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = catagoryRepository.findAll();
        if(categories.size() == 0)
            throw new ListSizeIsZero("catagories");
        return categories;
    }


}
