package lotto.controller;

import lotto.model.BonusBall;
import lotto.model.LottoResult;
import lotto.model.Payment;
import lotto.model.WinNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMembersInitializer {

    public static Payment initializePayment() {
        OutputView.printinput();
        return new Payment(InputView.inputPayment());
    }

    public static BonusBall initializeBonusNumber(WinNumber winNumber) {
        OutputView.printInputBonusNumber();
        return new BonusBall(winNumber, InputView.inputBonusBall());
    }

    public static WinNumber initializeWinNumber() {
        OutputView.printInputWinNumber();
        return new WinNumber(InputView.inputWinNumber());
    }

    public static LottoResult initializeResultCount() {
        return new LottoResult();
    }
}
