package lotto;

import lotto.domain.InvalidLottoQuantityException;
import lotto.domain.LottoMachine;
import lotto.domain.LottoQuantity;
import lotto.domain.lotto.*;
import lotto.domain.lottoresult.InvalidWinningLottoException;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.purchaseamount.PurchaseAmount;
import lotto.domain.purchaseamount.PurchaseAmountException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        PurchaseAmount lottoPurchaseAmount = createLottoPurchaseAmount();

        LottoTicketGroup lottos = createLottos(lottoPurchaseAmount);
        OutputView.printChange(lottoPurchaseAmount);

        LottoResult lottoResult = new LottoResult(createWinningLotto(), lottos);
        OutputView.printLottoResult(lottoResult);
    }

    private static PurchaseAmount createLottoPurchaseAmount() {
        PurchaseAmount lottoPurchaseAmount = createPurchaseAmount();

        if (lottoPurchaseAmount.canBuy(LottoTicket.getPrice())) {
            return lottoPurchaseAmount;
        }

        OutputView.printErrorMessage("로또를 구매할 수 없는 금액입니다.");
        return createLottoPurchaseAmount();
    }

    private static PurchaseAmount createPurchaseAmount() {
        try {
            return PurchaseAmount.create(InputView.inputPurchaseAmount());
        } catch (PurchaseAmountException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createPurchaseAmount();
        }
    }

    private static LottoTicketGroup createLottos(PurchaseAmount purchaseAmount) {
        LottoTicketGroup manualLottos = createManualLottos(createManualLottoQuantity(purchaseAmount));
        purchaseAmount.buy(manualLottos.price());

        LottoTicketGroup autoLottos = LottoMachine.generateLottos(LottoQuantity.create(purchaseAmount));
        purchaseAmount.buy(autoLottos.price());

        OutputView.printLottoTicketGroup(manualLottos, autoLottos);

        return manualLottos.combine(autoLottos);
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

    private static LottoTicketGroup createManualLottos(LottoQuantity manualLottoQuantity) {
        try {
            return LottoMachine.generateLottos(getManualLottosText(manualLottoQuantity));
        } catch (InvalidLottoTicketException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createManualLottos(manualLottoQuantity);
        }
    }

    private static List<String> getManualLottosText(LottoQuantity manualLottoQuantity) {
        List<String> manualLottosText = new ArrayList<>();

        if (!manualLottoQuantity.equals(LottoQuantity.ZERO)) {
            manualLottosText.addAll(InputView.inputManualLottoNumbers(manualLottoQuantity));
        }

        return manualLottosText;
    }

    private static WinningLotto createWinningLotto() {
        LottoTicket winningLotto = createWinningLottoTicket();
        LottoNumber bonusNumber = createBonusNumber();

        try {
            return new WinningLotto(winningLotto, bonusNumber);
        } catch (InvalidWinningLottoException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLotto();
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

    private static LottoNumber createBonusNumber() {
        try {
            return LottoNumber.of(InputView.inputBonusNumber());
        } catch (InvalidLottoNumberException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createBonusNumber();
        }
    }
}
