package sapproject.project.services.interfaces;


import java.util.List;


public interface Service<T> {

    List<T> findAll();

    T getOne(int Id);

    T createOne(T entity);

    void deleteByID(int ID);

    T updateByID(int ID, T entity);
}
