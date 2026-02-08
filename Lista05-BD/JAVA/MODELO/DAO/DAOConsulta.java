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