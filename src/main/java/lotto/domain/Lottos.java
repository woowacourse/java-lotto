package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 100;
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_INVALID_QUANTITY =
            "로또는 " + MIN_QUANTITY + "장부터 최대 " + MAX_QUANTITY + "장까지 구매 가능합니다.";

    private final List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos ofAmount(final int amount) {
        int quantity = amount / LOTTO_PRICE;
        validateQuantity(quantity);
        List<Lotto> lottos = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            Lotto generatedLotto = Lotto.of(LottoGenerator.generate());
            lottos.add(generatedLotto);
        }
        return new Lottos(lottos);
    }

    private static void validateQuantity(final int quantity) {
        if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(ERROR_INVALID_QUANTITY);
        }
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public double calculateEarningRate(List<Prize> prizes) {
        long prizeTotal = Prize.calculateTotalPrize(prizes);
        return (double) prizeTotal / (getQuantity() * LOTTO_PRICE);
    }

    public List<Prize> calculatePrizes(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> Prize.getPrizePlace(
                        winningLotto.matchWinningNumbers(lotto), winningLotto.isBonusMatched(lotto)))
                .toList();
    }

    public int getQuantity() {
        return lottos.size();
    }

    public List<List<Integer>> getPurchasedLottos() {
        return lottos.stream().map(Lotto::getNumbers).toList();
    }
}
