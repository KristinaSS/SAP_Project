package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.models.Category;
import sapproject.project.repository.CatagoryRepository;
import sapproject.project.services.interfaces.ICatagoryService;

import java.util.List;

@Log4j2
@Service
public class CatagoryService implements ICatagoryService {

    @Autowired
    CatagoryRepository catagoryRepository;

    public Category findCategoryByName(String name){
        for (Category category: catagoryRepository.findAll()){
            if(category.getName().equals(name))
                return category;
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return catagoryRepository.findAll();
    }

    @Override
    public Category getOne(int Id) {
        return catagoryRepository.findById(Id) .orElseGet(()->{
            try {
                throw new EntityNotFoundException(Category.class);
            } catch (EntityNotFoundException e) {
                //log.warn("A category with this Id has not been found:  {}", Id);
            }
            return null;
        });
    }

    @Override
    public Category createOne(Category entity) {
        //log.info("New category has been created: {}", entity);
        return catagoryRepository.save(entity);
    }

    @Override
    public void deleteByID(int ID) {
        Category category = getOne(ID);
        if(category == null) {
            try {
                throw new EntityNotFoundException(Category.class);
            } catch (EntityNotFoundException e) {
               // log.warn("Category not found: {}", ID);
            }
            return;
        }
        //log.info("Deleted category: {} ",ID);
        catagoryRepository.delete(category);

    }

    @Override
    public Category updateByID(int ID, Category entity) {
        return catagoryRepository.findById(ID)
                .map(accountType -> catagoryRepository.save(updateCatagoryMembers(accountType,entity)))
                .orElseGet(()->{
                    entity.setCategoryId(ID);
                    //log.info("New category has been created: {}",ID);
                    return catagoryRepository.save(entity);
                });
    }

    private Category updateCatagoryMembers(Category category, Category updatedCategory){
        category.setName(updatedCategory.getName());
        //log.info("Category  updated: {}", catagory);
        return category;
    }
}
