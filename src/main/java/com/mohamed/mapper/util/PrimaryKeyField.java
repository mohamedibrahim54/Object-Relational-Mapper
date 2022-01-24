package com.mohamed.mapper.util;

import com.mohamed.mapper.annotation.PrimaryKey;

import java.lang.reflect.Field;

public class PrimaryKeyField {

    private final Field field;
    private final PrimaryKey primaryKey;

    public PrimaryKeyField(Field field) {
        this.field = field;
        this.primaryKey = field.getAnnotation(PrimaryKey.class);
    }

    public String getName() {
        return field.getName();
    }

    public Class<?> getType() {
        return field.getType();
    }

    public Field getField() {
        return field;
    }
}
