package com.fatec.gerenciamentohotel.entity;

import java.util.Date;
import java.util.List;

public class ServicoDeQuarto {
    private long id;
    private List<ItemServico> itemsServicos;
    private Date dataSolicitacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ItemServico> getItemsServicos() {
        return itemsServicos;
    }

    public void setItemsServicos(List<ItemServico> itemsServicos) {
        this.itemsServicos = itemsServicos;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
}
