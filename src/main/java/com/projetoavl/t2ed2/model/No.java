package com.projetoavl.t2ed2.model;

public class No {
    public Aluno aluno;
    public No esquerda;
    public No direita;
    public int altura;

    public No(Aluno aluno) {
        this.aluno = aluno;
        this.altura = 1;
    }
}
