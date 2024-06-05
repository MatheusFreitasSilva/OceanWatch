package org.example.entities.dtos;

import org.example.entities._BaseEntity;

public class PoluicaoAguasCidades extends _BaseEntity {
    private String cidade;
    private String regiao;
    private String entidade;
    private double qualidadeDoAr;
    private double poluicaoDaAgua;

    public PoluicaoAguasCidades(){}

    public PoluicaoAguasCidades(String cidade, String regiao, String entidade, double qualidadeDoAr, double poluicaoDaAgua) {
        this.cidade = cidade;
        this.regiao = regiao;
        this.entidade = entidade;
        this.qualidadeDoAr = qualidadeDoAr;
        this.poluicaoDaAgua = poluicaoDaAgua;
    }

    public PoluicaoAguasCidades(int id, String cidade, String regiao, String entidade, double qualidadeDoAr, double poluicaoDaAgua) {
        super(id);
        this.cidade = cidade;
        this.regiao = regiao;
        this.entidade = entidade;
        this.qualidadeDoAr = qualidadeDoAr;
        this.poluicaoDaAgua = poluicaoDaAgua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public double getQualidadeDoAr() {
        return qualidadeDoAr;
    }

    public void setQualidadeDoAr(double qualidadeDoAr) {
        this.qualidadeDoAr = qualidadeDoAr;
    }

    public double getPoluicaoDaAgua() {
        return poluicaoDaAgua;
    }

    public void setPoluicaoDaAgua(double poluicaoDaAgua) {
        this.poluicaoDaAgua = poluicaoDaAgua;
    }

    @Override
    public String toString() {
        return "PoluicaoAguas{" +
                "id='" + super.getId() + '\'' +
                ", cidade='" + cidade + '\'' +
                ", regiao='" + regiao + '\'' +
                ", entidade='" + entidade + '\'' +
                ", qualidadeDoAr=" + qualidadeDoAr +
                ", poluicaoDaAgua=" + poluicaoDaAgua +
                "} ";
    }
}
