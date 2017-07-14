package br.com.legacy.validator;

import br.com.legacy.model.entities.Budget;

public class ConsignmentValidator implements Validator<Budget>{


    @Override
    public boolean validate(Budget identified, boolean fullValidate) throws ValidateException {
        if(!(identified instanceof Budget)){
            throw new ValidateException("Tipo de entidade incompativel!");
        }                
        Budget budget = (Budget)identified;
        if(budget.getCustomer() == null || budget.getCustomer().getId() == null){
            throw new ValidateException("Informe um Cliente!");
        }
        if(budget.getPriceTable() == null || budget.getPriceTable().getId() == null){
            throw new ValidateException("Informe uma Tabela de Preço!");
        }
        if(fullValidate){
            if(budget.getItems().isEmpty()){
                throw new ValidateException("Adicione pelo menos um item!");
            }
            if(budget.getSeller()==null || budget.getSeller().getId()== null){
                throw new ValidateException("Informe um vendedor!");
            }
            /*if(budget.getCustomer().getType().equals(Customer.CustomerType.PADRAO)){
                if(budget.getValue().floatValue() < 0.0f ){
                    throw new ValidateException("Movimentação de consignado precisa possuir saldo positivo para este cliente!\nEntre em contato com o setor comercial.");
                }
            }*/
        }
        return true;
    }
    
}
