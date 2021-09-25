package com.ithr.commonutils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author com.hr.Text
 * @date 2020/12/19 - 18:26
 */
@Data
@AllArgsConstructor
public class LambdaEntity {
    private String name;
    private  Integer age;
    private Double  mone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMone() {
        return mone;
    }

    public void setMone(Double mone) {
        this.mone = mone;
    }
}
