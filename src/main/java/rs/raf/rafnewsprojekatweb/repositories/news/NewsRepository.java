package rs.raf.rafnewsprojekatweb.repositories.news;

import rs.raf.rafnewsprojekatweb.entities.News;

import java.util.List;

public interface NewsRepository {

    List<News> getAll(int offset);

    List<News> getMostVisitedForLastThirtyDays();

    List<News> getFirstTenByDate();

    News addNews(News news);

    List<News> getAllFromCategory(int page, String categoryName);

    void deleteNews(int id);

    News updateNews(News news);

    News updateNumberOfVisits(News news);

    News getNewsById(int id);

    List<News> getAllNewsWithTag(String keyWord);
}
