package domain;

import dto.LottoMatchResult;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import util.NumberPicker;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos purchase(final int purchaseMoney, final NumberPicker numberPicker) {
        final int purchaseableCount = Lotto.getPurchaseableCount(purchaseMoney);
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseableCount; i++) {
            lottos.add(Lotto.purchase(numberPicker));
        }
        return new Lottos(lottos);
    }

    public EnumMap<LottoPrize, Integer> getStatistics(List<Integer> matchNumbers, int bonusNumber) {
        return calculateStatistics(matchNumbers, bonusNumber);
    }

    public double getIncomeRate(List<Integer> matchNumbers, int bonusNumber, int money) {
        EnumMap<LottoPrize, Integer> prizeMap = calculateStatistics(matchNumbers, bonusNumber);
        long totalIncome = calculateTotalIncome(prizeMap);
        return (double) totalIncome / money;
    }

    private long calculateTotalIncome(EnumMap<LottoPrize, Integer> prizeMap) {
        long sum = 0L;
        for (Map.Entry<LottoPrize, Integer> entry : prizeMap.entrySet()) {
            sum += entry.getKey().getPrizeMoney() * entry.getValue();
        }
        return sum;
    }

    private EnumMap<LottoPrize, Integer> calculateStatistics(List<Integer> matchNumbers, int bonusNumber) {
        EnumMap<LottoPrize, Integer> lottoPrizeMap = LottoPrize.getInitailizedEnumMap();
        insertLottoPrizeResult(matchNumbers, bonusNumber, lottoPrizeMap);
        return lottoPrizeMap;
    }

    private void insertLottoPrizeResult(List<Integer> matchNumbers, int bonusNumber, EnumMap<LottoPrize, Integer> enumMap) {
        for (Lotto lotto : lottos) {
            LottoMatchResult matchResult = lotto.getMatchResult(matchNumbers, bonusNumber);
            Optional<LottoPrize> matchPrize = LottoPrize.match(matchResult);
            matchPrize.ifPresent(lottoPrize -> enumMap.put(lottoPrize, enumMap.get(lottoPrize) + 1));
        }
    }

    public int getLottoCount() {
        return lottos.size();
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        for (Lotto lotto : lottos) {
            sj.add(lotto.toString());
        }
        return sj.toString();
    }
}
