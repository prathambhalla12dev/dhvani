package com.gooseberry.validation;

import java.lang.reflect.Field;
import com.gooseberry.annotation.ValidString;

public class ValidateValidParam {
    
    private ValidateValidParam(){

    }

    public static void validate(Object object){
                /* 
                    SongCreationRequest songCreationRequest comes
                */
        try{
            Class<?> clazz = object.getClass(); //actual passed class is returned by object.getClass() = SongCreationRequest

            for(Field field: clazz.getDeclaredFields()){ // title, artistName
            if(field.isAnnotationPresent(ValidString.class)){ // both have ValidParam annotation
                field.setAccessible(true); // both are private, therefore to access them outside songCreationRequest: this is required
                Object value = field.get(object); // value of the Field 'field' of the passed object = title, artistName
                ValidString annotation = field.getAnnotation(ValidString.class); // @ValidParam annotation's instance/object applied to this field
                if(value==null){
                    throw new IllegalArgumentException(annotation.message());
                }
                if(!(value instanceof String)){
                    throw new IllegalArgumentException("ValidString annotation can only be applied to String fields");
                }
                String str = (String) value;
                if(str.trim().isEmpty()){
                    throw new IllegalArgumentException(annotation.message());
                }
            }
        }
        } catch(IllegalAccessException e){
            throw new RuntimeException("Validation Failed");
        }
    }
}
