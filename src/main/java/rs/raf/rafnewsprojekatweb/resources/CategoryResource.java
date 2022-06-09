package rs.raf.rafnewsprojekatweb.resources;

import rs.raf.rafnewsprojekatweb.entities.Category;
import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.services.CategoryService;
import rs.raf.rafnewsprojekatweb.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Path("offset/{offset}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> all(@PathParam("offset") Integer offset) {
        return categoryService.getALlCategories(offset);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> all() { return categoryService.getALlCategories(); }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category addCategory(@Valid Category category) {
        return this.categoryService.addCategory(category);
    }

    @DELETE
    @Path("/{name}")
    public void deleteCategory(@PathParam("name") String name){
        categoryService.deleteCategory(name);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category updateCategory(@Valid Category category){
        return categoryService.updateCategory(category);
    }

}
