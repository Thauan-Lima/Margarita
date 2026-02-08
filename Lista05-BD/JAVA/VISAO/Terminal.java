package VISAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import MODELO.ENTIDADE.*;
import MODELO.DAO.*;
import VISAO.UTIL.DateUtil;

public class Terminal {

    public static void main(String[] args) throws Exception {

        Scanner read = new Scanner(System.in);

        int resposta = 0;
        int matricula = 0;
        String cpf = null;

        Medico medico = null;
        Paciente paciente = null;
        Consulta consulta = null;

        do {

            System.out.println("\n1. Sair do programa");
            System.out.println("2. Cadastrar novo médico");
            System.out.println("3. Cadastrar novo paciente");
            System.out.println("4. Buscar médico por matrícula");
            System.out.println("5. Buscar paciente por CPF");
            System.out.println("6. Cadastrar uma nova consulta");
            System.out.println("7. Remover uma consulta cadastrada");
            System.out.println("8. Atualizar o horário de uma consulta cadastrada");
            System.out.println("9. Gerar relatório de consultas");

            System.out.print("Digite o número da opção: ");
            resposta = read.nextInt();
            read.nextLine();

            switch (resposta) {

                case 1:
                    System.out.println("Programa encerrado.");
                    break;

                case 2:

                    medico = new Medico();

                    System.out.println("Informe o nome do médico:");
                    medico.setNome(read.nextLine());

                    System.out.println("Informe a matrícula do médico:");
                    medico.setMatricula(read.nextInt());
                    read.nextLine();

                    System.out.println("Informe a especialidade:");
                    medico.setEspecialidade(read.nextLine());

                    System.out.println("Informe o salário:");
                    medico.setSalario(read.nextDouble());
                    read.nextLine();

                    try (DAOMedico daoMedico = new DAOMedico()) {

                        if (daoMedico.medicoSalvar(medico))
                            System.out.println("Médico salvo com sucesso.");
                        else
                            System.out.println("Erro ao salvar médico.");
                    }

                    break;

                case 3:

                    paciente = new Paciente();

                    System.out.println("Informe o nome do paciente:");
                    paciente.setNome(read.nextLine());

                    System.out.println("Informe o CPF:");
                    paciente.setCpf(read.nextLine());

                    System.out.println("Informe a doença:");
                    paciente.setDoenca(read.nextLine());

                    try (DAOPaciente daoPaciente = new DAOPaciente()) {

                        if (daoPaciente.pacienteSalvar(paciente))
                            System.out.println("Paciente salvo com sucesso.");
                        else
                            System.out.println("Erro ao salvar paciente.");
                    }

                    break;

                case 4:

                    System.out.println("Informe a matrícula do médico:");
                    matricula = read.nextInt();
                    read.nextLine();

                    try (DAOMedico daoMedico = new DAOMedico()) {

                        medico = daoMedico.medicoBuscar(matricula);

                        if (medico != null)
                            System.out.println(medico);
                        else
                            System.out.println("Médico não encontrado.");
                    }

                    break;

                case 5:

                    System.out.println("Informe o CPF do paciente:");
                    cpf = read.nextLine();

                    try (DAOPaciente daoPaciente = new DAOPaciente()) {

                        paciente = daoPaciente.pacienteBuscar(cpf);

                        if (paciente != null)
                            System.out.println(paciente);
                        else
                            System.out.println("Paciente não encontrado.");
                    }

                    break;

                case 6:

                    consulta = new Consulta();

                    System.out.println("Matrícula do médico:");
                    matricula = read.nextInt();
                    read.nextLine();

                    System.out.println("CPF do paciente:");
                    cpf = read.nextLine();

                    System.out.println("Horário (dd/MM/uuuu HH:mm:ss):");
                    consulta.setHorario(
                            DateUtil.stringToDate(read.nextLine(), LocalDateTime.class)
                    );

                    System.out.println("Valor:");
                    consulta.setValor(read.nextDouble());
                    read.nextLine();

                    try (DAOConsulta daoConsulta = new DAOConsulta()) {

                        if (daoConsulta.consultaCadastrar(matricula, cpf, consulta))
                            System.out.println("Consulta cadastrada.");
                        else
                            System.out.println("Erro ao cadastrar consulta.");
                    }

                    break;

                case 7:

                    consulta = new Consulta();

                    System.out.println("Matrícula do médico:");
                    matricula = read.nextInt();
                    read.nextLine();

                    System.out.println("CPF do paciente:");
                    cpf = read.nextLine();

                    System.out.println("Horário:");
                    consulta.setHorario(
                            DateUtil.stringToDate(read.nextLine(), LocalDateTime.class)
                    );

                    try (DAOConsulta daoConsulta = new DAOConsulta()) {

                        if (daoConsulta.consultaDeletar(matricula, cpf, consulta))
                            System.out.println("Consulta removida.");
                        else
                            System.out.println("Erro ao remover consulta.");
                    }

                    break;

                case 8:

                    consulta = new Consulta();

                    System.out.println("Matrícula do médico:");
                    matricula = read.nextInt();
                    read.nextLine();

                    System.out.println("CPF do paciente
