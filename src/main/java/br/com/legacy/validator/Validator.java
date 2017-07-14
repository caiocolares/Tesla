package br.com.legacy.validator;

@FunctionalInterface
public interface Validator<T> {

    boolean validate(T identified, boolean fullValidate) throws ValidateException;
}
