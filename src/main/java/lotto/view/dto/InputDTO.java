package lotto.view.dto;

import lotto.controller.Money;
import lotto.domain.LottoNumber;
import lotto.view.InputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputDTO {
    public static List<LottoNumber> inputSixNumbers() {
        return Arrays.stream(InputView.inputSixNumbers().split(", "))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static LottoNumber inputBonusNumber() {
        String inputForBonusNumber = InputView.inputBonusNumber();
        int valueForBonusNumber = Integer.parseInt(inputForBonusNumber);
        return new LottoNumber(valueForBonusNumber);
    }

    public static Money inputPurchaseMoney() {
        String inputForPurchaseMoney = InputView.inputPurchaseMoney();
        int valueForPurchaseMoney = Integer.parseInt(inputForPurchaseMoney);
        return new Money(valueForPurchaseMoney);
    }
}
