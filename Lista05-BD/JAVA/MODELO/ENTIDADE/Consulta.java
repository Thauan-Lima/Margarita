package MODELO.ENTIDADE;

import java.time.LocalDateTime;
import Visao.DateUtil.DateUtil;

public class Consulta {
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime horario;
    private double valor;

    @Override
    public String toString() {
        String informa = String.format("Nome do médico: %s (%d);\nNome do paciente: %s (%s);\nHorário da consulta: %s;\nValor da consulta: %f", medico.getMatricula(), medico.getMatricula(), paciente.getNome(), paciente.getCpf(), DateUtil.dateToString(horario), valor);
        return informa;
    }

    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico Medico) {
        this.medico = Medico;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente Paciente) {
        this.paciente = Paciente;
    }
    public LocalDateTime getHorario() {
        return horario;
    }
    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
}