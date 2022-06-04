package rs.raf.rafnewsprojekatweb.repositories.news;

import rs.raf.rafnewsprojekatweb.entities.News;

import java.util.List;

public interface NewsRepository {

    List<News> getAll(int offset);

    News addNews(News news);
}
