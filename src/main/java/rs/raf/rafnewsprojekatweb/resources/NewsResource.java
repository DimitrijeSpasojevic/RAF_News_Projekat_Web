package rs.raf.rafnewsprojekatweb.resources;

import rs.raf.rafnewsprojekatweb.entities.Category;
import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.entities.Tag;
import rs.raf.rafnewsprojekatweb.services.NewsService;
import rs.raf.rafnewsprojekatweb.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;
    @Inject
    private TagService tagService;

    @GET
    @Path("offset/{offset}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> all(@PathParam("offset") Integer offset) {
        return newsService.getAll(offset);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News addNews(@Valid News news) {
        News news1 = this.newsService.addNews(news);
        tagService.addTags(news.getKeyWords(), news1.getId());
        news1.setKeyWords(news.getKeyWords());
        return news1;
    }

    @GET
    @Path("category/{categoryName}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> all(@PathParam("categoryName") String categoryName, @PathParam("page") Integer page) {
        return newsService.getAllFromCategory(page,categoryName);
    }

    @GET
    @Path("tags/{keyWord}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNewsWithTag(@PathParam("keyWord") String keyWord) {
        return newsService.getAllNewsWithTag(keyWord);
    }

    @GET
    @Path("/{newsId}/tags")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getAllTagsOnNews(@PathParam("newsId") Integer newsId) {
        return tagService.getAllTagsOnNews(newsId);
    }


    @DELETE
    @Path("/{id}")
    public void deleteNews(@PathParam("id") Integer id){
        newsService.deleteNews(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public News updateNews(@Valid News news){
        tagService.deleteAndInsertAll(news.getKeyWords(),news.getId());
        return newsService.updateNews(news);
    }


}
