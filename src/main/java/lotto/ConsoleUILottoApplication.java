package lotto;

import lotto.domain.InvalidLottoQuantityException;
import lotto.domain.LottoMachine;
import lotto.domain.LottoQuantity;
import lotto.domain.lotto.InvalidLottoTicketException;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.lottoresult.InvalidWinningLottoException;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.purchaseamount.PurchaseAmount;
import lotto.domain.purchaseamount.PurchaseAmountException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = createPurchaseAmount();

        LottoTicketGroup manualLottos = createManualLottos(purchaseAmount);
        LottoTicketGroup autoLottos = LottoMachine.generateLottos(purchaseAmount);

        OutputView.printChange(purchaseAmount);
        OutputView.printLottoTicketGroup(manualLottos, autoLottos);

        LottoTicketGroup lottos = manualLottos.combine(autoLottos);
        LottoResult lottoResult = new LottoResult(createWinningLotto(), lottos);
        OutputView.printLottoResult(lottoResult);
    }

    private static PurchaseAmount createPurchaseAmount() {
        try {
            return PurchaseAmount.create(InputView.inputPurchaseAmount());
        } catch (PurchaseAmountException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createPurchaseAmount();
        }
    }

    private static LottoTicketGroup createManualLottos(PurchaseAmount purchaseAmount) {
        List<String> manualLottosText = InputView.inputManualLottoNumbers(createManualLottoQuantity(purchaseAmount));
        try {
            return LottoMachine.generateLottos(manualLottosText, purchaseAmount);
        } catch (InvalidLottoTicketException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createManualLottos(purchaseAmount);
        }
    }

    private static LottoQuantity createManualLottoQuantity(PurchaseAmount purchaseAmount) {
        try {
            LottoQuantity manualLottoQuantity = LottoQuantity.create(InputView.inputManualLottoQuantity());
            manualLottoQuantity.validateAvailable(purchaseAmount);
            return manualLottoQuantity;
        } catch (InvalidLottoQuantityException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createManualLottoQuantity(purchaseAmount);
        }
    }

    private static WinningLotto createWinningLotto() {
        try {
            return WinningLotto.create(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (InvalidWinningLottoException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLotto();
        }
    }
}
