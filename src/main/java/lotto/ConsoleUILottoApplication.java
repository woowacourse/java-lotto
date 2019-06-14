package lotto;

import lotto.domain.InvalidLottoQuantityException;
import lotto.domain.LottoMachine;
import lotto.domain.LottoQuantity;
import lotto.domain.lotto.InvalidLottoNumberGroupException;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.lottoresult.*;
import lotto.domain.purchaseamount.PurchaseAmount;
import lotto.domain.purchaseamount.PurchaseAmountException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        PurchaseAmount lottoPurchaseAmount = createLottoPurchaseAmount();

        LottoQuantity manualLottoQuantity = createManualLottoQuantity(lottoPurchaseAmount);
        LottoQuantity autoLottoQuantity = lottoPurchaseAmount.maxLottoQuantity().subtract(manualLottoQuantity);

        LottoTicketGroup lottos = createLottos(manualLottoQuantity, autoLottoQuantity);
        OutputView.printLottoTickets(lottos, manualLottoQuantity, autoLottoQuantity);

        PurchaseAmount change = lottoPurchaseAmount.buy(lottos.price());
        OutputView.printChange(change);

        LottoResult lottoResult = lottos.match(createWinningLotto());
        OutputView.printLottoResult(getCounts(lottoResult), lottoResult.getEarningRate());
    }

    private static PurchaseAmount createLottoPurchaseAmount() {
        Optional<PurchaseAmount> purchaseAmount;
        do {
            purchaseAmount = createOptionalPurchaseAmount();
        } while (!purchaseAmount.isPresent());

        return purchaseAmount.get();
    }

    private static Optional<PurchaseAmount> createOptionalPurchaseAmount() {
        try {
            return Optional.of(
                    PurchaseAmount.createLottoPurchaseAmount(InputView.inputPurchaseAmount())
            );
        } catch (PurchaseAmountException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private static LottoQuantity createManualLottoQuantity(PurchaseAmount lottoPurchaseAmount) {
        Optional<LottoQuantity> lottoQuantity;
        do {
            lottoQuantity = createOptionalLottoQuantity(lottoPurchaseAmount);
        } while (!lottoQuantity.isPresent());

        return lottoQuantity.get();
    }

    private static Optional<LottoQuantity> createOptionalLottoQuantity(PurchaseAmount purchaseAmount) {
        try {
            return Optional.of(
                    LottoQuantity.create(InputView.inputManualLottoQuantity(), purchaseAmount)
            );
        } catch (InvalidLottoQuantityException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }

    private static LottoTicketGroup createLottos(LottoQuantity manualLottoQuantity, LottoQuantity autoLottoQuantity) {
        Optional<LottoTicketGroup> lottos;

        do {
            lottos = createOptionalLottos(manualLottoQuantity, autoLottoQuantity);
        } while (!lottos.isPresent());

        return lottos.get();
    }

    private static Optional<LottoTicketGroup> createOptionalLottos(LottoQuantity manualLottoQuantity, LottoQuantity autoLottoQuantity) {
        try {
            LottoTicketGroup manualLottos = LottoMachine.generateManualLottos(getManualLottosText(manualLottoQuantity));
            LottoTicketGroup autoLottos = LottoMachine.generateAutoLottos(autoLottoQuantity);

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
        } while (!winningLotto.isPresent());

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

    private static List<RankCount> getCounts(LottoResult lottoResult) {
        return Arrays.asList(LottoRank.values()).stream()
                .sorted((x,y) -> x.getReward() - y.getReward())
                .map(rank -> new RankCount(rank, lottoResult.getCountOf(rank)))
                .collect(Collectors.toList());
    }

}