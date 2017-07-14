package br.com.legacy.pattern;

import br.com.legacy.model.entities.Budget;
import br.com.legacy.model.entities.BudgetItem;
import br.com.legacy.model.entities.RfidTag;
import br.com.legacy.model.entities.Shop;
import br.com.legacy.model.entities.TagStatus;

public class ConsignmentItemFactory implements BudgetItemFactory {

	@Override
    public BudgetItem createBudgetItem(Budget budget, Shop currentShop, RfidTag tag) {        
        if(!tag.getStatus().equals(TagStatus.DISPONIVEL) && !tag.getStatus().equals(TagStatus.CONSIGNADO)){
            return null;
        }        
        if(tag.getStatus().equals(TagStatus.CONSIGNADO)&&!tag.getConsignment().equals(budget.getConsignment()) ){
            return null;
        }        
        BudgetItem item = createBaseItem(budget, currentShop, tag);
        if(tag.getStatus().equals( TagStatus.DISPONIVEL)){
            item.setType(BudgetItem.BudgetItemType.SAIDA);
        }else{
            item.setType(BudgetItem.BudgetItemType.ENTRADA);
        }
        return item;        
    }
	
}
