package ua.taisiia.model.dao.mapper;

import ua.taisiia.model.entity.Discount;
import ua.taisiia.model.entity.DiscountType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DiscountMapper implements Mapper<Discount>{

    @Override
    public Discount resultSetToEntity(ResultSet resultSet) throws SQLException {
        Discount discount = new Discount();
        discount.setId(resultSet.getLong("id"));
        discount.setDiscountType(DiscountType.valueOf(resultSet.getString("discount_type")));
        discount.setDiscountPercentage(resultSet.getFloat("discount_percentage"));
        return discount;
    }



    @Override
    public Discount makeUnique(Map<Long, Discount> cache, Discount entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
