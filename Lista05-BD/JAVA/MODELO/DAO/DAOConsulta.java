package MODELO.DAO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import MODELO.ENTIDADE.Paciente;
import MODELO.ENTIDADE.Medico;
import MODELO.ENTIDADE.Consulta;


public class DAOConsulta extends DAOAbstrato {
    public boolean consultaCadastrar(int matricula, String cpf, Consulta consulta) {
        String sql = "INSERT INTO Consulta (idMedico, idPaciente, horario, valor) VALUES ((SELECT idMedico FROM Medico WHERE matricula = ?), (SELECT idPaciente FROM Paciente WHERE cpf = ?), ?, ?)";
        boolean sucesso;

        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            stmt.setString(2, cpf);
            stmt.setObject(3, consulta.getHorario());
            stmt.setDouble(4, consulta.getValor());

            sucesso = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            sucesso = false;
        }
        return sucesso;
    }

public boolean consultaDeletar(int matricula, String cpf, Consulta consulta) {
        String sql = "DELETE FROM Consulta WHERE idMedico = (SELECT idMedico FROM Medico WHERE matricula = ?) AND idPaciente = (SELECT idPaciente FROM Paciente WHERE cpf = ?) AND horario = ?";
        boolean sucesso;

        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            stmt.setString(2, cpf);
            stmt.setObject(3, consulta.getHorario());

            sucesso = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            sucesso = false;
        }
        return sucesso;
    }

public boolean atualizarHorario(int matricula, String cpf, Consulta consulta, LocalDateTime novaData) {
        String sql = "UPDATE Consulta SET horario = ? WHERE idMedico = (SELECT idMedico FROM Medico WHERE matricula = ?) AND idPaciente = (SELECT idPaciente FROM Paciente WHERE cpf = ?) AND horario = ?";
        boolean sucesso;

        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setObject(1, novaData);
            stmt.setInt(2, matricula);
            stmt.setString(3, cpf);
            stmt.setObject(4, consulta.getHorario());

            sucesso = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            sucesso = false;
        }
        return sucesso;
    }