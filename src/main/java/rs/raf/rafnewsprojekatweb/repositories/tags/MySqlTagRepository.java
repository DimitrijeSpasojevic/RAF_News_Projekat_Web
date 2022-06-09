package rs.raf.rafnewsprojekatweb.repositories.tags;

import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.entities.Tag;
import rs.raf.rafnewsprojekatweb.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagsRepository {
    @Override
    public Tag addTag(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("INSERT INTO tags (tag, news_id) VALUES(?, ?)");
            preparedStatement.setString(1, tag.getKeyWord());
            preparedStatement.setInt(2, tag.getNewsId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return tag;
    }

    @Override
    public void deleteAllOnNews(int newsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tags where news_id = ?");
            preparedStatement.setInt(1, newsId);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Tag> getTagsOnNews(int newsId) {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags where news_id = ?");
            preparedStatement.setInt(1, newsId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String keyWord = resultSet.getString("tag");
                Integer myNewsId = resultSet.getInt("news_id");
                Tag tag = new Tag(keyWord,myNewsId);
                tags.add(tag);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }
}
