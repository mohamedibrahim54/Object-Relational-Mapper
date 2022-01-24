package com.mohamed.mapper.util;

import com.mohamed.mapper.annotation.Column;

import java.lang.reflect.Field;

public class ColumnField {
    private final Field field;
    private final Column column;

    public ColumnField(Field field) {
        this.field = field;
        this.column = field.getAnnotation(Column.class);
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
