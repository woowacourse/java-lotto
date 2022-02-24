package controller;

import java.util.List;

import view.BonusNumberInputView;
import view.PurchaseMoneyInputView;
import view.WinningNumberInputView;

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
