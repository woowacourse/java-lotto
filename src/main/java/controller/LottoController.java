package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Lotto;
import model.LottoFactory;
import model.LottoOrder;
import model.LottoPurchasingMoney;
import model.LottoRank;
import model.WinningLotto;
import model.WinningStatistics;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        LottoPurchasingMoney inputLottoPurchasingMoney = inputPurchasingMoney();
        int manualLottoCount = inputManualLottoCount();
        LottoOrder lottoOrder = new LottoOrder(inputLottoPurchasingMoney, manualLottoCount);

        List<Lotto> lotteries = purchaseLotteries(lottoOrder);
        OutputView.printPurchasedLotteries(lottoOrder, lotteries);

        WinningLotto winningLotto = inputWinningLotto();

        WinningStatistics winningStatistics = calculateStatistics(lotteries, winningLotto);

        OutputView.printStatistics(winningStatistics, inputLottoPurchasingMoney);
    }

    private LottoPurchasingMoney inputPurchasingMoney() {
        try {
            return LottoPurchasingMoney.valueOf(InputView.inputMoney());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchasingMoney();
        }
    }

    private int inputManualLottoCount() {
        try {
            return InputView.inputManualLottoCount();
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualLottoCount();
        }
    }

    private List<Lotto> purchaseLotteries(LottoOrder lottoOrder) {
        return Stream.of(purchaseManualLotteries(lottoOrder), purchaseAutoLotteries(lottoOrder))
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<List<Integer>> inputManualLottoNumbers(LottoOrder lottoOrder) {
        try {
            return InputView.inputManualLottoNumbers(lottoOrder.getManualLottoCount());
        } catch (IOException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualLottoNumbers(lottoOrder);
        }
    }

    private List<Lotto> purchaseManualLotteries(LottoOrder lottoOrder) {
        LottoFactory lottoFactory = LottoFactory.getInstance();
        try {
            List<List<Integer>> manualLottoNumbers = inputManualLottoNumbers(lottoOrder);
            return manualLottoNumbers.stream()
                    .map(lottoFactory::generateManual)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return purchaseManualLotteries(lottoOrder);
        }
    }

    private List<Lotto> purchaseAutoLotteries(LottoOrder lottoOrder) {
        LottoFactory lottoFactory = LottoFactory.getInstance();
        List<Lotto> autoLotteries = new ArrayList<>();
        for (int i = 0; i < lottoOrder.getAutoLottoCount(); i++) {
            autoLotteries.add(lottoFactory.generateAuto());
        }
        return autoLotteries;
    }

    private WinningLotto inputWinningLotto() {
        try {
            return WinningLotto.of(InputView.inputWinningNumbers(), InputView.inputBonusBall());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningLotto();
        }
    }

    private WinningStatistics calculateStatistics(List<Lotto> lotteries, WinningLotto winningLotto) {
        WinningStatistics winningStatistics = new WinningStatistics();

        lotteries.forEach(lotto -> {
            LottoRank lottoRank = LottoRank.getRank(
                    winningLotto.countMatching(lotto),
                    winningLotto.containBonusBall(lotto)
            );
            winningStatistics.put(lottoRank);
        });

        return winningStatistics;
    }
}
