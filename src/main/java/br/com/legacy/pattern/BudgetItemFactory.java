package br.com.legacy.pattern;

import br.com.legacy.model.entities.Budget;
import br.com.legacy.model.entities.BudgetItem;
import br.com.legacy.model.entities.RfidTag;
import br.com.legacy.model.entities.Shop;

@FunctionalInterface
public interface BudgetItemFactory {

    default BudgetItem createBaseItem(Budget budget, Shop currentShop, RfidTag tag) {
        return  BudgetItem.builder().budget(budget).shop(currentShop).tag(tag).build();
    }
    
    public BudgetItem createBudgetItem(Budget budget, Shop currentShop, RfidTag tag);

}
