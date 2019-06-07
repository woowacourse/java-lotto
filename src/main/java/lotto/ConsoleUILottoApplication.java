package lotto;

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
        PurchaseAmount lottoPurchaseAmount = createPurchaseAmount();

        LottoQuantity totalLottoQuantity = lottoPurchaseAmount.maxLottoQuantity();
        LottoQuantity manualLottoQuantity = createManualLottoQuantity(totalLottoQuantity);
        LottoQuantity autoLottoQuantity = totalLottoQuantity.subtract(manualLottoQuantity);

        LottoTicketGroup lottos = createLottos(manualLottoQuantity, autoLottoQuantity);
        OutputView.printLottoTickets(lottos, manualLottoQuantity, autoLottoQuantity);

        lottoPurchaseAmount.buy(lottos.price());
        OutputView.printChange(lottoPurchaseAmount);

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

    private static LottoQuantity createManualLottoQuantity(LottoQuantity totalLottoQuantity) {
        LottoQuantity manualLottoQuantity = LottoQuantity.create(InputView.inputManualLottoQuantity());

        if (manualLottoQuantity.biggerThan(totalLottoQuantity)) {
            OutputView.printErrorMessage("생성 가능 로또 개수보다 큽니다.");
            return createManualLottoQuantity(totalLottoQuantity);
        }

        return manualLottoQuantity;
    }

    private static LottoTicketGroup createLottos(LottoQuantity manualLottoQuantity, LottoQuantity autoLottoQuantity) {
        try {
            LottoTicketGroup manualLottos = LottoMachine.generateLottos(getManualLottosText(manualLottoQuantity));
            LottoTicketGroup autoLottos = LottoMachine.generateLottos(autoLottoQuantity);

            return manualLottos.combine(autoLottos);
        } catch (InvalidLottoTicketException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createLottos(manualLottoQuantity, autoLottoQuantity);
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