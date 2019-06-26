package lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.InputUtil;
import com.woowacourse.lotto.view.ConsoleInputView;
import com.woowacourse.lotto.view.ConsoleOutputView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        List<Lotto> lottos = getLottos();
        WinningLotto winningLotto = createWinningLotto();
        WinningAggregator aggregator = new WinningAggregator();
        lottos.forEach(l -> aggregator.addResult(winningLotto.match(l)));
        printAggregation(aggregator);
    }

    private static List<Lotto> getLottos() {
        LottoQuantity quantity = createBuyingMoney().getQuantity();
        NumberGenerator numberGenerator = new RandomNumberGenerator(LottoNumber.LOTTO_NUMBER_MIN, LottoNumber.LOTTO_NUMBER_MAX);
        List<Lotto> lottos = getManualLottos(quantity);
        final int numOfManuals = lottos.size();
        for (int i = 0; i < quantity.toInt() - numOfManuals; i++) {
            lottos.add(LottoFactory.createLotto(numberGenerator));
        }
        ConsoleOutputView.printLottos(lottos, numOfManuals);
        return lottos;
    }

    private static BuyingMoney createBuyingMoney() {
        Optional<BuyingMoney> bm = tryCreateBuyingMoney();
        while (!bm.isPresent()) {
            bm = tryCreateBuyingMoney();
        }
        return bm.get();
    }

    private static Optional<BuyingMoney> tryCreateBuyingMoney() {
        try {
            return Optional.of(new BuyingMoney(Integer.parseInt(ConsoleInputView.promptBuyingMoney())));
        } catch (IllegalArgumentException e) {
            ConsoleOutputView.printError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static List<Lotto> getManualLottos(LottoQuantity totalLottoQuantity) {
        LottoQuantity manualLottoQuantity = createManualLottoQuantity();

        while (totalLottoQuantity.isMoreThan(manualLottoQuantity)) {
            ConsoleOutputView.printError(String.format("최대 %d장 까지 구매할 수 있습니다.", totalLottoQuantity.toInt()));
            manualLottoQuantity = createManualLottoQuantity();
        }

        return createManualLottos(manualLottoQuantity);
    }

    private static LottoQuantity createManualLottoQuantity() {
        Optional<LottoQuantity> maybeQuantity = tryCreateManualLottoQuantity();
        while (!maybeQuantity.isPresent()) {
            maybeQuantity = tryCreateManualLottoQuantity();
        }

        return maybeQuantity.get();
    }

    private static Optional<LottoQuantity> tryCreateManualLottoQuantity() {
        try {
            return Optional.of(LottoQuantity.of(ConsoleInputView.promptManualLottoQuantity()));
        } catch (IllegalArgumentException e) {
            ConsoleOutputView.printError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static List<Lotto> createManualLottos(LottoQuantity manualLottoQuantity) {
        Optional<List<Lotto>> maybeManualLottos = tryCreateManualLottos(manualLottoQuantity);

        while (!maybeManualLottos.isPresent()) {
            maybeManualLottos = tryCreateManualLottos(manualLottoQuantity);
        }
        return maybeManualLottos.get();
    }

    private static Optional<List<Lotto>> tryCreateManualLottos(LottoQuantity manualLottoQuantity) {
        try {
            return Optional.of(ConsoleInputView.promptManualLottoNumbers(manualLottoQuantity).stream()
                .map(InputUtil::splitByComma)
                .map(LottoNumberGroup::of)
                .map(LottoFactory::createLotto)
                .collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            ConsoleOutputView.printError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static WinningLotto createWinningLotto() {
        Optional<WinningLotto> maybeWinningLotto = tryCreateWinningLotto();

        while (!maybeWinningLotto.isPresent()) {
            maybeWinningLotto = tryCreateWinningLotto();
        }
        return maybeWinningLotto.get();
    }

    private static Optional<WinningLotto> tryCreateWinningLotto() {
        try {
            LottoNumberGroup winningNums = LottoNumberGroup.of(
                InputUtil.splitByComma(ConsoleInputView.promptWinningNumber()));
            int winningBonusNumber = Integer.parseInt(ConsoleInputView.promptWinningBonusNumber());
            return Optional.of(new WinningLotto(winningNums, LottoNumber.of(winningBonusNumber)));
        } catch (NumberFormatException e) {
            ConsoleOutputView.printError("숫자를 입력해주세요.");
        } catch (IllegalArgumentException e) {
            ConsoleOutputView.printError(e.getMessage());
        }
        return Optional.empty();
    }

    private static void printAggregation(WinningAggregator aggregator) {
        ConsoleOutputView.printResultTitle();
        ConsoleOutputView.printAggregationItem(LottoResult.FIFTH, aggregator.getResultCount(LottoResult.FIFTH));
        ConsoleOutputView.printAggregationItem(LottoResult.FOURTH, aggregator.getResultCount(LottoResult.FOURTH));
        ConsoleOutputView.printAggregationItem(LottoResult.THIRD, aggregator.getResultCount(LottoResult.THIRD));
        ConsoleOutputView.printAggregationItem(LottoResult.SECOND, aggregator.getResultCount(LottoResult.SECOND));
        ConsoleOutputView.printAggregationItem(LottoResult.FIRST, aggregator.getResultCount(LottoResult.FIRST));
        ConsoleOutputView.printEarningRate(aggregator.calculateEarningRate(Lotto.UNIT_PRICE));
    }
}
