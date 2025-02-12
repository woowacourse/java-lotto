package Controller;

import Model.Lottos;
import Model.WinnerNumber;
import Service.LottoService;
import View.InputView;
import View.OutputView;
import java.util.List;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run(){
        OutputView.inputPurchaseAmount();
        int price = InputView.inputPrice();
        int lottoAmount = lottoService.lottoCount(price);

        Lottos lottos = new Lottos(lottoAmount);
        OutputView.printLottoResults(lottos);
        OutputView.inputWinnerNumbers();
        List<Integer> winnerNumbers = InputView.inputWinnerNumbers();
        int bonusBall = InputView.inputBonusBall(winnerNumbers);
        WinnerNumber winnerNumber = new WinnerNumber(winnerNumbers, bonusBall);


    }
}
