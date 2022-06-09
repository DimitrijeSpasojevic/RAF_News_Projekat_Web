package rs.raf.rafnewsprojekatweb.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.entities.Tag;
import rs.raf.rafnewsprojekatweb.entities.User;
import rs.raf.rafnewsprojekatweb.repositories.news.NewsRepository;
import rs.raf.rafnewsprojekatweb.repositories.tags.TagsRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewsService {

    @Inject
    NewsRepository newsRepository;
    @Inject
    TagsRepository tagsRepository;

    public News addNews(News news){
        news.setDate(java.sql.Date.valueOf(LocalDate.now()));
        news.setNumberOfVisits(0);
       return newsRepository.addNews(news);
    }

    public List<News> getAll(int offset){
        List<News> newsList = newsRepository.getAll(offset);

        for(News news: newsList){
            List<Tag> tags = tagsRepository.getTagsOnNews(news.getId());
            List<String> keyWords = tags.stream().map(Tag::getKeyWord).collect(Collectors.toList());
            news.setKeyWords(keyWords);
        }

        List<News> newsListSorted = newsList.stream().sorted(Comparator.comparing(News::getDate)).collect(Collectors.toList());
        return newsListSorted;
    }

    public List<News> getAllFromCategory(int page, String categoryName){
        return newsRepository.getAllFromCategory(page,categoryName);
    }

    public void deleteNews(int id){
        newsRepository.deleteNews(id);
    }

    public News updateNews(News news){
        News newsFromDb = newsRepository.getNewsById(news.getId());
        news.setDate(newsFromDb.getDate());
        news.setNumberOfVisits(newsFromDb.getNumberOfVisits());
        return newsRepository.updateNews(news);
    }

    public News getNewsById(int id){
        return newsRepository.getNewsById(id);
    }

    public List<News> getAllNewsWithTag(String keyWord){
        List<News> newsList = newsRepository.getAllNewsWithTag(keyWord);

        for(News news: newsList){
            List<Tag> tags = tagsRepository.getTagsOnNews(news.getId());
            List<String> keyWords = tags.stream().map(Tag::getKeyWord).collect(Collectors.toList());
            news.setKeyWords(keyWords);
        }
       return newsList;
    }
}
