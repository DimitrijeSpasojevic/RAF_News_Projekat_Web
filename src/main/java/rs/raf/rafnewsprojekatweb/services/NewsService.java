package rs.raf.rafnewsprojekatweb.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.entities.User;
import rs.raf.rafnewsprojekatweb.repositories.news.NewsRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class NewsService {

    @Inject
    NewsRepository newsRepository;

    public News addNews(News news){
        news.setDate(java.sql.Date.valueOf(LocalDate.now()));
        news.setNumberOfVisits(0);
       return newsRepository.addNews(news);
    }

    public List<News> getAll(int offset){
        return newsRepository.getAll(offset);
    }
}
