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
import java.util.Optional;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        PurchaseAmount lottoPurchaseAmount = createLottoPurchaseAmount();

        LottoQuantity totalLottoQuantity = lottoPurchaseAmount.maxLottoQuantity();
        LottoQuantity manualLottoQuantity = createManualLottoQuantity(totalLottoQuantity);
        LottoQuantity autoLottoQuantity = totalLottoQuantity.subtract(manualLottoQuantity);

        LottoTicketGroup lottos = createLottos(manualLottoQuantity, autoLottoQuantity);
        OutputView.printLottoTickets(lottos, manualLottoQuantity, autoLottoQuantity);

        PurchaseAmount change = lottoPurchaseAmount.buy(lottos.price());
        OutputView.printChange(change);

        LottoResult lottoResult = new LottoResult(createWinningLotto(), lottos);
        OutputView.printLottoResult(lottoResult);
    }

    private static PurchaseAmount createLottoPurchaseAmount() {
        Optional<PurchaseAmount> purchaseAmount;
        do {
            purchaseAmount = createOptionalPurchaseAmount();
        } while(purchaseAmount.isEmpty());

        return purchaseAmount.get();
    }

    private static Optional<PurchaseAmount> createOptionalPurchaseAmount() {
        try {
            return Optional.of(getLottoPurchaseAmount());
        } catch (PurchaseAmountException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private static PurchaseAmount getLottoPurchaseAmount() {
        PurchaseAmount purchaseAmount = PurchaseAmount.create(InputView.inputPurchaseAmount());

        if (purchaseAmount.canBuy(LottoTicket.PRICE)) {
            return purchaseAmount;
        }
        throw new PurchaseAmountException("로또 한 장은 " + LottoTicket.PRICE + "원 입니다.");
    }

    private static LottoQuantity createManualLottoQuantity(LottoQuantity totalLottoQuantity) {
        Optional<LottoQuantity> lottoQuantity;
        do {
            lottoQuantity = createOptionalLottoQuantity(totalLottoQuantity);
        } while(lottoQuantity.isEmpty());

        return lottoQuantity.get();
    }

    private static Optional<LottoQuantity> createOptionalLottoQuantity(LottoQuantity totalLottoQuantity) {
        try {
            return Optional.of(getLottoQuantity(totalLottoQuantity));
        } catch (InvalidLottoQuantityException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private static LottoQuantity getLottoQuantity(LottoQuantity totalLottoQuantity) {
        LottoQuantity lottoQuantity = LottoQuantity.create(InputView.inputManualLottoQuantity());

        if (lottoQuantity.biggerThan(totalLottoQuantity)) {
            throw new InvalidLottoQuantityException("생성 가능 로또 개수보다 큽니다.");
        }
        return lottoQuantity;
    }

    private static LottoTicketGroup createLottos(LottoQuantity manualLottoQuantity, LottoQuantity autoLottoQuantity) {
        Optional<LottoTicketGroup> lottos;

        do {
            lottos = createOptionalLottos(manualLottoQuantity, autoLottoQuantity);
        } while (lottos.isEmpty());

        return lottos.get();
    }

    private static Optional<LottoTicketGroup> createOptionalLottos(LottoQuantity manualLottoQuantity, LottoQuantity autoLottoQuantity) {
        try {
            LottoTicketGroup manualLottos = LottoMachine.generateLottos(getManualLottosText(manualLottoQuantity));
            LottoTicketGroup autoLottos = LottoMachine.generateLottos(autoLottoQuantity);

            return Optional.of(manualLottos.combine(autoLottos));
        } catch (InvalidLottoNumberGroupException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private static List<String> getManualLottosText(LottoQuantity manualLottoQuantity) {
        if (manualLottoQuantity == LottoQuantity.ZERO) {
            return new ArrayList<>();
        }

        return InputView.inputManualLottoNumbers(manualLottoQuantity);
    }

    private static WinningLotto createWinningLotto() {
        Optional<WinningLotto> winningLotto;

        do {
            winningLotto = createOptionalWinningLotto();
        } while(winningLotto.isEmpty());

        return winningLotto.get();
    }

    private static Optional<WinningLotto> createOptionalWinningLotto() {
        try {
            return Optional.of(WinningLotto.create(
                    InputView.inputWinningNumbers(), InputView.inputBonusNumber())
            );
        } catch (InvalidWinningLottoException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}