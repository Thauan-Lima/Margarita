package MODELO.DAO;

import MODELO.ENTIDADE.Medico;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOMedico extends DAOAbstrato {
    
    public boolean medicoSalvar (Medico m) {
        String sql = "INSERT INTO Medico (nome, matricula, especialidade, salario) VALUES (?, ?, ?, ?)";
        boolean sucesso;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) { 
            stmt.setString(1, medico.getNome());
            stmt.setInt(2, medico.getMatricula());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setDouble(4, medico.getSalario());

            sucesso = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            sucesso = false;
        }

        return sucesso;
    }

    public Medico medicoBuscar(int matricula) {
        String sql = "SELECT * FROM Medico WHERE matricula = ?";
        Medico m = new Medico();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            pS.setInt(1, matricula);
            ResultSet rS = stmt.executeQuery();
            
            if (rS.next()) {
                m.setIdMedico(rS.getInt("idMedico"));
                m.setNome(rS.getString("nome"));
                m.setMatricula(rS.getInt("matricula"));
                m.setEspecialidade(rS.getString("especialidade"));
                m.setSalario(rS.getInt("salario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }
}