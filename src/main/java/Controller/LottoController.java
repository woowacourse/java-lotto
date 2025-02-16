package Controller;

import Model.Lotto;
import Model.LottoResult;
import Model.Lottos;
import Model.WinnerNumber;
import View.InputView;
import View.OutputView;
import java.util.List;
import java.util.Scanner;

public class LottoController {

    private static final Scanner SCANNER = new Scanner(System.in);

    public void run() {
        int price = getPrice();
        Lottos lottos = getLottos(price);
        printLottoAmount(lottos);

        List<Integer> winnerNumbers = getWinnerNumbers();

        int bonusBall = getBonusBall(winnerNumbers);

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

    private int getBonusBall(List<Integer> winnerNumbers) {
        OutputView.inputBonusBall();
        String inputBonusBall = SCANNER.nextLine();
        return InputView.inputBonusBall(inputBonusBall, winnerNumbers);
    }

    private List<Integer> getWinnerNumbers() {
        OutputView.inputWinnerNumbers();
        String inputWinnerNumbers = SCANNER.nextLine();
        return InputView.inputWinnerNumbers(inputWinnerNumbers);
    }

    private Lottos getLottos(int lottoAmount) {
        Lottos lottos = new Lottos(lottoAmount);
        OutputView.printLottoResults(lottos);
        return lottos;
    }

    private void printLottoAmount(Lottos lottos) {
        OutputView.printPurchaseCount(lottos.lottoSize());
    }

    private int getPrice() {
        OutputView.inputPurchaseAmount();
        String inputPrice = SCANNER.nextLine();
        return InputView.inputPrice(inputPrice);
    }
}
