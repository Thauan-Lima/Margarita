package MODELO.DAO;

import Modelo.Entidade.Medico;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOMedico extends DAOAbstrato {
    
    public boolean salvarMedico (Medico medico) {
        String ordem = "INSERT INTO Medico (nome, matricula, especialidade, salario) VALUES (?, ?, ?, ?)";
        boolean sucesso;

        try (PreparedStatement pS = conexao.prepareStatement(ordem)) { 
            pS.setString(1, medico.getNome());
            pS.setInt(2, medico.getMatricula());
            pS.setString(3, medico.getEspecialidade());
            pS.setDouble(4, medico.getSalario());

            sucesso = pS.executeUpdate() == 1;
        } catch (SQLException e) {
            sucesso = false;
        }

        return sucesso;
    }

    public Medico buscarMedico(int matricula) {
        String ordem = "SELECT * FROM Medico WHERE matricula = ?";
        Medico medico = new Medico();

        try (PreparedStatement pS = conexao.prepareStatement(ordem)) {
            pS.setInt(1, matricula);
            ResultSet rS = pS.executeQuery();
            
            if (rS.next()) {
                medico.setIdMedico(rS.getInt("idMedico"));
                medico.setNome(rS.getString("nome"));
                medico.setMatricula(rS.getInt("matricula"));
                medico.setEspecialidade(rS.getString("especialidade"));
                medico.setSalario(rS.getInt("salario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medico;
    }
}