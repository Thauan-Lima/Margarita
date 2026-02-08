package MODELO.ENTIDADE;

public class Medico {
    private int idMedico;
    private String nome;
    private int matricula;
    private String especialidade;
    private double salario;

    @Override
    public String toString() {
        String informa = String.format("ID do médico: %d;\nNome do médico: %s;\nMatricula do médico: %d;\nEspecialidade do médico: %s;\nSalário do médico: %f.", idMedico, nome, matricula, especialidade, salario);
        return informa;
    }

    public int getIdMedico() {
        return idMedico;
    }
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }     
}