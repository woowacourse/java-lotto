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
import model.WinningLotto;
import model.WinningStatistics;
import model.dto.LottoDto;
import model.dto.LottoRequestDto;
import model.dto.WinningStatisticsDto;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        LottoPurchasingMoney lottoPurchasingMoney = inputPurchasingMoney();

        LottoOrder lottoOrder = inputLottoOrder(lottoPurchasingMoney);

        List<Lotto> lotteries = purchaseLotteries(lottoOrder);
        printPurchasedLotteries(lottoOrder, lotteries);

        WinningLotto winningLotto = inputWinningLotto();

        WinningStatistics winningStatistics = calculateStatistics(lotteries, winningLotto, lottoPurchasingMoney);

        printWinningStatistics(winningStatistics);
    }

    private void printPurchasedLotteries(LottoOrder lottoOrder, List<Lotto> lotteries) {
        List<LottoDto> lotteriesDto = lotteries.stream()
                .map(LottoDto::new)
                .collect(Collectors.toUnmodifiableList());
        OutputView.printPurchasedLotteries(lottoOrder, lotteriesDto);
    }

    private LottoPurchasingMoney inputPurchasingMoney() {
        try {
            return LottoPurchasingMoney.valueOf(InputView.inputMoney());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchasingMoney();
        }
    }

    private LottoOrder inputLottoOrder(LottoPurchasingMoney lottoPurchasingMoney) {
        try {
            int manualLottoCount = InputView.inputManualLottoCount();
            return new LottoOrder(lottoPurchasingMoney, manualLottoCount);
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputLottoOrder(lottoPurchasingMoney);
        }
    }

    private List<Lotto> purchaseLotteries(LottoOrder lottoOrder) {
        return Stream.of(purchaseManualLotteries(lottoOrder), purchaseAutoLotteries(lottoOrder))
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Lotto> purchaseManualLotteries(LottoOrder lottoOrder) {
        LottoFactory lottoFactory = LottoFactory.getInstance();
        try {
            List<LottoRequestDto> manualLottoNumbers = inputManualLottoNumbers(lottoOrder);
            return manualLottoNumbers.stream()
                    .map(numbers -> lottoFactory.generateManual(numbers.getNumbers()))
                    .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return purchaseManualLotteries(lottoOrder);
        }
    }

    private List<LottoRequestDto> inputManualLottoNumbers(LottoOrder lottoOrder) {
        try {
            return InputView.inputManualLottoNumbers(lottoOrder.getManualLottoCount());
        } catch (IOException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualLottoNumbers(lottoOrder);
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

    private WinningStatistics calculateStatistics(List<Lotto> lotteries, WinningLotto winningLotto,
                                                  LottoPurchasingMoney lottoPurchasingMoney) {
        return WinningStatistics.of(lotteries, winningLotto, lottoPurchasingMoney);
    }

    private void printWinningStatistics(WinningStatistics winningStatistics) {
        OutputView.printStatistics(new WinningStatisticsDto(winningStatistics));
    }
}
