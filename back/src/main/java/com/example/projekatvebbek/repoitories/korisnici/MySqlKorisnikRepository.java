package com.example.projekatvebbek.repoitories.korisnici;

import com.example.projekatvebbek.entities.Korisnik;
import com.example.projekatvebbek.repoitories.MySqlAbstractRepository;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlKorisnikRepository extends MySqlAbstractRepository implements KorisnikRepository {
    @Override
    public Korisnik addKorisnik(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            Korisnik existingKorisnik = findKorisnikByEmail(korisnik.getEmail());

            if(existingKorisnik == null) {

                String[] generatedColumns = {"id"};

                preparedStatement = connection.prepareStatement("INSERT INTO korisnici (ime, prezime, email, tipKorisnika, status, hashLozinke) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
                preparedStatement.setString(1, korisnik.getIme());
                preparedStatement.setString(2, korisnik.getPrezime());
                preparedStatement.setString(3, korisnik.getEmail());
                preparedStatement.setString(4, korisnik.getTipKorisnika());
                preparedStatement.setString(5, korisnik.getStatus());
                // Hesiranje lozinke ???????????
                String hashedPassword = DigestUtils.sha256Hex(korisnik.getHashLozinke());
                preparedStatement.setString(6, hashedPassword);

                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    korisnik.setId(resultSet.getInt(1));
                }
            }else {
                throw new RuntimeException("Vec postoji korisnik sa tim emailom");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnik;
    }

    @Override
    public List<Korisnik> allKorisnici() {
        List<Korisnik> korisnici = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM korisnici");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String email = resultSet.getString("email");
                String tipKorisnika = resultSet.getString("tipKorisnika");
                String status = resultSet.getString("status");
                String hashedPassword = resultSet.getString("hashLozinke");
                Korisnik korisnik = new Korisnik(id, ime, prezime, email, tipKorisnika, status, hashedPassword);
                korisnici.add(korisnik);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnici;
    }

    @Override
    public Korisnik findKorisnik(Integer id) {
        Korisnik korisnik = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String email = resultSet.getString("email");
                String tipKorisnika = resultSet.getString("tipKorisnika");
                String status = resultSet.getString("status");
                String hashedPassword = resultSet.getString("hashLozinke");

                korisnik = new Korisnik(id, ime, prezime, email, tipKorisnika, status, hashedPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnik;
    }

    @Override
    public void deleteKorisnik(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM korisnici WHERE id = ?");
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
    public Korisnik editKorisnik(Integer id, Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            if (findKorisnik(id) != null) {
                preparedStatement = connection.prepareStatement("UPDATE korisnici SET ime = ?, prezime = ?, email = ?, tipKorisnika = ?, status = ?, hashLozinke = ? WHERE id = ?");
                preparedStatement.setString(1, korisnik.getIme());
                preparedStatement.setString(2, korisnik.getPrezime());
                preparedStatement.setString(3, korisnik.getEmail());
                preparedStatement.setString(4, korisnik.getTipKorisnika());
                preparedStatement.setString(5, korisnik.getStatus());
                preparedStatement.setString(6, korisnik.getHashLozinke());
                preparedStatement.setInt(7, korisnik.getId());

                preparedStatement.executeUpdate();
            } else {
                throw new RuntimeException("Korisnik sa ID-om " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return korisnik;
    }

    @Override
    public Korisnik findKorisnikByEmail(String email) {
        Korisnik korisnik = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String tipKorisnika = resultSet.getString("tipKorisnika");
                String emailKorisnika = resultSet.getString("email");
                String status = resultSet.getString("status");
                String hashedPassword = resultSet.getString("hashLozinke");

                korisnik = new Korisnik(id, ime, prezime, emailKorisnika, tipKorisnika, status, hashedPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return korisnik;
    }
}