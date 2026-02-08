package MODELO.DAO;

import MODELO.ENTIDADE.Medico;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOMedico extends DAOAbstrato {
    
    public boolean medicoSalvar (Medico m) {
        String ordem = "INSERT INTO Medico (nome, matricula, especialidade, salario) VALUES (?, ?, ?, ?)";
        boolean sucesso;

        try (PreparedStatement stm = conexao.prepareStatement(ordem)) { 
            stm.setString(1, medico.getNome());
            stm.setInt(2, medico.getMatricula());
            stm.setString(3, medico.getEspecialidade());
            stm.setDouble(4, medico.getSalario());

            sucesso = stm.executeUpdate() == 1;
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