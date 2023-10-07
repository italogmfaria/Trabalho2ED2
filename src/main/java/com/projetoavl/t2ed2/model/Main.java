package com.projetoavl.t2ed2.model;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        Scanner scanner = new Scanner(System.in);
        // Ler dados do arquivo "entrada.txt" e inserir na árvore
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/projetoavl/t2ed2/entrada.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int matricula = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                int faltas = Integer.parseInt(dados[2].trim());
                double nota1 = Double.parseDouble(dados[3].trim());
                double nota2 = Double.parseDouble(dados[4].trim());
                double nota3 = Double.parseDouble(dados[5].trim());

                Aluno aluno = new Aluno(matricula, nome, faltas, nota1, nota2, nota3);
                arvore.inserir(aluno);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de entrada: " + e.getMessage());
            return;
        }

        while (true) {
            // Exibir menu
            System.out.println("Menu:");
            System.out.println("1. Inserir aluno");
            System.out.println("2. Remover aluno");
            System.out.println("3. Buscar aluno por matrícula");
            System.out.println("4. Imprimir todos os alunos em ordem de matrícula");
            System.out.println("5. Encerrar programa e gravar em arquivo");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt(); // Leitura da opção usando Scanner

            switch (opcao) {
                case 1:
                    // Inserir aluno
                    System.out.print("Digite a matrícula do aluno: ");
                    int matricula = scanner.nextInt();

                    if (arvore.buscar(matricula) != null) {
                        System.out.println("\nMatrícula já existe. Não é possível inserir aluno com a mesma matrícula.\n");
                        break;
                    }

                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.print("Digite o nome do aluno: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o número de faltas: ");
                    int faltas = scanner.nextInt();
                    System.out.print("Digite o valor da primeira nota: ");
                    double nota1 = scanner.nextDouble();
                    System.out.print("Digite o valor da segunda nota: ");
                    double nota2 = scanner.nextDouble();
                    System.out.print("Digite o valor da terceira nota: ");
                    double nota3 = scanner.nextDouble();

                    Aluno novoAluno = new Aluno(matricula, nome, faltas, nota1, nota2, nota3);
                    arvore.inserir(novoAluno);
                    System.out.println("Aluno inserido com sucesso!");
                    break;
                case 2:
                    // Remover aluno
                    System.out.print("Digite a matrícula do aluno a ser removido: ");
                    int matriculaRemover = scanner.nextInt();
                    arvore.remover(matriculaRemover);
                    System.out.println("Aluno removido com sucesso!");
                    break;
                case 3:
                    // Buscar aluno por matrícula
                    System.out.print("Digite a matrícula do aluno a ser buscado: ");
                    int matriculaBuscar = scanner.nextInt();
                    Aluno alunoBuscado = arvore.buscar(matriculaBuscar);
                    if (alunoBuscado != null) {
                        System.out.println("Aluno encontrado:");
                        System.out.println(alunoBuscado.toString());
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                case 4:
                    // Imprimir todos os alunos em ordem de matrícula
                    arvore.imprimirEmOrdem();
                    break;
                case 5:
                    // Encerrar programa e gravar em arquivo
                    arvore.gravarEmArquivo("src/main/java/com/projetoavl/t2ed2/saida.txt");
                    System.out.println("Dados gravados em 'saida.txt'. Encerrando o programa.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}