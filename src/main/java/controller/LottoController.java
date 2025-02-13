package controller;

import dto.IssuedLottoDto;
import dto.IssuedLottosDto;
import java.util.List;
import java.util.stream.Collectors;
import service.LottoIssue;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoIssue lottoIssue;

    public LottoController(LottoIssue lottoIssue) {
        this.lottoIssue = lottoIssue;
    }

    public void start() {
        OutputView.printLottoResult(issueLotto());
    }

    public IssuedLottosDto issueLotto(){
        int money = InputView.askMoney();
        return lottoIssue.issueLottos(money);
    }



}
