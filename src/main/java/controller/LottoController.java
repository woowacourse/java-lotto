package controller;

import model.Lotto;
import model.LottoResult;
import model.Lottos;
import model.WinnerNumber;
import view.InputView;
import view.OutputView;
import java.util.List;
import java.util.Scanner;

public class LottoController {

    private static final Scanner SCANNER = new Scanner(System.in);

    public void run() {
        int price = readPrice();
        Lottos lottos = generateLottos(price);
        printLottoAmount(lottos);
        List<Integer> winnerNumbers = readWinnerNumbers();
        int bonusBall = readBonusBall(winnerNumbers);
        WinnerNumber winnerNumber = new WinnerNumber(winnerNumbers, bonusBall);
        compareWinning(lottos, winnerNumber);
        double result = LottoResult.lottoRateOfReturn(price);
        OutputView.winningStatistics(result);
        SCANNER.close();
    }

    private void compareWinning(Lottos lottos, WinnerNumber winnerNumber) {
        for (Lotto lotto : lottos.getLottos()) {
            winnerNumber.compareWinning(lotto);
        }
    }

    private int readBonusBall(List<Integer> winnerNumbers) {
        OutputView.inputBonusBall();
        String inputBonusBall = SCANNER.nextLine();
        return InputView.inputBonusBall(inputBonusBall, winnerNumbers);
    }

    private List<Integer> readWinnerNumbers() {
        OutputView.inputWinnerNumbers();
        String inputWinnerNumbers = SCANNER.nextLine();
        return InputView.inputWinnerNumbers(inputWinnerNumbers);
    }

    private Lottos generateLottos(int lottoAmount) {
        Lottos lottos = new Lottos(lottoAmount);
        OutputView.printLottoResults(lottos);
        return lottos;
    }

    private void printLottoAmount(Lottos lottos) {
        OutputView.printPurchaseCount(lottos.lottoSize());
    }

    private int readPrice() {
        OutputView.inputPurchaseAmount();
        String inputPrice = SCANNER.nextLine();
        return InputView.inputPrice(inputPrice);
    }
}
