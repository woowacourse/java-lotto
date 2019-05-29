package lotto.controller;

import lotto.InputValidator;
import lotto.domain.LottoFactory;
import lotto.domain.Lottoes;
import lotto.domain.Price;
import lotto.domain.PriceFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

import static lotto.domain.LottoFactory.generateLottoes;

public class Controller {
    public static void main(String[] args) {
        String priceInput = InputView.promptPrice();
        Price price = generatePrice(priceInput);
        Lottoes lottoes = LottoFactory.generateLottoes(price.getSize());
        OutputView.printLottoes(lottoes);
    }


    private static Price generatePrice(String priceInput) {
        while(InputValidator.isNotValidPrice(priceInput)){
            priceInput = InputView.promptPrice();
        }
        Price price = PriceFactory.generatePrice(Integer.parseInt(priceInput));
        return price;
    }


}
