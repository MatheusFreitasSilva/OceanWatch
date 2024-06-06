package org.example.entities.dtos;

import org.example.entities._BaseEntity;

/**
 * Classe ProducaoPlasticoGlobal que representa dados de produção global de plástico.
 * Estende a classe _BaseEntity para incluir um identificador único.
 * Contém informações sobre a entidade responsável, ano e produção anual de plástico.
 */
public class ProducaoPlasticoGlobal extends _BaseEntity {
    private String entidade;
    private String ano;
    private double producaoAnualPlastico;

    public ProducaoPlasticoGlobal(){}

    public ProducaoPlasticoGlobal(String entidade, String ano, double producaoAnualPlastico) {
        this.entidade = entidade;
        this.ano = ano;
        this.producaoAnualPlastico = producaoAnualPlastico;
    }

    public ProducaoPlasticoGlobal(int id, String entidade, String ano, double producaoAnualPlastico) {
        super(id);
        this.entidade = entidade;
        this.ano = ano;
        this.producaoAnualPlastico = producaoAnualPlastico;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public double getProducaoAnualPlastico() {
        return producaoAnualPlastico;
    }

    public void setProducaoAnualPlastico(double producaoAnualPlastico) {
        this.producaoAnualPlastico = producaoAnualPlastico;
    }

    @Override
    public String toString() {
        return "ProducaoPlasticoGlobal{" +
                "id='" + super.getId() + '\'' +
                ", entidade='" + entidade + '\'' +
                ", ano='" + ano + '\'' +
                ", producaoAnualPlastico=" + producaoAnualPlastico +
                "} ";
    }
}
