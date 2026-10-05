package org.aston.module.value.objects;

public class Length {
    private int length;
    public Length(int value){
        if(isValid(value))
            length = value;
        else
            throw new IllegalArgumentException("Длина коллекции должна быть больше 0 и меньше 2147483647");
    }
    public boolean isValid(int value){
        return value >= 0;
    }
    public int getValue(){
        return length;
    }
}
