package br.com.legacy.validator;

import br.com.legacy.model.entities.Budget;

/**
 *
 * @author caio
 */
public class SaleValidator implements Validator<Budget> {

    @Override
    public boolean validate(Budget identified, boolean fullValidate) throws ValidateException {
        if(!(identified instanceof Budget)){
            throw new ValidateException("Tipo de entidade incompativel!");
        }                
        Budget budget = (Budget)identified;
        
        if (budget.getPriceTable() == null || budget.getPriceTable().getId() == null) {
            throw new ValidateException("Informe uma Tabela de Preço!");
        }
        if (fullValidate) {
            if (budget.getCustomer() == null || budget.getCustomer().getId() == null) {
                throw new ValidateException("Informe um Cliente!");
            }
            if (budget.getItems().isEmpty()) {
                throw new ValidateException("Adicione pelo menos um item!");
            }
            if (budget.getSeller() == null || budget.getSeller().getId() == null) {
                throw new ValidateException("Informe um vendedor!");
            }
        }
        return true;
    }

}
