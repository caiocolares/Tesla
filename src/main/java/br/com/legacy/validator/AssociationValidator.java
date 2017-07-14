package br.com.legacy.validator;

import br.com.legacy.model.entities.Order;

/**
 * @author caio
 */
public class AssociationValidator implements Validator<Order>{

    @Override
    public boolean validate(Order identified, boolean fullValidate) throws ValidateException {
        return true;
    }
    
}
