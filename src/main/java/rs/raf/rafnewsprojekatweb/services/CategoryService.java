package rs.raf.rafnewsprojekatweb.services;

import rs.raf.rafnewsprojekatweb.entities.Category;
import rs.raf.rafnewsprojekatweb.repositories.categories.CategoriesRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    CategoriesRepository categoriesRepository;

    public List<Category> getALlCategories(){
        return categoriesRepository.getAll();
    }

    public List<Category> getALlCategories(int pageNum){
        return categoriesRepository.getAll(pageNum);
    }

    public Category addCategory(Category category){
        return categoriesRepository.addCategory(category);
    }

    public Category updateCategory(Category category){
        return categoriesRepository.updateCategory(category);
    }

    public void deleteCategory(String name){
        categoriesRepository.deleteCategory(name);
    }

}
