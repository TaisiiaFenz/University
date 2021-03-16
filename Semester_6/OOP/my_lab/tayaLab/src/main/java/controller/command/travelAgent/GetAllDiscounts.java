package controller.command.travelAgent;

import controller.command.Command;
import model.service.DiscountService;

import javax.servlet.http.HttpServletRequest;

public class GetAllDiscounts implements Command {
    DiscountService discountService;

    public GetAllDiscounts(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
       return discountService.getAllDiscounts();
    }
}
