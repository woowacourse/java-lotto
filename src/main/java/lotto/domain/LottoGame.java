package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    public void play() {
        Money purchaseAmount = inputPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        Result result = produceResult(lottos, purchaseAmount);
        OutputView.printResult(result);
    }

    private Money inputPurchaseAmount() {
        Integer purchaseAmount = execute(InputView::inputPurchaseAmount);
        return new Money(purchaseAmount);
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

    private Integer inputNumberToBuyManually(Integer lottosSize) {
        return execute(() -> InputView.inputNumberToBuyManually(lottosSize));
    }

    private List<Set<LottoNumber>> inputLottoNumbersBasket(int numberToBuyManually) {
        List<Set<LottoNumber>> lottoNumbersBasket = new ArrayList<>();
        List<Set<Integer>> rawlottoNumbersBasket = execute(() -> InputView.inputManualLottos(numberToBuyManually));
        for (Set<Integer> rawlottoNumbers : rawlottoNumbersBasket) {
            Set<LottoNumber> lottoNumbers = rawlottoNumbers.stream().map(LottoNumber::new).collect(Collectors.toSet());
            lottoNumbersBasket.add(lottoNumbers);
        }

        return lottoNumbersBasket;
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
        List<Integer> winningNumbers = execute(InputView::inputLastWeekWinningNumbers);
        return Lotto.createWinningLotto(winningNumbers);
    }

    private LottoNumber inputBonusNumber() {
        int bonusNumber = execute(InputView::inputBonusNumber);
        return new LottoNumber(bonusNumber);
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

    private <T> T execute(LottoLogic<T> lottoLogic) {
        LottoService<T> lottoService = new LottoService<>(lottoLogic);
        return lottoService.executeOrRepeatWithException();
    }
}
