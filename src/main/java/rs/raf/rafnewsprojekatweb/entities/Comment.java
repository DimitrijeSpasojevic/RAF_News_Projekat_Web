package rs.raf.rafnewsprojekatweb.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class Comment {


    private Integer id;

    @NotNull
    @NotEmpty
    private String author;

    @NotNull
    @NotEmpty
    private String text;

    private Date date;

    @NotNull
    @NotEmpty
    private Integer newsId;

    public Comment(Integer id, String author, String text, Date date, Integer newsId) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.date = date;
        this.newsId = newsId;
    }

    public Comment(String author, String text, Integer newsId) {
        this.author = author;
        this.text = text;
        this.newsId = newsId;
    }

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}
