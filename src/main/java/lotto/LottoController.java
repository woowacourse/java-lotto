package lotto;

import static lotto.LottoNumberConstants.LOTTO_NUMBER_MAX;
import static lotto.LottoNumberConstants.LOTTO_NUMBER_MIN;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        int purchaseAmount = inputView.requestPurchaseAmount();
        Cashier cashier = new Cashier();
        List<Lotto> lottos = cashier.payForLotto(purchaseAmount);
        outputView.printLottos(convertLottoDtos(lottos));
        Lotto winningLotto = new Lotto(Set.copyOf(inputView.requestWinningNumbers()));
        int bonusNumber = inputView.requestBonusNumber();
        validateBonusNumber(winningLotto, bonusNumber);

        Map<Rank, Integer> ranks = initRanks();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.getMatchCount(winningLotto);
            boolean isBonusMatch = lotto.contains(bonusNumber);

            Rank rank = Rank.calculate(matchCount, isBonusMatch);
            ranks.put(rank, ranks.getOrDefault(rank, 0) + 1);
        }

        outputView.printResult(ranks);
        outputView.printWinningRatio(calculateRatio(ranks, purchaseAmount));
    }

    private void validateBonusNumber(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
        }
        if (bonusNumber < LOTTO_NUMBER_MIN.value() || bonusNumber > LOTTO_NUMBER_MAX.value()) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 수여야 합니다.");
        }
    }

    private List<LottoDto> convertLottoDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(LottoDto::of)
                .toList();
    }

    private Map<Rank, Integer> initRanks() {
        Map<Rank, Integer> ranks = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
        return ranks;
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
}
