package com.example.projekatvebbek.repoitories.kategorije;

import com.example.projekatvebbek.entities.Kategorija;
import com.example.projekatvebbek.repoitories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlKategorijaRepository extends MySqlAbstractRepository implements KategorijaRepository {
    @Override
    public Kategorija addKategorija(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

                Kategorija existingKategorija = findKategorijuByIme(kategorija.getIme());

                if (existingKategorija == null) {
                    String[] generatedColumns = {"id"};

                    preparedStatement = connection.prepareStatement("INSERT INTO kategorije (ime,opis) VALUES(?,?)", generatedColumns);
                    preparedStatement.setString(1, kategorija.getIme());
                    preparedStatement.setString(2, kategorija.getOpis());
                    preparedStatement.executeUpdate();
                    resultSet = preparedStatement.getGeneratedKeys();

                    if (resultSet.next()) {
                        kategorija.setId(resultSet.getInt(1));
                    }
                }else {
                    throw new RuntimeException("Kategorija sa istim imenom već postoji.");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return kategorija;
    }

    @Override
    public List<Kategorija> allKategorije() {
        List<Kategorija> kategorije = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije");
            while (resultSet.next()) {
                kategorije.add(new Kategorija(resultSet.getInt("id"), resultSet.getString("ime"),resultSet.getString("opis")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorije;
    }

    @Override
    public Kategorija findKategoriju(Integer id) {
        Kategorija kategorija = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int kategorijaId = resultSet.getInt("id");
                String ime = resultSet.getString("ime");
                String opis = resultSet.getString("opis");
                kategorija = new Kategorija(kategorijaId, ime, opis);
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

        if(kategorija == null){
            System.out.println("ne postoji kategorija, sa prosledjenim imenom");
            throw new RuntimeException("ne postoji kategorija, sa prosledjenim imenom");
        }
        return kategorija;
    }

    @Override
    public void deleteKategoriju(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM kategorije where id = ?");
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
    public Kategorija editKategorija(Integer id,Kategorija kategorija) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            if (findKategoriju(id) != null) {
                preparedStatement = connection.prepareStatement("UPDATE kategorije SET ime = ?, opis = ? WHERE id = ?");
                preparedStatement.setString(1, kategorija.getIme());
                preparedStatement.setString(2, kategorija.getOpis());
                preparedStatement.setInt(3, kategorija.getId());
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
        return kategorija;
    }

    @Override
    public Kategorija findKategorijuByIme(String ime) {
        Kategorija kategorija = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije where ime = ?");
            preparedStatement.setString(1, ime);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int kategorijaId = resultSet.getInt("id");
                String kategorijaIme = resultSet.getString("ime");
                String opis = resultSet.getString("opis");
                kategorija = new Kategorija(kategorijaId, kategorijaIme, opis);
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

        return kategorija;
    }
}
