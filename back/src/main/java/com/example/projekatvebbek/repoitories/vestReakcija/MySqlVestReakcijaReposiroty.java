package com.example.projekatvebbek.repoitories.vestReakcija;

import com.example.projekatvebbek.entities.VestReakcija;
import com.example.projekatvebbek.repoitories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVestReakcijaReposiroty extends MySqlAbstractRepository implements VestReakcijaReposiroty {
    @Override
    public VestReakcija addVestReakciju(VestReakcija vestReakcija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO vestireakcije (vest_id, korisnik_id, reakcija_tip) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setInt(1, vestReakcija.getVestId());
            preparedStatement.setInt(2, vestReakcija.getKorisnikId());
            preparedStatement.setString(3, vestReakcija.getTipReakcije());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vestReakcija.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return vestReakcija;
    }

    @Override
    public List<VestReakcija> allVestReakcije() {
        List<VestReakcija> vestiReakcije = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM vestireakcije");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int vestId = resultSet.getInt("vest_id");
                int korisnikId = resultSet.getInt("korisnik_id");
                String reakcijaTip = resultSet.getString("reakcija_tip");

                VestReakcija vestReakcija = new VestReakcija(id, vestId, korisnikId, reakcijaTip);
                vestiReakcije.add(vestReakcija);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vestiReakcije;
    }

    @Override
    public VestReakcija findVestReakcija(Integer id) {
        VestReakcija vestReakcija = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vestireakcije WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int vestId = resultSet.getInt("vest_id");
                int korisnikId = resultSet.getInt("korisnik_id");
                String reakcijaTip = resultSet.getString("reakcija_tip");

                vestReakcija = new VestReakcija(id, vestId, korisnikId, reakcijaTip);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vestReakcija;
    }

    @Override
    public void deleteVestReakcija(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM vestireakcije WHERE id = ?");
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
    public VestReakcija editVestReakcija(Integer id, VestReakcija vestReakcija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            if (findVestReakcija(id) != null) {
                preparedStatement = connection.prepareStatement("UPDATE vestireakcije SET vest_id = ?, korisnik_id = ?, reakcija_tip = ? WHERE id = ?");
                preparedStatement.setInt(1, vestReakcija.getVestId());
                preparedStatement.setInt(2, vestReakcija.getKorisnikId());
                preparedStatement.setString(3, vestReakcija.getTipReakcije());
                preparedStatement.setInt(4, vestReakcija.getId());
                preparedStatement.executeUpdate();
            } else {
                throw new RuntimeException("VestReakcija sa ID-om " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vestReakcija;
    }
}