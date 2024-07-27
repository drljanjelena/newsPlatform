package com.example.projekatvebbek.repoitories.komentari;

import com.example.projekatvebbek.entities.Komentar;
import com.example.projekatvebbek.repoitories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlKomentarRepository extends MySqlAbstractRepository implements KomentarRepository {
    @Override
    public Komentar addKomentar(Komentar komentar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO komentari (imeAutora, tekst, datumKreiranja, korisnikId, vestId) VALUES(?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, komentar.getImeAutora());
            preparedStatement.setString(2, komentar.getTekst());
            preparedStatement.setTimestamp(3, new Timestamp(komentar.getDatumKreiranja().getTime()));
            preparedStatement.setInt(4, komentar.getKorisnikId());
            preparedStatement.setInt(5, komentar.getVestId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                komentar.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentar;
    }

    @Override
    public List<Komentar> allKomentari() {
        List<Komentar> komentari = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM komentari");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String imeAutora = resultSet.getString("imeAutora");
                String tekst = resultSet.getString("tekst");
                Timestamp datumKreiranja = resultSet.getTimestamp("datumKreiranja");
                Date datum = new Date(datumKreiranja.getTime());
                int korisnikId = resultSet.getInt("korisnikId");
                int vestId = resultSet.getInt("vestId");

                Komentar komentar = new Komentar(id, imeAutora, tekst, datum, korisnikId, vestId);
                komentari.add(komentar);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentari;
    }

    @Override
    public Komentar findKomentar(Integer id) {
        Komentar komentar = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM komentari WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String imeAutora = resultSet.getString("imeAutora");
                String tekst = resultSet.getString("tekst");
                Timestamp datumKreiranja = resultSet.getTimestamp("datumKreiranja");
                Date datum = new Date(datumKreiranja.getTime());
                int korisnikId = resultSet.getInt("korisnikId");
                int vestId = resultSet.getInt("vestId");

                komentar = new Komentar(id, imeAutora, tekst, datum, korisnikId, vestId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentar;
    }

    @Override
    public void deleteKomentar(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM komentari WHERE id = ?");
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
    public Komentar editKomentar(Integer id, Komentar komentar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            if (findKomentar(id) != null) {
                preparedStatement = connection.prepareStatement("UPDATE komentari SET imeAutora = ?, tekst = ?, datumKreiranja = ?, korisnikId = ?, vestId = ? WHERE id = ?");
                preparedStatement.setString(1, komentar.getImeAutora());
                preparedStatement.setString(2, komentar.getTekst());
                preparedStatement.setTimestamp(3, new Timestamp(komentar.getDatumKreiranja().getTime()));
                preparedStatement.setInt(4, komentar.getKorisnikId());
                preparedStatement.setInt(5, komentar.getVestId());
                preparedStatement.setInt(6, id);

                preparedStatement.executeUpdate();
            } else {
                throw new RuntimeException("Komentar sa ID-om " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return komentar;
    }

    @Override
    public List<Komentar> findByVest(Integer id) {
        List<Komentar> komentari = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM komentari WHERE vestId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int komentarId = resultSet.getInt("id");
                String imeAutora = resultSet.getString("imeAutora");
                String tekst = resultSet.getString("tekst");
                Timestamp datumKreiranja = resultSet.getTimestamp("datumKreiranja");
                Date datum = new Date(datumKreiranja.getTime());
                int korisnikId = resultSet.getInt("korisnikId");

                Komentar komentar = new Komentar(komentarId, imeAutora, tekst, datum, korisnikId, id);
                komentari.add(komentar);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return komentari;
    }
}