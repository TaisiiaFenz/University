package model.service;

import com.google.gson.Gson;
import model.dao.DaoFactory;
import model.dao.DiscountDao;
import model.entity.Discount;

import java.util.List;

public class DiscountService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public String getAllDiscounts(){
        Gson gson = new Gson();
        try(DiscountDao discountDao = daoFactory.createDiscountDao()){
            return gson.toJson(discountDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
