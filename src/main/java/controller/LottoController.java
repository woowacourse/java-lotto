package controller;

import java.util.List;
import java.util.function.Supplier;
import model.Lotto;
import model.LottoMachine;
import model.LottoNumber;
import model.PurchaseAmount;
import model.WinningNumbers;
import model.WinningResult;
import view.input.InputView;
import view.output.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final PurchaseAmount purchaseAmount = executeWithRetry(
                () -> new PurchaseAmount(inputView.readPurchaseAmount()));
        outputView.printPurchaseQuantity(purchaseAmount.calculateLottoCount());

        final LottoMachine lottoMachine = new LottoMachine();
        final List<Lotto> lottos = lottoMachine.issueLottos(purchaseAmount);
        outputView.printLottoNumbers(convertLottos(lottos));

        final WinningNumbers winningNumbers = executeWithRetry(() -> {
            List<Integer> winningNumber = inputView.readWinningNumber();
            int bonusBall = inputView.readBonusBall();
            return WinningNumbers.of(winningNumber, bonusBall);
        });

        final WinningResult winningResult = WinningResult.of(lottos, winningNumbers);
        outputView.printLottoStatistics(winningResult.calculateRateOfRevenue(), winningResult.getLottoRanks(),
                winningResult.isDamage());
    }

    private List<List<Integer>> convertLottos(final List<Lotto> lottos) {
        return lottos.stream()
                .map(Lotto::getLottoNumbers)
                .map(this::convertLottoNumbers)
                .toList();
    }

    private List<Integer> convertLottoNumbers(final List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

    private <T> T executeWithRetry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (final IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
