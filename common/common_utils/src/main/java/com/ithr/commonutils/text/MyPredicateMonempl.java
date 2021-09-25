package com.ithr.commonutils.text;

import com.ithr.commonutils.LambdaEntity;

/**
 * @author com.hr.Text
 * @date 2020/12/21 - 15:33
 */
public class MyPredicateMonempl implements MyPredicate<LambdaEntity>{

    @Override
    public boolean text(LambdaEntity lambdaEntity) {
        return lambdaEntity.getMone() >= 7000;
    }
}
