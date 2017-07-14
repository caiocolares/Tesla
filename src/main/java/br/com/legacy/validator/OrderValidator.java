/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.legacy.validator;

import br.com.legacy.model.entities.Order;

/**
 *
 * @author caio
 */
public class OrderValidator implements Validator<Order>{

    @Override
    public boolean validate(Order identified, boolean fullValidate) throws ValidateException {
        return true;
    }
    
}
