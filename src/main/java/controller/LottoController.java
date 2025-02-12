package controller;

import dto.IssuedLottosDto;
import service.LottoIssue;
import view.InputView;

public class LottoController {
    private final LottoIssue lottoIssue;

    public LottoController(LottoIssue lottoIssue) {
        this.lottoIssue = lottoIssue;
    }

    public void issueLotto(){
        int money = InputView.askMoney();
        IssuedLottosDto lottosDto = lottoIssue.issueLottos(money);
    }
}
