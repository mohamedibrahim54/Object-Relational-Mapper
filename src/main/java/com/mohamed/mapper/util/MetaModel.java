package com.mohamed.mapper.util;

import com.mohamed.mapper.annotation.Column;
import com.mohamed.mapper.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MetaModel {
    private final Class<?> aClass;
    private final PrimaryKeyField primaryKeyField;
    private final List<ColumnField>  columnFieldList;

    public static MetaModel of(Class<?> aClass) {
        return new MetaModel(aClass);
    }

    public MetaModel(Class<?> aClass) {
        this.aClass = aClass;
        primaryKeyField = getPrimaryKey();
        columnFieldList = getColumns();
    }

    public PrimaryKeyField getPrimaryKeyField() {
        return primaryKeyField;
    }

    public List<ColumnField> getColumnFieldList() {
        return columnFieldList;
    }

    private PrimaryKeyField getPrimaryKey() {
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(PrimaryKey.class) != null) {
                return new PrimaryKeyField(field);
            }
        }
        throw new IllegalArgumentException("No primary key found in class " + aClass.getSimpleName());
    }

    private List<ColumnField> getColumns() {
        List<ColumnField> columnFieldList = new ArrayList<>();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                columnFieldList.add(new ColumnField(field));
            }
        }
        return columnFieldList;
    }

    public String buildInsertRequest() {
        return "insert into " + aClass.getSimpleName() +
                " (" + buildColumnNames() + ") values (" + buildQuestionMarks() + ")";
    }

    private String buildColumnNames() {
        List<String> columns = columnFieldList.stream().map(ColumnField::getName)
                .collect(Collectors.toList());
        String primaryKey = primaryKeyField.getName();
        columns.add(0, primaryKey);
        return String.join(", ", columns);
    }

    private String buildQuestionMarks() {
        int size = columnFieldList.size() + 1;
        return IntStream.range(0, size)
                .mapToObj((i) -> "?")
                .collect(Collectors.joining(", "));
    }
}
