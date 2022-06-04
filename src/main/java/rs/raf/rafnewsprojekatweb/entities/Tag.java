package rs.raf.rafnewsprojekatweb.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {

    @NotNull
    @NotEmpty
    private String keyWord;

    @NotNull
    private Integer newsId;

    public Tag(String keyWord, Integer newsId) {
        this.keyWord = keyWord;
        this.newsId = newsId;
    }

    public Tag() {
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}
