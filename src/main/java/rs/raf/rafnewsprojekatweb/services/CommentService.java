package rs.raf.rafnewsprojekatweb.services;

import rs.raf.rafnewsprojekatweb.entities.Comment;
import rs.raf.rafnewsprojekatweb.repositories.comments.CommentRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class CommentService {

    @Inject
    CommentRepository commentRepository;

    public List<Comment> getAllCommentsOnNewsWithId(int id){
        return  commentRepository.getAllCommentsOnNewsWithId(id);
    }

    public Comment addComment(Comment comment){
        comment.setDate(java.sql.Date.valueOf(LocalDate.now()));
        return commentRepository.addCommentOnNews(comment);
    }

}
