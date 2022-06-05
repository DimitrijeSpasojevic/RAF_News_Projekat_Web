package rs.raf.rafnewsprojekatweb.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class News {

    private Integer id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String text;

    private Date date;

    private Integer numberOfVisits;

    @NotNull
    @NotEmpty
    private String authorEmail;

    @NotNull
    @NotEmpty
    private String categoryName;

    private List<String> keyWords = new ArrayList<>();

    public News(Integer id, String title, String text, Date date, Integer numberOfVisits, String authorEmail, String categoryName) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.numberOfVisits = numberOfVisits;
        this.authorEmail = authorEmail;
        this.categoryName = categoryName;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public News(String title, String text, Date date, Integer numberOfVisits, String authorEmail, String categoryName) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.numberOfVisits = numberOfVisits;
        this.authorEmail = authorEmail;
        this.categoryName = categoryName;
    }

    public News(String title, String text, String authorEmail, String categoryName) {
        this.title = title;
        this.text = text;
        this.authorEmail = authorEmail;
        this.categoryName = categoryName;
    }

    public News() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(Integer numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
