package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.purchaseamount.TotalPurchaseAmount;
import lotto.dto.InputLottoDto;
import lotto.view.input.InputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMachineInitializer {
    private final InputView inputView;

    public LottoMachineInitializer(final InputView inputView) {
        this.inputView = inputView;
    }

    public LottoMachine initializeLottoService(final int lottoPrice) {
        final TotalPurchaseAmount.TotalPurchaseAmountBuilder totalPurchaseAmountBuilder =
                inputTotalPurchaseAmount(lottoPrice);
        final TotalPurchaseAmount totalPurchaseAmount = inputManualPurchaseAmount(totalPurchaseAmountBuilder).build();
        final List<Lotto> manualLottos = inputManualLottos(totalPurchaseAmount.getCountOfManualLottoNumbers());
        return new LottoMachine(totalPurchaseAmount, manualLottos);
    }

    private TotalPurchaseAmount.TotalPurchaseAmountBuilder inputTotalPurchaseAmount(final int lottoPrice) {
        try {
            final String purchaseAmountInput = inputView.inputPurchaseAmount();
            return new TotalPurchaseAmount.TotalPurchaseAmountBuilder()
                    .setLottoPrice(lottoPrice)
                    .setTotalAmount(purchaseAmountInput);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputTotalPurchaseAmount(lottoPrice);
        }
    }

    private TotalPurchaseAmount.TotalPurchaseAmountBuilder inputManualPurchaseAmount(
            final TotalPurchaseAmount.TotalPurchaseAmountBuilder totalPurchaseAmountBuilder) {
        try {
            final String manualPurchaseLottoAmount = inputView.inputManualPurchaseAmount();
            return totalPurchaseAmountBuilder.setManualPurchaseAmount(manualPurchaseLottoAmount);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputManualPurchaseAmount(totalPurchaseAmountBuilder);
        }
    }

    private List<Lotto> inputManualLottos(final int numberOfManualLottos) {
        try {
            final List<InputLottoDto> manualInputLottos = inputView.inputManualLottoNumbers(numberOfManualLottos);
            final List<Lotto> manual = manualInputLottos.stream()
                    .map(InputLottoDto::getNumbers)
                    .map(Lotto::new)
                    .collect(Collectors.toUnmodifiableList());
            return new Lottos(manual).getLottos();
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return inputManualLottos(numberOfManualLottos);
        }
    }
}
