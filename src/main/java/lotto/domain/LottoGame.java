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

    private Result produceResult(List<Lotto> lottos, Money purchaseMoney) {
        Lotto winningLotto = InputView.inputLastWeekWinningNumbers();
        LottoNumber bonusNumber = InputView.inputBonusNumber();
        WinningRanks winningRanks = compareWithWinningNumbers(lottos, winningLotto, bonusNumber);
        return new Result(winningRanks, purchaseMoney);
    }

    private WinningRanks compareWithWinningNumbers(List<Lotto> lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        //당첨 하기
        WinningRanks winningRanks = new WinningRanks(new HashMap<>());
        //todo: 함수를 더 간결하게 정리
        for (Lotto lotto : lottos) {
            int matchingNumber = lotto.matchWinningNumbers(winningLotto);
            if (Rank.isValid(matchingNumber)) {
                Rank rank = Rank.valueOf(matchingNumber, lotto.matchBonusNumber(bonusNumber));
                winningRanks.addWinningRanks(rank);
            }
        }
        return winningRanks;
    }

    private List<Lotto> purchaseLottos(Money purchaseAmount) {
        List<Lotto> lottosManual = purchaseLottosManually(purchaseAmount.toLottosSize());
        List<Lotto> lottosAutomatic = generateLottosAutomatically(purchaseAmount.toLottosSize() - lottosManual.size());
        printLottos(lottosManual, lottosAutomatic);
        return Stream.concat(lottosManual.stream(), lottosAutomatic.stream()).collect(Collectors.toList());
    }

    private List<Lotto> purchaseLottosManually(int lottosSize) {
        int numberToBuyManually;
        try {
            numberToBuyManually = InputView.inputNumberToBuyManually(lottosSize);
        } catch (InvalidInputException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            numberToBuyManually = InputView.inputNumberToBuyManually(lottosSize);
        }

        List<Set<LottoNumber>> lottoNumbersBasket = InputView.inputManualLottos(numberToBuyManually);
        return LottosGenerator.generateManually(lottoNumbersBasket);
    }

    private List<Lotto> generateLottosAutomatically(int lottosSize) {
        List<Lotto> lottos = LottosGenerator.generateAutomatically(lottosSize);

        return lottos;
    }

    private void printLottos(List<Lotto> lottosManual, List<Lotto> lottosAutomatic) {
        OutputView.printLottos(lottosManual, lottosAutomatic);

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

}
