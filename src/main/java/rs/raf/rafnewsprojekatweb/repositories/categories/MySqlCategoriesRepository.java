package rs.raf.rafnewsprojekatweb.repositories.categories;

import rs.raf.rafnewsprojekatweb.entities.Category;
import rs.raf.rafnewsprojekatweb.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategoriesRepository extends MySqlAbstractRepository implements CategoriesRepository {

    @Override
    public List<Category> getAll(int pageNum) {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("SELECT * FROM categories LIMIT 5 OFFSET ?", generatedColumns);
            preparedStatement.setInt(1, pageNum * 5);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                Category category = new Category(name,description);
                category.setId(resultSet.getInt(3));
                categories.add(category);
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

        return categories;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("SELECT * FROM categories", generatedColumns);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                Category category = new Category(name,description);
                category.setId(resultSet.getInt(3));
                categories.add(category);
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

        return categories;
    }

    @Override
    public Category addCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO categories (name, description) VALUES(?, ?)" ,generatedColumns);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return category;
    }

    @Override
    public void deleteCategory(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM categories where name = ?");
            preparedStatement.setString(1, name);
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
    public Category updateCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE categories SET name = ?, description= ? WHERE id = ?" );
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, category.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return category;
    }
}
