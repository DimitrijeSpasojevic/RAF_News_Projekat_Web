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

    public Category addCategory(Category category){
        return categoriesRepository.addCategory(category);
    }

}
