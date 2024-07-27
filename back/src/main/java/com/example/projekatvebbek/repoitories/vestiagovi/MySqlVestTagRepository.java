package com.example.projekatvebbek.repoitories.vestiagovi;

import com.example.projekatvebbek.entities.VestTag;
import com.example.projekatvebbek.repoitories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVestTagRepository extends MySqlAbstractRepository implements VestTagRepository {
    @Override
    public VestTag addVestTag(VestTag vestTag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO vestitagovi (vestId, tagId) VALUES(?, ?)", generatedColumns);
            preparedStatement.setInt(1, vestTag.getVestId());
            preparedStatement.setInt(2, vestTag.getTagId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vestTag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vestTag;
    }

    @Override
    public List<VestTag> allVestTag() {
        List<VestTag> vestTags = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM vestitagovi");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int vestId = resultSet.getInt("vestId");
                int tagId = resultSet.getInt("tagId");

                VestTag vestTag = new VestTag(id, vestId, tagId);
                vestTags.add(vestTag);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vestTags;
    }

    @Override
    public VestTag findVestTag(Integer id) {
        VestTag vestTag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vestitagovi WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int vestId = resultSet.getInt("vestId");
                int tagId = resultSet.getInt("tagId");

                vestTag = new VestTag(id, vestId, tagId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vestTag;
    }

    @Override
    public void deleteVestTag(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM vestitagovi WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public VestTag editVestTag(Integer id, VestTag vestTag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            if (findVestTag(id) != null) {
                preparedStatement = connection.prepareStatement("UPDATE vestitagovi SET vestId = ?, tagId = ? WHERE id = ?");
                preparedStatement.setInt(1, vestTag.getVestId());
                preparedStatement.setInt(2, vestTag.getTagId());
                preparedStatement.setInt(3, id);

                preparedStatement.executeUpdate();
            } else {
                throw new RuntimeException("VestTag sa ID-om " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vestTag;
    }
}
