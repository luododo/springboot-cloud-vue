package com.ithr.commonutils.text;

import com.ithr.commonutils.LambdaEntity;

/**
 * @author com.hr.Text
 * @date 2020/12/21 - 15:11
 */
public class MyPredicateAgeImpl<T> implements MyPredicate<LambdaEntity> {
    @Override
    public boolean text(LambdaEntity t) {
        return t.getAge() >= 35;
    }
}
