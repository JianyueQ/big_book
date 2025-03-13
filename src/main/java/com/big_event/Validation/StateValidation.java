package com.big_event.Validation;

import com.big_event.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    //<给哪个注解提供校验规则,校验的数据类型>

    /**
     *
     * @param value 将来要校验的数据
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验数据
        if ( value == null ){
            return false;
        }
        if ( value.equals("已发布") || value.equals("草稿") ){
            return true;
        }
        return false;
    }




}
