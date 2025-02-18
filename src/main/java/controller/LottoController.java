package controller;

import java.util.List;
import java.util.Scanner;
import model.LottoCalculator;
import model.LottoShop;
import model.Lottos;
import model.WinnerNumber;
import view.InputView;
import view.OutputView;

public class LottoController {
    private static final Scanner scanner = new Scanner(System.in);

    public void run() {
        LottoShop lottoShop = new LottoShop();
        int price = inputPrice();
        printLottoAmount(lottoShop, price);
        Lottos lottos = lottoShop.buyLottos(price);
        printPurchasedLotto(lottos);

        List<Integer> winnerNumbers = inputWinnerNumbers();
        int bonusBall = inputBonusBall(winnerNumbers);
        WinnerNumber winnerNumber = new WinnerNumber(winnerNumbers, bonusBall);

        LottoCalculator lottoCalculator = winnerNumber.compareLottoToWinning(lottos);
        double result = lottoCalculator.lottoRateOfReturn(price);

        OutputView.winningStatistics(lottoCalculator, result);
        scanner.close();
    }

    private int inputPrice() {
        OutputView.inputPurchaseAmount();
        String inputPrice = scanner.nextLine();
        return InputView.inputPrice(inputPrice);
    }

    private void printLottoAmount(LottoShop lottoShop, int price) {
        int lottoAmount = lottoShop.calculateLottoAmount(price);
        OutputView.printPurchaseCount(lottoAmount);
    }

    private void printPurchasedLotto(Lottos lottos) {
        List<String> lottoNumbers = lottos.searchLottoNumbers();
        OutputView.printLottoResults(lottoNumbers);
    }

    private List<Integer> inputWinnerNumbers() {
        OutputView.inputWinnerNumbers();
        String inputWinnerNumbers = scanner.nextLine();
        return InputView.inputWinnerNumbers(inputWinnerNumbers);

    }

    private int inputBonusBall(List<Integer> winnerNumbers) {
        OutputView.inputBonusBall();
        String inputBonusBall = scanner.nextLine();
        return InputView.inputBonusBall(inputBonusBall, winnerNumbers);
    }
}
