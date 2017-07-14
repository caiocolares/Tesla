package br.com.legacy.model.entities;

import br.com.legacy.pattern.BudgetItemFactory;
import br.com.legacy.pattern.ConsignmentItemFactory;
import br.com.legacy.pattern.SaleItemFactory;
import br.com.legacy.validator.AssociationValidator;
import br.com.legacy.validator.BalanceValidator;
import br.com.legacy.validator.ConsignmentValidator;
import br.com.legacy.validator.OrderValidator;
import br.com.legacy.validator.SaleValidator;
import br.com.legacy.validator.Validator;

public enum ActionType {
    
    CONSIGNACAO(new ConsignmentValidator(),new ConsignmentItemFactory()),
    VENDA(new SaleValidator(),new SaleItemFactory()),
    PEDIDO(new OrderValidator(),null),
    BALANCO(new BalanceValidator(),null),
    ASSOCIACAO( new AssociationValidator(),null);
    
    private ActionType(Validator<?> validator, BudgetItemFactory factory){
        this.validator = validator;
        this.factory = factory;
    }
    
    private final BudgetItemFactory factory;
    
    private final Validator<?> validator;
    
    public BudgetItemFactory getFactory(){
        return this.factory;                
    }
    
    public Validator<?> getValidator(){
        return this.validator;
    }
}
