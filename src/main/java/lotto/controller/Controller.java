package lotto.controller;

import lotto.InputValidator;
import lotto.domain.Price;
import lotto.domain.PriceFactory;
import lotto.view.InputView;

public class Controller {
    public static void main(String[] args) {
        String priceInput = InputView.promptPrice();
        Price price = generatePrice(priceInput);
    }

    private static Price generatePrice(String priceInput) {
        while(InputValidator.isNotValidPrice(priceInput)){
            priceInput = InputView.promptPrice();
        }
        Price price = PriceFactory.generatePrice(Integer.parseInt(priceInput));
        return price;
    }


}
