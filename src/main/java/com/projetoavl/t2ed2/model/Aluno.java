package com.projetoavl.t2ed2.model;

import java.text.DecimalFormat;

public class Aluno {
    private int matricula;
    private String nome;
    private int faltas;
    private double nota1;
    private double nota2;
    private double nota3;
    private double media;

    // Construtor
    public Aluno(int matricula, String nome, int faltas, double nota1, double nota2, double nota3) {
        this.matricula = matricula;
        this.nome = nome;
        this.faltas = faltas;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.media = calcularMedia();
    }

    // Getters e Setters

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getFaltas() {
        return faltas;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public double getMedia() {
        return media;
    }

    // Método para calcular a média
    private double calcularMedia() {
        return nota1 * 0.2 + nota2 * 0.35 + nota3 * 0.45;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "Matrícula: " + matricula + ", Nome: " + nome + ", Faltas: " + faltas +
                ", Nota1: " + nota1 + ", Nota2: " + nota2 + ", Nota3: " + nota3 +
                ", Média: " + df.format(media);
    }

}