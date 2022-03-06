package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private static final int DEFAULT_VALUE = 0;

    private final LottoGameMoney purchaseMoney;
    private final Map<LottoReward, Integer> statistics = new EnumMap<>(LottoReward.class);

    public WinningStatistics(LottoGameMoney purchaseMoney, List<LottoReward> lottoRewards) {
        validateNull(purchaseMoney, lottoRewards);
        this.purchaseMoney = purchaseMoney;
        initializeStatistics(lottoRewards);
    }

    private void validateNull(LottoGameMoney purchaseMoney, List<LottoReward> lottoRewards) {
        if (purchaseMoney == null) {
            throw new NullPointerException("WinningStatistics 생성시 구매 금액이 null 일 수 없습니다.");
        }
        if (lottoRewards == null) {
            throw new NullPointerException("WinningStatistics 생성시 로또 리워드 결과가 null 일 수 없습니다.");
        }
    }

    private void initializeStatistics(List<LottoReward> lottoRewards) {
        Arrays.stream(LottoReward.values()).forEach(lottoReward -> statistics.put(lottoReward, DEFAULT_VALUE));
        lottoRewards.forEach(lottoReward -> statistics.replace(lottoReward, statistics.get(lottoReward) + 1));
    }

    public double profitRate() {
        int winningAmount = calculateWinningAmount();
        int purchasedLottoAmount = purchaseMoney.amount();
        return (double)winningAmount / purchasedLottoAmount;
    }

    private int calculateWinningAmount() {
        return Arrays.stream(LottoReward.values())
            .mapToInt(reward -> reward.price() * statistics.get(reward))
            .sum();
    }

    public Map<LottoReward, Integer> values() {
        return Collections.unmodifiableMap(statistics);
    }
}
