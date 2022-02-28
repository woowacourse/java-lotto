package controller;

import java.util.List;

public class InputController {

    public Integer inputMoney() {
        return (new PurchaseMoneyInputView()).getUserInputData();
    }

    public List<Integer> inputWinningNumbers() {
        return (new WinningNumberInputView()).getUserInputData();
    }

    public Integer inputBonusNumber() {
        return (new BonusNumberInputView()).getUserInputData();
    }

}
