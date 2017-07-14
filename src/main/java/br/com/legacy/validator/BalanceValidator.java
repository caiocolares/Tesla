/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.legacy.validator;

import br.com.legacy.model.entities.Balance;

/**
 *
 * @author caio
 */
public class BalanceValidator implements Validator<Balance>{

    @Override
    public boolean validate(Balance identified, boolean fullValidate) throws ValidateException {
        return true;
    }
    
}
