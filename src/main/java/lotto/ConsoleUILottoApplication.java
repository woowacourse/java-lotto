package lotto;

import lotto.domain.LottoMachine;
import lotto.domain.lotto.InvalidLottoTicketException;
import lotto.domain.lotto.LottoTicket;
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
        LottoTicketGroup manualLottos = LottoMachine
                .createManualLottos(createManualLottoNumbers(purchaseAmount), purchaseAmount);
        LottoTicketGroup autoLottos = LottoMachine.createAutoLottos(purchaseAmount);
        OutputView.printChange(purchaseAmount);
        OutputView.printLottoTicketGroup(manualLottos, autoLottos);

        WinningLotto winningLotto = createWinningLotto(createWinningLottoTicket());
        LottoResult lottoResult = new LottoResult(winningLotto, manualLottos.combine(autoLottos));
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

    private static List<String> createManualLottoNumbers(PurchaseAmount purchaseAmount) {
        return InputView.inputManualLottoNumbers(createManualLottoQuantity(purchaseAmount));
    }

    private static int createManualLottoQuantity(PurchaseAmount purchaseAmount) {
        try {
            int manualLottoQuantity = Integer.parseInt(InputView.inputManualLottoQuantity());
            validateManualLottoQuantity(purchaseAmount, manualLottoQuantity);
            return manualLottoQuantity;
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage("숫자를 입력하세요");
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return createManualLottoQuantity(purchaseAmount);
    }

    private static void validateManualLottoQuantity(PurchaseAmount purchaseAmount, int manualLottoQuantity) {
        if (!purchaseAmount.canBuy(manualLottoQuantity * LottoTicket.getPrice())) {
            throw new IllegalArgumentException("구입 금액으로는 구매 불가한 개수입니다.");
        }
    }

    private static LottoTicket createWinningLottoTicket() {
        try {
            return LottoTicket.create(InputView.inputWinningNumbers());
        } catch (InvalidLottoTicketException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLottoTicket();
        }
    }

    private static WinningLotto createWinningLotto(LottoTicket winningTicket) {
        try {
            return WinningLotto.create(winningTicket, InputView.inputBonusNumber());
        } catch (InvalidWinningLottoException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLotto(winningTicket);
        }
    }
}
