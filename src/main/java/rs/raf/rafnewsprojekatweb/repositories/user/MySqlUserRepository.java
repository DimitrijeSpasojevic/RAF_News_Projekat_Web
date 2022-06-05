package rs.raf.rafnewsprojekatweb.repositories.user;

import rs.raf.rafnewsprojekatweb.dto.UserUpdateDto;
import rs.raf.rafnewsprojekatweb.entities.User;
import rs.raf.rafnewsprojekatweb.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserRepository extends MySqlAbstractRepository implements UserRepository {
    @Override
    public User findUser(String email) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String userEmail = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String type = resultSet.getString("type");
                Boolean active = resultSet.getBoolean("active");
                String password = resultSet.getString("password");
                user = new User(userEmail,firstName,lastName,type,active,password);
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

        return user;
    }

    @Override
    public User addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO users (email, first_name, last_name, " +
                    "type, active, password) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setBoolean(5, user.getActive());
            preparedStatement.setString(6, user.getPassword());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public void delete(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM users where email = ?");
            preparedStatement.setString(1, email);
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
    public UserUpdateDto updateUser(UserUpdateDto userUpdateDto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE users SET email = ?, first_name= ?, last_name= ?" +
                    ", type= ?, active = ? WHERE id = ?");
            preparedStatement.setString(1, userUpdateDto.getEmail());
            preparedStatement.setString(2, userUpdateDto.getFirstName());
            preparedStatement.setString(3, userUpdateDto.getLastName());
            preparedStatement.setString(4, userUpdateDto.getType());
            preparedStatement.setBoolean(5, userUpdateDto.getActive());
            preparedStatement.setInt(6, userUpdateDto.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return userUpdateDto;
    }
}
