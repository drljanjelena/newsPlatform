package com.example.projekatvebbek.repoitories.komentarReakcija;

import com.example.projekatvebbek.entities.KomentarReakcija;
import com.example.projekatvebbek.repoitories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlKomentarReakcijaRepository extends MySqlAbstractRepository implements KomentarReakcijaRepository {

    @Override
    public KomentarReakcija addKomentarReakcija(KomentarReakcija komentarReakcija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO komentari_reakcije (komentar_id, korisnik_id, tip_reakcije) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setInt(1, komentarReakcija.getKomentarId());
            preparedStatement.setInt(2, komentarReakcija.getKorisnikId());
            preparedStatement.setString(3, komentarReakcija.getTipReakcije());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                komentarReakcija.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return komentarReakcija;
    }

    @Override
    public List<KomentarReakcija> allKomentariReakcije() {
        List<KomentarReakcija> komentariReakcije = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM komentarireakcije");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int komentarId = resultSet.getInt("komentar_id");
                int korisnikId = resultSet.getInt("korisnik_id");
                String tipReakcije = resultSet.getString("tip_reakcije");

                KomentarReakcija komentarReakcija = new KomentarReakcija(id, komentarId, korisnikId, tipReakcije);
                komentariReakcije.add(komentarReakcija);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentariReakcije;
    }

    @Override
    public KomentarReakcija findKomentarReakcija(Integer id) {
        KomentarReakcija komentarReakcija = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM komentarireakcije WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int komentarId = resultSet.getInt("komentar_id");
                int korisnikId = resultSet.getInt("korisnik_id");
                String tipReakcije = resultSet.getString("tip_reakcije");

                komentarReakcija = new KomentarReakcija(id, komentarId, korisnikId, tipReakcije);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentarReakcija;
    }

    @Override
    public void deleteKomentarReakcija(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM komentarireakcije WHERE id = ?");
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
    public KomentarReakcija editKomentarReakcija(Integer id, KomentarReakcija komentarReakcija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            if (findKomentarReakcija(id) != null) {
                preparedStatement = connection.prepareStatement("UPDATE komentarireakcije SET komentar_id = ?, korisnik_id = ?, tip_reakcije = ? WHERE id = ?");
                preparedStatement.setInt(1, komentarReakcija.getKomentarId());
                preparedStatement.setInt(2, komentarReakcija.getKorisnikId());
                preparedStatement.setString(3, komentarReakcija.getTipReakcije());
                preparedStatement.setInt(4, komentarReakcija.getId());
                preparedStatement.executeUpdate();
            } else {
                throw new RuntimeException("KomentarReakcija sa ID-om " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return komentarReakcija;
    }
}
