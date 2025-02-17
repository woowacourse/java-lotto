package model;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LottoEvaluator {
    private static final int COUNTER_STEP = 1;
    private static final int DEFAULT_COUNT = 0;
    private final WinningLotto winningLotto;

    public LottoEvaluator(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public Map<Prize, Integer> getResult(Lottos lottos) {
        Map<Prize, Integer> result = new TreeMap<>();
        Arrays.stream(Prize.values()).forEach(prize -> result.put(prize, DEFAULT_COUNT));
        lottos.stream()
                .forEach(lotto -> {
                    Prize prize = calculatePrize(lotto);
                    if (prize != Prize.LAST) {
                        result.merge(prize, COUNTER_STEP, Integer::sum);
                    }
                });
        return result;
    }

    public double computeProfit(Lottos lottos) {
        Map<Prize, Integer> result = getResult(lottos);
        int sum = result.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        return sum / (double) lottos.computeTicketAmount();
    }

    public Prize calculatePrize(Lotto userLotto) {
        boolean hasBonusNumber = containsBonusNumber(userLotto);
        return Prize.findPrize(countMatchingNumbers(userLotto), hasBonusNumber);
    }

    private int countMatchingNumbers(Lotto userLotto) {
        Set<LottoNumber> winningLottoNumbers = winningLotto.getLotto().getLottoNumbers();
        Set<LottoNumber> userLottoNumbers = userLotto.getLottoNumbers();
        userLottoNumbers.retainAll(winningLottoNumbers);
        return userLottoNumbers.size();
    }

    private boolean containsBonusNumber(Lotto userLotto) {
        return userLotto.containsNumber(winningLotto.getBonus());
    }

}
