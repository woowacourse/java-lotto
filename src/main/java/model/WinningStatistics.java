package model;


import static model.RankType.NONE;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WinningStatistics {
    private final Map<RankType, Integer> winningStatistics;

    public WinningStatistics(LottoRepository lottoRepository, UserLotto userLotto) {
        this.winningStatistics = initStatistics();
        calculateWinningStatistics(lottoRepository, userLotto);
    }

    public Map<RankType, Integer> getWinningStatistics() {
        return winningStatistics;
    }

    private Map<RankType, Integer> initStatistics() {
        Map<RankType, Integer> winningStatistics = new EnumMap<>(RankType.class);

        for(RankType rankType : RankType.values()){
            winningStatistics.put(rankType, 0);
        }
        return winningStatistics;
    }

    private int calculateTotalPrize() {
        int totalPrice = 0;

        for (RankType rankType : winningStatistics.keySet()) {
            totalPrice += rankType.getPrice() * winningStatistics.get(rankType);
        }

        return totalPrice;
    }

    private void updateWinningStatistics(RankType rankType) {
        if (rankType == NONE) {
            return;
        }
        winningStatistics.put(rankType, winningStatistics.get(rankType) + 1);
    }


    private void calculateWinningStatistics(final LottoRepository lottoRepository, final UserLotto userLotto) {
        for (Lotto lotto : lottoRepository.getLottos()) {
            int matchCount = userLotto.calculateMatchCount(lotto);
            boolean bonusMatch = userLotto.isBonusMatch(lotto);

            updateWinningStatistics(RankType.calculateRankType(bonusMatch, matchCount));
        }
    }

    public double calculateWinningRate(Wallet wallet) {
        int totalPrice = calculateTotalPrize();
        return (double) totalPrice / wallet.getMoney();
    }

}
