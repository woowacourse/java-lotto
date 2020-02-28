package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.errors.InvalidInputException;

public class LottoGame {

    public void play() {
        Money purchaseAmount = inputPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        Result result = produceResult(lottos, purchaseAmount);
        OutputView.printResult(result);
    }

    private Money inputPurchaseAmount() {
        try {
            Money purchaseAmount = InputView.inputPurchaseAmount();
            OutputView.printLottosSize(purchaseAmount.toLottosSize());
            return purchaseAmount;
        } catch (InvalidInputException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    private List<Lotto> purchaseLottos(Money purchaseAmount) {
        List<Lotto> lottosManual = purchaseLottosManually(purchaseAmount.toLottosSize());
        List<Lotto> lottosAutomatic = generateLottosAutomatically(purchaseAmount.toLottosSize() - lottosManual.size());
        printLottos(lottosManual, lottosAutomatic);
        return Stream.concat(lottosManual.stream(), lottosAutomatic.stream()).collect(Collectors.toList());
    }

    private List<Lotto> purchaseLottosManually(int lottosSize) {
        int numberToBuyManually = inputNumberToBuyManually(lottosSize);
        List<Set<LottoNumber>> lottoNumbersBasket = inputLottoNumbersBasket(numberToBuyManually);
        return LottosGenerator.generateManually(lottoNumbersBasket);
    }

    private int inputNumberToBuyManually(int lottosSize) {
        try {
            return InputView.inputNumberToBuyManually(lottosSize);
        } catch (InvalidInputException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            return inputNumberToBuyManually(lottosSize);
        }
    }

    private List<Set<LottoNumber>> inputLottoNumbersBasket(int numberToBuyManually) {
        try {
            return InputView.inputManualLottos(numberToBuyManually);
        } catch (InvalidInputException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            return inputLottoNumbersBasket(numberToBuyManually);
        }
    }

    private List<Lotto> generateLottosAutomatically(int lottosSize) {
        return LottosGenerator.generateAutomatically(lottosSize);
    }

    private void printLottos(List<Lotto> lottosManual, List<Lotto> lottosAutomatic) {
        OutputView.printLottos(lottosManual, lottosAutomatic);
    }

    private Result produceResult(List<Lotto> lottos, Money purchaseMoney) {
        Lotto winningLotto = inputWinningLotto();
        LottoNumber bonusNumber = inputBonusNumber();
        WinningRanks winningRanks = compareWithWinningNumbers(lottos, winningLotto, bonusNumber);
        return new Result(winningRanks, purchaseMoney);
    }

    private Lotto inputWinningLotto() {
        try {
            return InputView.inputLastWeekWinningNumbers();
        } catch (InvalidInputException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            return inputWinningLotto();
        }
    }

    private LottoNumber inputBonusNumber() {
        try {
            return InputView.inputBonusNumber();
        } catch (InvalidInputException e) {
            return inputBonusNumber();
        }
    }

    private WinningRanks compareWithWinningNumbers(List<Lotto> lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        WinningRanks winningRanks = new WinningRanks(new HashMap<>());
        for (Lotto lotto : lottos) {
            int matchingNumber = lotto.matchWinningNumbers(winningLotto);
            addWinningRank(bonusNumber, winningRanks, lotto, matchingNumber);
        }
        return winningRanks;
    }

    private void addWinningRank(LottoNumber bonusNumber, WinningRanks winningRanks, Lotto lotto, int matchingNumber) {
        if (Rank.isValid(matchingNumber)) {
            Rank rank = Rank.valueOf(matchingNumber, lotto.matchBonusNumber(bonusNumber));
            winningRanks.addWinningRanks(rank);
        }
    }
}
