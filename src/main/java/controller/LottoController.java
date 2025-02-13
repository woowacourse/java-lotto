package controller;

import constant.WinningCount;
import dto.IssuedLottosDto;
import dto.WinningLottoDto;
import java.util.List;
import java.util.Map;
import service.IssueLottoService;
import service.OpenLottoService;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final IssueLottoService issueLottoService;
    private final OpenLottoService openLottoService;

    public LottoController(IssueLottoService issueLottoService, OpenLottoService openLottoService) {
        this.issueLottoService = issueLottoService;
        this.openLottoService = openLottoService;
    }

    public void start() {
        IssuedLottosDto issuedLottosDto = issueLotto();
        OutputView.printLottoResult(issuedLottosDto);
        Map<WinningCount, Integer> winningCountIntegerMap = openLottoService.openResult(makeWinningLotto(),
                issuedLottosDto);
        openLottoService.calculateEarningRate(winningCountIntegerMap, issuedLottosDto.lottos().size() * 1000);
    }

    private IssuedLottosDto issueLotto(){
        int money = InputView.askMoney();
        return issueLottoService.issueLottos(money);
    }

    private WinningLottoDto makeWinningLotto(){
        List<Integer> numbers = InputView.askWinningLotto();
        Integer bonusNumber = InputView.askBonusNumber();
        return openLottoService.makeWinningLotto(numbers, bonusNumber);
    }


}
