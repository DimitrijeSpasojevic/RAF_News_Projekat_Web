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
    public List<News> getMostVisitedForLastThirtyDays() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE date between DATE_SUB(CURDATE(), INTERVAL 30 DAY) and CURDATE() ORDER BY number_of_visits DESC LIMIT 10;");
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
    public List<News> getFirstTenByDate() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY date DESC LIMIT 10;");
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

    @Override
    public List<News> getAllFromCategory(int page, String categoryName) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news where category_name = ? LIMIT 5 OFFSET ?");
            preparedStatement.setString(1,categoryName);
            preparedStatement.setInt(2, page * 5);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                Date date = resultSet.getDate("date");
                Integer numberOfVisits = resultSet.getInt("number_of_visits");
                String author = resultSet.getString("author_email");
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
    public void deleteNews(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM news where id = ?");
            preparedStatement.setInt(1, id);
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
    public News updateNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection
                    .prepareStatement("UPDATE news SET title = ?, text = ?, author_email = ?," +
                    "category_name = ? WHERE id = ?", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getText());
            preparedStatement.setString(3, news.getAuthorEmail());
            preparedStatement.setString(4, news.getCategoryName());
            preparedStatement.setInt(5, news.getId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setTitle(resultSet.getString("title"));
                news.setText(resultSet.getString("text"));
                news.setAuthorEmail(resultSet.getString("author_email"));
                news.setCategoryName(resultSet.getString("category_name"));
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

    @Override
    public News updateNumberOfVisits(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection
                    .prepareStatement("UPDATE news SET number_of_visits = ? WHERE id = ?", generatedColumns);
            preparedStatement.setInt(1, news.getNumberOfVisits() + 1);
            preparedStatement.setInt(2, news.getId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setTitle(resultSet.getString("title"));
                news.setText(resultSet.getString("text"));
                news.setAuthorEmail(resultSet.getString("author_email"));
                news.setCategoryName(resultSet.getString("category_name"));
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

    @Override
    public News getNewsById(int id) {
        News news = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Integer newsId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                Date date = resultSet.getDate("date");
                Integer numberOfVisits = resultSet.getInt("number_of_visits");
                String author = resultSet.getString("author_email");
                String categoryName = resultSet.getString("category_name");
                news = new News(newsId,title,text,date,numberOfVisits,author,categoryName);
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
    public List<News> getAllNewsWithTag(String keyWord) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            //SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
            //FROM Orders
            //INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM news INNER JOIN tags ON news.id=tags.news_id WHERE tags.tag = ?");
            preparedStatement.setString(1, keyWord);
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
}
