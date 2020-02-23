package lotto.view.dto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputDTO {
    private InputDTO() {
    }

    public static Money inputPurchaseMoney() {
        try {
            return generateMoneyFromInput();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    private static Money generateMoneyFromInput() {
        String inputForPurchaseMoney = InputView.inputPurchaseMoney();
        int valueForPurchaseMoney = Integer.parseInt(inputForPurchaseMoney);
        return Money.createPurchaseMoney(valueForPurchaseMoney);
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
            return generateLottoTicketFromNumbers();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputWinningLottoTicket();
        }
    }

    private static LottoTicket generateLottoTicketFromNumbers() {
        List<LottoNumber> sixNumbers = Arrays.stream(InputView.inputSixNumbers().split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoTicket(sixNumbers);
    }

    private static LottoNumber inputBonusNumber() {
        try {
            return generateBonusNumberFromInput();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputBonusNumber();
        }
    }

    private static LottoNumber generateBonusNumberFromInput() {
        String inputForBonusNumber = InputView.inputBonusNumber();
        int valueForBonusNumber = Integer.parseInt(inputForBonusNumber);
        return new LottoNumber(valueForBonusNumber);
    }
}
