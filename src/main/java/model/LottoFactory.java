package model;

import static constant.LottoConstant.DAMAGE;
import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_NUMBER_MIN_RANGE;
import static constant.LottoConstant.LOTTO_PURCHASE_UNIT;
import static constant.LottoConstant.PROFIT;
import static model.Prize.initialize;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;
import util.random.RandomUtil;

public class LottoFactory {

    private final int lottoCount;
    private final List<Lotto> issuedLottoTickets;
    private final RandomUtil randomUtil;

    public static LottoFactory of(final LottoPurchase lottoPurchase, final RandomUtil randomUtil) {
        return new LottoFactory(lottoPurchase.calculateLottoCount(), randomUtil);
    }

    private LottoFactory(final int lottoCount, final RandomUtil randomUtil) {
        this.lottoCount = lottoCount;
        this.randomUtil = randomUtil;
        this.issuedLottoTickets = issueLottoTickets();
    }

    public EnumMap<Prize, Integer> getStatistic(final Lotto lotto, final Bonus bonus) {
        EnumMap<Prize, Integer> prizes = initialize();
        for (Lotto issuedTicket : issuedLottoTickets) {
            int matchCount = lotto.matchCount(issuedTicket);
            boolean matchesBonus = bonus.isContainedIn(lotto);
            Prize foundPrize = Prize.find(matchCount, matchesBonus);

            prizes.put(foundPrize, prizes.get(foundPrize) + 1);
        }

        return prizes;
    }

    public double getWinningAmount(final EnumMap<Prize, Integer> prizes) {
        int principalMoney = lottoCount * LOTTO_PURCHASE_UNIT;
        int winningAmount = Prize.calculateWinningAmount(prizes);

        return (double) winningAmount / principalMoney;
    }

    public String lossOrGain(final double winningAmount) {
        if (winningAmount >= 1) {
            return PROFIT;
        }
        return DAMAGE;
    }

    public String lottoCountToString() {
        return String.valueOf(lottoCount);
    }

    public List<String> issuedLottoTicketsToString() {
        return issuedLottoTickets.stream()
                .map(Lotto::toString)
                .toList();
    }

    private List<Lotto> issueLottoTickets() {
        return Stream.generate(this::issueLottoTicket)
                .limit(lottoCount)
                .toList();
    }

    private Lotto issueLottoTicket() {
        List<Integer> numbers = randomUtil.generateRandomNumbers(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE, 6);
        return Lotto.from(numbers);
    }
}
