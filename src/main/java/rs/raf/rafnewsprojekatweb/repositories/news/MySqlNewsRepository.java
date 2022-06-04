package rs.raf.rafnewsprojekatweb.repositories.news;

import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlNewsRepository extends MySqlAbstractRepository implements NewsRepository {
    @Override
    public List<News> getAll(int numOffset) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news LIMIT 5 OFFSET ?");
            preparedStatement.setInt(1, numOffset * 5);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                Date date = resultSet.getDate("date");
                Integer numberOfVisits = resultSet.getInt("number_of_visits");
                String author = resultSet.getString("author_email");
                String categoryName = resultSet.getString("category_name");
                News news1 = new News(id,title,text,date,numberOfVisits,author,categoryName);
                news.add(news1);
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

        return news;
    }

    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO news (title, text, date, " +
                    "number_of_visits, author_email, category_name) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getText());
            preparedStatement.setDate(3, news.getDate());
            preparedStatement.setInt(4, news.getNumberOfVisits());
            preparedStatement.setString(5, news.getAuthorEmail());
            preparedStatement.setString(6, news.getCategoryName());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }
}
