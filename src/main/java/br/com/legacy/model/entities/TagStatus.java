package br.com.legacy.model.entities;

public enum TagStatus {
    PENDENTE(-1),
    INDISPONIVEL(-1),
    RESERVADO(0),
    DISPONIVEL(1),
    FATURADO(2),
    CONSIGNADO(0),
    DEVOLVIDO(0);    
    
    private final int nextStatus;
    
    private static final TagStatus[] vector = new TagStatus[]{DISPONIVEL,FATURADO,CONSIGNADO};
    
    private TagStatus(int nextStatus){
        this.nextStatus = nextStatus;
    }
    
    public TagStatus getNextStatus(){
        if(nextStatus == -1)
            return this;
        return TagStatus.vector[nextStatus];
    }
    
}