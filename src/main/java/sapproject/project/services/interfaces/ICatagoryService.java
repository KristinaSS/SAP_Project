package sapproject.project.services.interfaces;

import sapproject.project.models.Category;

import java.util.List;

public interface ICatagoryService {
    Category findCategoryByName(String name);

    List<Category> findAll();
}
