package controller;

import java.util.List;
import java.util.Scanner;
import model.Lotto;
import model.Lottos;
import model.WinnerNumber;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoService lottoService;
    private static Scanner sc = new Scanner(System.in);


    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        int price = getPrice();
        int lottoAmount = getLottoAmount(price);
        Lottos lottos = getLottos(lottoAmount);

        List<Integer> winnerNumbers = getWinnerNumbers();

        int bonusBall = getBonusBall(winnerNumbers);

        WinnerNumber winnerNumber = new WinnerNumber(winnerNumbers, bonusBall);

        compareWinning(lottos, winnerNumber);
        double result = lottoService.lottoRateOfReturn(price);

        OutputView.winningStatistics(result);
        sc.close();
    }

    private void compareWinning(Lottos lottos, WinnerNumber winnerNumber) {
        for (Lotto lotto : lottos.getLottos()) {
            winnerNumber.compareWinning(lotto);
        }
    }

    private int getBonusBall(List<Integer> winnerNumbers) {
        OutputView.inputBonusBall();
        String inputBonusBall = sc.nextLine();
        return InputView.inputBonusBall(inputBonusBall, winnerNumbers);
    }

    private List<Integer> getWinnerNumbers() {
        OutputView.inputWinnerNumbers();
        String inputWinnerNumbers = sc.nextLine();
        return InputView.inputWinnerNumbers(inputWinnerNumbers);

    }

    private Lottos getLottos(int lottoAmount) {
        Lottos lottos = new Lottos(lottoAmount);
        OutputView.printLottoResults(lottos);
        return lottos;
    }

    private int getLottoAmount(int price) {
        int lottoAmount = lottoService.lottoCount(price);
        OutputView.printPurchaseCount(lottoAmount);
        return lottoAmount;
    }

    private int getPrice() {
        OutputView.inputPurchaseAmount();
        String inputPrice = sc.nextLine();
        return InputView.inputPrice(inputPrice);
    }
}
