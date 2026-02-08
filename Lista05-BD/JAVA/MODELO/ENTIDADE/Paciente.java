package MODELO.ENTIDADE;

public class Paciente {
    private int idPaciente;
    private String nome;
    private String cpf;
    private String doenca;

    @Override
    public String toString() {
        String informa = String.format("ID do paciente: %d;\nNome do paciente: %s;\nCPF do paciente: %s;\nDoença do paciente: %s.", idPaciente, nome, cpf, doenca);
        return informa;
    }
    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getDoenca() {
        return doenca;
    }
    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }
}