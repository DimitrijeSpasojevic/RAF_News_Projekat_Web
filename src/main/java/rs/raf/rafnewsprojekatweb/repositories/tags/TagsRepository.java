package rs.raf.rafnewsprojekatweb.repositories.tags;

import rs.raf.rafnewsprojekatweb.entities.Tag;

import java.util.List;

public interface TagsRepository {

    Tag addTag(Tag tag);

    void deleteAllOnNews(int newsId);

    List<Tag> getTagsOnNews(int newsId);
}
