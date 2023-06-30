package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator{
    public Courier generic(){
        return new Courier("sdasda", "12345", "arsen");
    }

    public Courier random(){
        return new Courier(RandomStringUtils.randomAlphabetic(5,10), "12345", "arsen");
    }
    public Courier genericWithOutLogin(){
        return new Courier("", "12345", "arsen");
    }
    public Courier genericWithOutPassword(){
        return new Courier(RandomStringUtils.randomAlphabetic(5,10), "","arsen");
    }
    public Courier genericCourierWithOutParametrs(){
        return new Courier("", "", "arsen");
    }
}
