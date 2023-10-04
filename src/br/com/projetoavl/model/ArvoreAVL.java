package br.com.projetoavl.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class No {
    public Aluno aluno;
    public No esquerda;
    public No direita;
    public int altura;

    public No(Aluno aluno) {
        this.aluno = aluno;
        this.altura = 1;
    }
}

public class ArvoreAVL {
    private No raiz;

    // Construtor
    public ArvoreAVL() {
        raiz = null;
    }

    // Método para calcular a altura de um nó
    private int altura(No no) {
        if (no == null)
            return 0;
        return no.altura;
    }

    // Método para calcular o fator de balanceamento de um nó
    private int fatorBalanceamento(No no) {
        if (no == null)
            return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    // Método para atualizar a altura de um nó
    private void atualizarAltura(No no) {
        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
    }

    // Método para realizar a rotação à direita
    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        // Realizar a rotação
        x.direita = y;
        y.esquerda = T2;

        // Atualizar alturas
        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    // Método para realizar a rotação à esquerda
    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        // Realizar a rotação
        y.esquerda = x;
        x.direita = T2;

        // Atualizar alturas
        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    // Método para inserir um aluno na árvore AVL
    public void inserir(Aluno aluno) {
        raiz = inserirRec(raiz, aluno);
    }

    private No inserirRec(No no, Aluno aluno) {
        if (no == null)
            return new No(aluno);

        if (aluno.getMatricula() < no.aluno.getMatricula())
            no.esquerda = inserirRec(no.esquerda, aluno);
        else if (aluno.getMatricula() > no.aluno.getMatricula())
            no.direita = inserirRec(no.direita, aluno);
        else
            return no; // Não permite matrículas iguais

        // Atualizar a altura do nó atual
        atualizarAltura(no);

        // Obter o fator de balanceamento do nó
        int balanceamento = fatorBalanceamento(no);

        // Realizar rotações, se necessário
        if (balanceamento > 1) {
            if (aluno.getMatricula() < no.esquerda.aluno.getMatricula())
                return rotacaoDireita(no);
            else {
                no.esquerda = rotacaoEsquerda(no.esquerda);
                return rotacaoDireita(no);
            }
        }
        if (balanceamento < -1) {
            if (aluno.getMatricula() > no.direita.aluno.getMatricula())
                return rotacaoEsquerda(no);
            else {
                no.direita = rotacaoDireita(no.direita);
                return rotacaoEsquerda(no);
            }
        }

        return no;
    }

    // Método para remover um aluno da árvore AVL
    public void remover(int matricula) {
        raiz = removerRec(raiz, matricula);
    }

    private No removerRec(No no, int matricula) {
        if (no == null)
            return no;

        if (matricula < no.aluno.getMatricula())
            no.esquerda = removerRec(no.esquerda, matricula);
        else if (matricula > no.aluno.getMatricula())
            no.direita = removerRec(no.direita, matricula);
        else {
            if ((no.esquerda == null) || (no.direita == null)) {
                No temp = null;
                if (temp == no.esquerda)
                    temp = no.direita;
                else
                    temp = no.esquerda;

                if (temp == null) {
                    temp = no;
                    no = null;
                } else
                    no = temp;
            } else {
                No temp = minValueNode(no.direita);
                no.aluno = temp.aluno;
                no.direita = removerRec(no.direita, temp.aluno.getMatricula());
            }
        }

        if (no == null)
            return no;

        atualizarAltura(no);

        int balanceamento = fatorBalanceamento(no);

        if (balanceamento > 1) {
            if (fatorBalanceamento(no.esquerda) >= 0)
                return rotacaoDireita(no);
            else {
                no.esquerda = rotacaoEsquerda(no.esquerda);
                return rotacaoDireita(no);
            }
        }

        if (balanceamento < -1) {
            if (fatorBalanceamento(no.direita) <= 0)
                return rotacaoEsquerda(no);
            else {
                no.direita = rotacaoDireita(no.direita);
                return rotacaoEsquerda(no);
            }
        }

        return no;
    }

    private No minValueNode(No node) {
        No current = node;
        while (current.esquerda != null)
            current = current.esquerda;
        return current;
    }

    // Método para buscar um aluno por matrícula
    public Aluno buscar(int matricula) {
        return buscarRec(raiz, matricula);
    }

    private Aluno buscarRec(No no, int matricula) {
        if (no == null)
            return null;

        if (matricula == no.aluno.getMatricula())
            return no.aluno;

        if (matricula < no.aluno.getMatricula())
            return buscarRec(no.esquerda, matricula);

        return buscarRec(no.direita, matricula);
    }

    // Método para imprimir todos os alunos em ordem de matrícula
    public void imprimirEmOrdem() {
        imprimirEmOrdemRec(raiz);
    }

    private void imprimirEmOrdemRec(No no) {
        if (no != null) {
            imprimirEmOrdemRec(no.esquerda);
            System.out.println(no.aluno.toString());
            imprimirEmOrdemRec(no.direita);
        }
    }

    // Método para gravar os dados em um arquivo
    public void gravarEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            gravarEmArquivoRec(writer, raiz);
        } catch (IOException e) {
            System.out.println("Erro ao gravar em arquivo: " + e.getMessage());
        }
    }

    private void gravarEmArquivoRec(BufferedWriter writer, No no) throws IOException {
        if (no != null) {
            gravarEmArquivoRec(writer, no.esquerda);
            writer.write(no.aluno.toString());
            writer.newLine();
            gravarEmArquivoRec(writer, no.direita);
        }
    }
}

