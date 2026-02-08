package MODELO.DAO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import MODELO.ENTIDADE.Paciente;
import MODELO.ENTIDADE.Medico;
import MODELO.ENTIDADE.Consulta;

import java.util.ArrayList;

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

public ArrayList<Consulta> exibirAllConsultas() {
        String sql = "SELECT * FROM Consulta, Medico, Paciente";

        ArrayList<Consulta> consultas = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rS = stmt.executeQuery()) {
            while(rS.next()) {
                Consulta c = new Consulta();
                Medico m = new Medico();
                Paciente p = new Paciente();

                m.setNome(rS.getString("nome"));
                m.setMatricula(rS.getInt("matricula"));

                p.setNome(rS.getString("nome"));
                p.setCpf(rS.getString("cpf"));

                c.setMedico(m);
                c.setPaciente(p);
                c.setHorario(rS.getObject("horario", LocalDateTime.class));
                consulta.setValor(rS.getInt("valor"));

                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultas;        
    }