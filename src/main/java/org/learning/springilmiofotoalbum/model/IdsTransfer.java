package org.learning.springilmiofotoalbum.model;

public class IdsTransfer {

    public enum IdsTransferAction {
        UPDATE, SHOWFORM , HIDEFORM
    }

    private IdsTransferAction idsTransferAction;
    private Integer categoryId;

    public IdsTransfer() {
        super();
    }

    public IdsTransfer(IdsTransferAction idsTransferAction, Integer categoryId) {
        this.idsTransferAction = idsTransferAction;
        this.categoryId = categoryId;
    }

    public IdsTransfer(IdsTransferAction idsTransferAction) {
        this.idsTransferAction = idsTransferAction;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public IdsTransferAction getIdsTransferAction() {
        return idsTransferAction;
    }

    public void setIdsTransferAction(IdsTransferAction idsTransferAction) {
        this.idsTransferAction = idsTransferAction;
    }
}
