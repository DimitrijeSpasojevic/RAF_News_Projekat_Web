package rs.raf.rafnewsprojekatweb.services;


import rs.raf.rafnewsprojekatweb.entities.Tag;
import rs.raf.rafnewsprojekatweb.repositories.tags.TagsRepository;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class TagService {

    @Inject
    TagsRepository tagsRepository;

    public List<Tag> addTags(List<String> keyWords, int newsId){
        if(keyWords.size() == 0) return null;

        List<Tag> tags = new ArrayList<>();
        for(String keyWord : keyWords){
          tags.add(tagsRepository.addTag( new Tag(keyWord,newsId)) );
        }
        return tags;
    }

    public void deleteAllTagsOnNews(int newsId){
        tagsRepository.deleteAllOnNews(newsId);
    }

    public List<Tag> deleteAndInsertAll(List<String> keyWords, int newsId){
        deleteAllTagsOnNews(newsId);
        if(keyWords.size() == 0)
            return null;
        return addTags(keyWords,newsId);
    }

    public List<Tag> getAllTagsOnNews(int newsId){
       return tagsRepository.getTagsOnNews(newsId);
    }


}
