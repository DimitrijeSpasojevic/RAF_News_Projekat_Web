package rs.raf.rafnewsprojekatweb.repositories.categories;

import rs.raf.rafnewsprojekatweb.entities.Category;
import rs.raf.rafnewsprojekatweb.entities.News;

import java.util.List;

public interface CategoriesRepository {
    List<Category> getAll();

    Category addCategory(Category category);

    void deleteCategory(String name);

    Category updateCategory(Category category);

}
