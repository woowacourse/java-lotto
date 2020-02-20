package lotto;

import lotto.domain.exception.DuplicateLottoNumberException;
import lotto.domain.exception.LottoNumberSizeException;
import lotto.domain.number.*;
import lotto.domain.random.RandomNumberGenerator;
import lotto.domain.result.GameResult;
import lotto.domain.result.GameResults;
import lotto.domain.result.Yield;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        PurchaseNumber purchaseNumber = inputPurchaseNumber();
        AllLottoNumbers allLottoNumbers = createAllLottoNumbers(purchaseNumber);
        OutputView.printAllLottoNumbers(allLottoNumbers);
        WinningNumbers winningNumbers = inputWinningNumbers();

        GameResults gameResults = createGameResults(purchaseNumber, allLottoNumbers, winningNumbers);
        OutputView.printGameResults(gameResults);

        Yield yield = Yield.calculateYield(purchaseNumber, gameResults);
        OutputView.printYield(yield);
    }

    private static PurchaseNumber inputPurchaseNumber() {
        PurchaseNumber purchaseNumber = InputView.inputPurchaseMoney();
        OutputView.printPurchaseNumber(purchaseNumber);
        return purchaseNumber;
    }

    private static AllLottoNumbers createAllLottoNumbers(PurchaseNumber purchaseNumber) {
        List<LottoNumbers> lottoNumbersList = new ArrayList<>();
        for (int i = 0; i < purchaseNumber.getPurchaseNumber(); i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            List<LottoNumber> randomNumbers = randomNumberGenerator.generateNumbers();
            lottoNumbersList.add(new LottoNumbers(randomNumbers));
        }
        return new AllLottoNumbers(lottoNumbersList);
    }

    private static GameResults createGameResults(PurchaseNumber purchaseNumber, AllLottoNumbers allLottoNumbers, WinningNumbers winningNumbers) {
        List<GameResult> gameResultList = new ArrayList<>();
        for (int i = 0; i < purchaseNumber.getPurchaseNumber(); i++) {
            LottoNumbers presentLottoNumbers = allLottoNumbers.getAllLottoNumbers().get(i);
            int correctNumber = winningNumbers.calculateCollectNumberSize(presentLottoNumbers);
            boolean isCorrectBonusNumber = winningNumbers.isCorrectBonusNumber(presentLottoNumbers);
            GameResult gameResult = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
            gameResultList.add(gameResult);
        }
        return new GameResults(gameResultList);
    }

    private static WinningNumbers inputWinningNumbers() {
        try {
            List<LottoNumber> lottoNumbers = InputView.inputWinningNumbers();
            LottoNumber bonusNumber = InputView.inputBonusNumber();
            return new WinningNumbers(lottoNumbers, bonusNumber);
        } catch (DuplicateLottoNumberException | LottoNumberSizeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningNumbers();
        }
    }
}
