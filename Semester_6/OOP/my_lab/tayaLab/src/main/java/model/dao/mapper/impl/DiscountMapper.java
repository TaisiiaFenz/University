package model.dao.mapper.impl;

import model.dao.mapper.Mapper;
import model.entity.Discount;
import model.entity.DiscountType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DiscountMapper implements Mapper<Discount> {
    @Override
    public Discount resultSetToEntity(ResultSet resultSet) throws SQLException {
        Discount discount = new Discount();
        discount.setId(resultSet.getLong("id"));
        discount.setDiscountPercentage(resultSet.getFloat("percentage"));
        discount.setDiscountType(DiscountType.valueOf(resultSet.getString("type")));
        return discount;
    }

    @Override
    public Discount makeUnique(Map<Long, Discount> cache, Discount entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
