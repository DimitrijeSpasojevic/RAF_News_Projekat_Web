package rs.raf.rafnewsprojekatweb.repositories.comments;

import rs.raf.rafnewsprojekatweb.entities.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAllCommentsOnNewsWithId(int id);

    Comment addCommentOnNews(Comment comment);
}
