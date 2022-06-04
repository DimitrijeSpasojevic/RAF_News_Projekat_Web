package rs.raf.rafnewsprojekatweb.resources;

import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.entities.User;
import rs.raf.rafnewsprojekatweb.requests.LoginRequest;
import rs.raf.rafnewsprojekatweb.services.NewsService;
import rs.raf.rafnewsprojekatweb.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @GET
    @Path("offset/{offset}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> all(@PathParam("offset") Integer offset) {
        return newsService.getAll(offset);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News addNews(@Valid News news) {
        return this.newsService.addNews(news);
    }

}
