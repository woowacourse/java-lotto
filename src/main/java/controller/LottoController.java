package controller;

import generator.NumberGenerator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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
    private final NumberGenerator numberGenerator;

    public LottoController(
        final InputView inputView,
        final OutputView outputView,
        final NumberGenerator numberGenerator
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        final PurchaseAmount purchaseAmount = executeWithRetry(this::inputPurchaseAmount);
        outputView.printPurchaseQuantity(purchaseAmount.calculateLottoCount());

        final LottoMachine lottoMachine = new LottoMachine(numberGenerator);
        final List<Lotto> lottos = lottoMachine.issueLottos(purchaseAmount);
        outputView.printLottoNumbers(convertLottos(lottos));

        final WinningNumbers winningNumbers = executeWithRetry(this::inputWinningNumbers);
        final WinningResult winningResult = WinningResult.of(lottos, winningNumbers);
        outputView.printLottoStatistics(winningResult.calculateRateOfRevenue(), winningResult.getLottoRanks(),
            winningResult.isDamage());
    }

    private PurchaseAmount inputPurchaseAmount() {
        return new PurchaseAmount(inputView.readPurchaseAmount());
    }

    private WinningNumbers inputWinningNumbers() {
        final List<Integer> winningNumber = inputView.readWinningNumber();
        final int bonusBall = inputView.readBonusBall();
        return WinningNumbers.of(winningNumber, bonusBall);
    }

    private List<List<Integer>> convertLottos(final List<Lotto> lottos) {
        return lottos.stream()
            .map(Lotto::getLottoNumbers)
            .map(this::convertLottoNumbers)
            .collect(Collectors.toList());
    }

    private List<Integer> convertLottoNumbers(final List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toList());
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
