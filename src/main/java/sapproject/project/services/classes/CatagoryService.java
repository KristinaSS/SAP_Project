package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.models.Catagory;
import sapproject.project.repository.CatagoryRepository;
import sapproject.project.services.interfaces.ICatagoryService;

import java.util.List;

@Log4j2
@Service
public class CatagoryService implements ICatagoryService {

    @Autowired
    CatagoryRepository catagoryRepository;

    @Override
    public List<Catagory> findAll() {
        return catagoryRepository.findAll();
    }

    @Override
    public Catagory getOne(int Id) {
        return catagoryRepository.findById(Id) .orElseGet(()->{
            try {
                throw new EntityNotFoundException(Catagory.class);
            } catch (EntityNotFoundException e) {
                //log.warn("A category with this Id has not been found:  {}", Id);
            }
            return null;
        });
    }

    @Override
    public Catagory createOne(Catagory entity) {
        //log.info("New category has been created: {}", entity);
        return catagoryRepository.save(entity);
    }

    @Override
    public void deleteByID(int ID) {
        Catagory catagory = getOne(ID);
        if(catagory == null) {
            try {
                throw new EntityNotFoundException(Catagory.class);
            } catch (EntityNotFoundException e) {
               // log.warn("Category not found: {}", ID);
            }
            return;
        }
        //log.info("Deleted category: {} ",ID);
        catagoryRepository.delete(catagory);

    }

    @Override
    public Catagory updateByID(int ID, Catagory entity) {
        return catagoryRepository.findById(ID)
                .map(accountType -> catagoryRepository.save(updateCatagoryMembers(accountType,entity)))
                .orElseGet(()->{
                    entity.setCategoryId(ID);
                    //log.info("New category has been created: {}",ID);
                    return catagoryRepository.save(entity);
                });
    }

    private Catagory updateCatagoryMembers(Catagory catagory, Catagory updatedCatagory){
        catagory.setName(updatedCatagory.getName());
        //log.info("Category  updated: {}", catagory);
        return catagory;
    }
}
