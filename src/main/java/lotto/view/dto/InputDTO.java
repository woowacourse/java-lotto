package lotto.view.dto;

import lotto.controller.Money;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputDTO {

    public static Money inputPurchaseMoney() {
        try {
            String inputForPurchaseMoney = InputView.inputPurchaseMoney();
            int valueForPurchaseMoney = Integer.parseInt(inputForPurchaseMoney);
            return Money.generatePurchaseMoney(valueForPurchaseMoney);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    public static WinningNumbers inputWinningNumbers() {
        try {
            return new WinningNumbers(inputWinningLottoTicket(), inputBonusNumber());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private static LottoTicket inputWinningLottoTicket() {
        try {
            List<LottoNumber> sixNumbers = Arrays.stream(InputView.inputSixNumbers().split(","))
                    .map(Integer::parseInt)
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            return new LottoTicket(sixNumbers);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputWinningLottoTicket();
        }
    }

    private static LottoNumber inputBonusNumber() {
        try {
            String inputForBonusNumber = InputView.inputBonusNumber();
            int valueForBonusNumber = Integer.parseInt(inputForBonusNumber);
            return new LottoNumber(valueForBonusNumber);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputBonusNumber();
        }
    }
}
