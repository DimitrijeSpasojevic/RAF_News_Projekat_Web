package rs.raf.rafnewsprojekatweb.repositories.categories;

import rs.raf.rafnewsprojekatweb.entities.Category;

import java.util.List;

public interface CategoriesRepository {
    List<Category> getAll(int pageNum);

    List<Category> getAll();

    Category addCategory(Category category);

    void deleteCategory(String name);

    Category updateCategory(Category category);

}
