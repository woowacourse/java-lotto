package lotto;

import static lotto.LottoNumberConstants.LOTTO_NUMBER_MAX;
import static lotto.LottoNumberConstants.LOTTO_NUMBER_MIN;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.view.Console;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        int purchaseAmount = inputView.requestPurchaseAmount();
        List<Lotto> lottos = purchaseLotto(purchaseAmount);
        Lotto winningLotto = getWinningLotto();
        int bonusNumber = inputView.requestBonusNumber();
        validateBonusNumber(winningLotto, bonusNumber);
        DashBoard dashBoard = judgeLottoResult(lottos, winningLotto, bonusNumber);
        printResult(dashBoard, purchaseAmount);
        end();
    }

    private List<Lotto> purchaseLotto(int purchaseAmount) {
        Cashier cashier = new Cashier();
        List<Lotto> lottos = cashier.payForLotto(purchaseAmount);
        outputView.printLottos(convertLottoDtos(lottos));
        return lottos;
    }

    private List<LottoDto> convertLottoDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(LottoDto::of)
                .toList();
    }

    private Lotto getWinningLotto() {
        return new Lotto(Set.copyOf(inputView.requestWinningNumbers()));
    }

    private void validateBonusNumber(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
        }
        if (bonusNumber < LOTTO_NUMBER_MIN.value() || bonusNumber > LOTTO_NUMBER_MAX.value()) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 수여야 합니다.");
        }
    }

    private float calculateRatio(Map<Rank, Integer> ranks, int purchaseAmount) {
        return (float) calculateTotalWinningAmount(ranks) / purchaseAmount;
    }

    private int calculateTotalWinningAmount(Map<Rank, Integer> ranks) {
        int totalAmount = 0;
        for (Rank rank : ranks.keySet()) {
            totalAmount += rank.getWinningAmount() * ranks.get(rank);
        }
        return totalAmount;
    }

    private DashBoard judgeLottoResult(List<Lotto> lottos, Lotto winningLotto, int bonusNumber) {
        DashBoard dashBoard = new DashBoard();
        for (Lotto lotto : lottos) {
            dashBoard.update(lotto, winningLotto, bonusNumber);
        }
        return dashBoard;
    }

    private void printResult(DashBoard dashBoard, int purchaseAmount) {
        outputView.printResult(dashBoard.getRanks());
        outputView.printWinningRatio(calculateRatio(dashBoard.getRanks(), purchaseAmount));
    }

    private void end() {
        Console.close();
    }
}
