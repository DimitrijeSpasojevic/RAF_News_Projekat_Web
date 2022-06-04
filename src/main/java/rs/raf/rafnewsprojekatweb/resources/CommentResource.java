package rs.raf.rafnewsprojekatweb.resources;

import rs.raf.rafnewsprojekatweb.entities.Comment;
import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.services.CommentService;
import rs.raf.rafnewsprojekatweb.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Path("/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> allOnNewsWithId(@PathParam("newsId") Integer newsId) {
        return commentService.getAllCommentsOnNewsWithId(newsId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment addComment(@Valid Comment comment) {
        return this.commentService.addComment(comment);
    }

}
