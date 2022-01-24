package com.mohamed.mapper.orm;

import com.mohamed.mapper.util.ColumnField;
import com.mohamed.mapper.util.MetaModel;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractEntityManager<T> implements EntityManager<T> {

    AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public void persist(T t) throws SQLException, IllegalAccessException {
        MetaModel model = MetaModel.of(t.getClass());
        String sql = model.buildInsertRequest();
        try (PreparedStatement statement = prepareStatementWith(sql).andParameters(t);){
            statement.executeUpdate();
        }
    }

    private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {
        Connection connection = buildConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        PreparedStatementWrapper statementWrapper = new PreparedStatementWrapper(preparedStatement);
        return statementWrapper;
    }

    protected abstract Connection buildConnection() throws SQLException;


    private class PreparedStatementWrapper {
        private PreparedStatement statement;
        public PreparedStatementWrapper(PreparedStatement statement) {
            this.statement = statement;
        }

        public PreparedStatement andParameters(T t) throws SQLException, IllegalAccessException {
            MetaModel model = MetaModel.of(t.getClass());

            if(model.getPrimaryKeyField().getType() == long.class){
                long id = idGenerator.incrementAndGet();
                statement.setLong(1, id);

                // set object id
                Field field = model.getPrimaryKeyField().getField();
                field.setAccessible(true);
                field.setLong(t, id);
            }

            List<ColumnField> columnFields = model.getColumnFieldList();
            for (int index = 0; index < columnFields.size(); index++){
                ColumnField columnField = columnFields.get(index);
                Class<?> type = columnField.getType();
                Field field = columnField.getField();
                field.setAccessible(true);
                Object value = field.get(t);
                if (type == int.class){
                    statement.setInt(index+2, (int)value);
                }else if (type == String.class){
                    statement.setString(index+2, (String) value);
                }
            }

            return statement;
        }
    }
}
