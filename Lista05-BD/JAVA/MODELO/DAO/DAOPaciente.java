package MODELO.DAO;

import MODELO.ENTIDADE.Paciente;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOPaciente extends DAOAbstrato {
    
    public boolean pacienteSalvar(Paciente p) {
        String sql = "INSERT INTO Paciente (nome, cpf, doenca) VALUES (?, ?, ?)";
        boolean sucesso;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getDoenca());

            sucesso = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            sucesso = false;
        }
        
        return sucesso;
    }

    public Paciente pacienteBuscar(String cpf) {
        String sql = "SELECT * FROM Paciente WHERE cpf = ?";
        Paciente p = new Paciente();

        try (PreparedStatement stmt = conexao.prepareStatement(ordem)) {
            stmt.setString(1, cpf);
            ResultSet rS = stmt.executeQuery();

            if(rS.next()) {
                p.setIdPaciente(rS.getInt("idPaciente"));
                p.setNome(rS.getString("nome"));
                p.setCpf(rS.getString("cpf"));
                p.setDoenca(rS.getString("doenca"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }
}