package com.example.projekatvebbek.repoitories.tag;

import com.example.projekatvebbek.entities.Tag;
import com.example.projekatvebbek.repoitories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository {
    @Override
    public Tag addTag(Tag tag) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO tagovi (naziv) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag.getNaziv());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tag;
    }

    @Override
    public List<Tag> allTag() {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tagovi");
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("id"), resultSet.getString("naziv")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public Tag findTag(Integer id) {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tagovi where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int tagId = resultSet.getInt("id");
                String naziv = resultSet.getString("naziv");
                tag = new Tag(tagId, naziv);
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

        return tag;
    }

    @Override
    public void deleteTag(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tagovi where id = ?");
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
    public Tag editTag(Integer id, Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            if (findTag(id) != null) {
                preparedStatement = connection.prepareStatement("UPDATE tagovi SET naziv = ? WHERE id = ?");
                preparedStatement.setString(1, tag.getNaziv());
                preparedStatement.setInt(2, tag.getId());
                preparedStatement.executeUpdate();
            } else {
                throw new RuntimeException("Tag sa ID-om " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return tag;
    }

}
