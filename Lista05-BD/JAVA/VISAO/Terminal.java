package Visao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Entidade.*;
import Modelo.DAO.*;
import Visao.DateUtil.DateUtil;

public class Teste {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int resposta = 0, matricula = 0;
        String cpf = null;
        Medico medico = null;
        Paciente paciente = null;
        Consulta consulta = null;

        do {
            System.out.println("\n1. Sair do programa"); // ✓
            System.out.println("2. Cadastrar novo médico"); // ✓
            System.out.println("3. Cadastrar novo paciente"); // ✓
            System.out.println("4. Buscar médico por matrícula"); // ✓
            System.out.println("5. Buscar paciente por CPF"); // ✓
            System.out.println("6. Cadastrar uma nova consulta"); // ✓
            System.out.println("7. Remover uma consulta cadastrada"); // ✓
            System.out.println("8. Atualizar o horário de uma consulta cadastrada"); // ✓
            System.out.println("9. Gerar relatório de consultas"); // ✓
            System.out.print("Digite o número da opção:");
            resposta = scanner.nextInt();
            scanner.nextLine();

            switch (resposta) {
                case 1:
                    System.out.println("Programa encerrado.");
                    break;

                case 2:
                    medico = new Medico();

                    System.out.println("Informe o nome do médico");
                    medico.setNome(scanner.nextLine());

                    System.out.println("Informe a matricula do médico");
                    medico.setMatricula(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println("Informe a especialidade do médico");
                    medico.setEspecialidade(scanner.nextLine());
                    
                    System.out.println("Informe o salário do médico");
                    medico.setSalario(scanner.nextDouble());
                    
                    try (DAOMedico dM = new DAOMedico()) {
                        if(dM.salvarMedico(medico))
                            System.out.println("Médico foi salvo com sucesso.");
                        else
                            System.out.println("Erro ao salvar o médico.");
                    }
                    break;

                case 3:
                    paciente = new Paciente();
                        
                    System.out.println("Informe o nome do paciente");
                    paciente.setNome(scanner.nextLine());

                    System.out.println("Informe o CPF do paciente");
                    paciente.setCpf(scanner.nextLine());

                    System.out.println("Informe a doença do paciente");
                    paciente.setDoenca(scanner.nextLine());
                    
                    try (DAOPaciente dP = new DAOPaciente()) {
                        if(dP.salvarPaciente(paciente))
                            System.out.println("Paciente foi salvo com sucesso.");
                        else
                            System.out.println("Erro ao salvar o paciente.");
                    }
                    break;

                case 4:
                    System.out.println("Escreva a matrícula do médico:");
                    matricula = scanner.nextInt();
                    
                    try (DAOMedico dM = new DAOMedico()) {
                        medico = dM.buscarMedico(matricula);
                        if(medico != null)
                            System.out.println(medico);
                        else
                            System.out.println("Erro ao buscar o médico.");
                    }
                    break;

                case 5:
                    System.out.println("Escreva o CPF do paciente:");
                    cpf = scanner.nextLine();

                    try (DAOPaciente dP = new DAOPaciente()) {
                        paciente = dP.buscarPaciente(cpf);
                        if(paciente != null)
                            System.out.println(paciente);
                        else
                            System.out.println("Erro ao buscar o paciente.");
                    }
                    break;

                case 6:
                    consulta = new Consulta();
                        
                    System.out.println("Informe a matricula do médico");
                    matricula = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Informe o CPF do paciente");
                    cpf = scanner.nextLine();

                    System.out.println("Informe o horário da consulta (dd/MM/uuuu HH:mm:ss");
                    consulta.setHorario(DateUtil.stringToDate(scanner.nextLine(), LocalDateTime.class));

                    System.out.println("Informe o valor da consulta");
                    consulta.setValor(scanner.nextInt());
                    
                    try (DAOConsulta dC = new DAOConsulta()) {
                        if(dC.cadastrarConsulta(matricula, cpf, consulta))
                            System.out.println("Consulta cadastrada com sucesso.");
                        else
                            System.out.println("Erro ao salvar a consulta.");
                    }                    
                    break;

                case 7:
                    consulta = new Consulta();
                        
                    System.out.println("Informe a matricula do médico");
                    matricula = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Informe o CPF do paciente");
                    cpf = scanner.nextLine();
                    
                    System.out.println("Informe o horário da consulta");
                    consulta.setHorario(DateUtil.stringToDate(scanner.nextLine(), LocalDateTime.class));
                    
                    try (DAOConsulta dC = new DAOConsulta()) {
                        if(dC.deletarConsulta(matricula, cpf, consulta))
                            System.out.println("Consulta removida com sucesso.");
                        else
                            System.out.println("Erro ao remover a consulta.");
                    }                    
                    break;

                case 8:
                    consulta = new Consulta();
                        
                    System.out.println("Informe a matricula do médico");
                    matricula = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Informe o CPF do paciente");
                    cpf = scanner.nextLine();
                    System.out.println("Informe o antigo horário da consulta");
                    consulta.setHorario(DateUtil.stringToDate(scanner.nextLine(), LocalDateTime.class));
                    System.out.println("Informe o novo horário da consulta");
                    LocalDateTime lDT = DateUtil.stringToDate(scanner.nextLine(), LocalDateTime.class);
                    
                    try (DAOConsulta dC = new DAOConsulta()) {
                        if(dC.atualizarHorario(matricula, cpf, consulta, lDT))
                            System.out.println("Horário da consulta atualizado com sucesso.");
                        else
                            System.out.println("Erro ao atualizar o horário da consulta.");
                    }                    
                    break;
                
                case 9:
                    try (DAOConsulta dC = new DAOConsulta()) {
                        ArrayList<Consulta> consultas = dC.exibirTodasAsConsultas();
                        // Em teste
                        if(!(consultas.isEmpty()))
                            consultas.forEach(numreroConsulta -> {
                                int contadorDeConsultas = 1;
                                String exibirConsulta = String.format("Consulta %d:\n\t %s", contadorDeConsultas, numreroConsulta);
                                System.out.println(exibirConsulta);});
                        else
                            System.out.println("Nunhuma consulta encontrada.");
                    }                    
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }while (resposta != 1);
        scanner.close();
    }
}
