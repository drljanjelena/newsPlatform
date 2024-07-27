package com.example.projekatvebbek.repoitories.vesti;

import com.example.projekatvebbek.entities.Vest;
import com.example.projekatvebbek.repoitories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVestRepository extends MySqlAbstractRepository implements VestRepository {
    @Override
    public Vest addVest(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO vesti (naslov, tekst, datumKreiranja, autor, kategorijaID, brojLajkova, brojDislajkova, brojPoseta) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, vest.getNaslov());
            preparedStatement.setString(2, vest.getTekst());
            preparedStatement.setTimestamp(3, new Timestamp(vest.getDatumKreiranja().getTime()));
            preparedStatement.setInt(4, vest.getAutor());
            preparedStatement.setInt(5, vest.getKategorijaID());
            preparedStatement.setInt(6, vest.getBrojLajkova());
            preparedStatement.setInt(7, vest.getBrojDislajkova());
            preparedStatement.setInt(8, vest.getBrojPoseta());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vest.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vest;
    }

    @Override
    public List<Vest> allVesti() {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM vesti");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                Date datumKreiranja = resultSet.getDate("datumKreiranja");
                int autor = resultSet.getInt("autor");
                int kategorijaID = resultSet.getInt("kategorijaID");
                int brojLajkova = resultSet.getInt("brojLajkova");
                int brojDislajkova = resultSet.getInt("brojDislajkova");
                int brojPoseta = resultSet.getInt("brojPoseta");

                Vest vest = new Vest(id, naslov, tekst, datumKreiranja, autor, kategorijaID, brojLajkova, brojDislajkova, brojPoseta);
                vesti.add(vest);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

    @Override
    public Vest findVest(Integer id) {
        Vest vest = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vesti WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                Date datumKreiranja = resultSet.getDate("datumKreiranja");
                int autor = resultSet.getInt("autor");
                int kategorijaID = resultSet.getInt("kategorijaID");
                int brojLajkova = resultSet.getInt("brojLajkova");
                int brojDislajkova = resultSet.getInt("brojDislajkova");
                int brojPoseta = resultSet.getInt("brojPoseta");

                vest = new Vest(id, naslov, tekst, datumKreiranja, autor, kategorijaID, brojLajkova, brojDislajkova, brojPoseta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public void deleteVest(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM vesti WHERE id = ?");
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
    public Vest editVest(Integer id, Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            if (findVest(id) != null) {
                preparedStatement = connection.prepareStatement("UPDATE vesti SET naslov = ?, tekst = ?, datumKreiranja = ?, autor = ?, kategorijaID = ?, brojLajkova = ?, brojDislajkova = ?, brojPoseta = ? WHERE id = ?");
                preparedStatement.setString(1, vest.getNaslov());
                preparedStatement.setString(2, vest.getTekst());
                preparedStatement.setTimestamp(3, new Timestamp(vest.getDatumKreiranja().getTime()));
                preparedStatement.setInt(4, vest.getAutor());
                preparedStatement.setInt(5, vest.getKategorijaID());
                preparedStatement.setInt(6, vest.getBrojLajkova());
                preparedStatement.setInt(7, vest.getBrojDislajkova());
                preparedStatement.setInt(8, vest.getBrojPoseta());
                preparedStatement.setInt(9, vest.getId());
                preparedStatement.executeUpdate();
            } else {
                throw new RuntimeException("Vest sa ID-om " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vest;
    }

    @Override
    public List<Vest> getVestiByCategory(Integer kategorijaID) {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vesti WHERE kategorijaID = ?");
            preparedStatement.setInt(1, kategorijaID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                Date datumKreiranja = resultSet.getDate("datumKreiranja");
                int autor = resultSet.getInt("autor");
                int brojLajkova = resultSet.getInt("brojLajkova");
                int brojDislajkova = resultSet.getInt("brojDislajkova");
                int brojPoseta = resultSet.getInt("brojPoseta");

                Vest vest = new Vest(id, naslov, tekst, datumKreiranja, autor, kategorijaID, brojLajkova, brojDislajkova, brojPoseta);
                vesti.add(vest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

}
