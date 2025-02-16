package model;

import static constant.LottoConstant.DAMAGE;
import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_PURCHASE_UNIT;
import static constant.LottoConstant.LOTTO_SEPARATOR;
import static constant.LottoConstant.LOTTO_TICKET_SIZE;
import static constant.LottoConstant.PROFIT;
import static model.Prize.initialize;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoFactory {

    private static final Random random = new Random();

    private final int lottoCount;
    private final List<Lotto> issuedLottoTickets;

    public static LottoFactory of(final LottoPurchase lottoPurchase) {
        return new LottoFactory(lottoPurchase.calculateLottoCount());
    }

    private LottoFactory(final int lottoCount) {
        this.lottoCount = lottoCount;
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
                .collect(Collectors.toList());
    }

    private List<Lotto> issueLottoTickets() {
        return Stream.generate(this::issueLottoTicket)
                .limit(lottoCount)
                .toList();
    }

    private Lotto issueLottoTicket() {
        HashSet<Integer> issuedTicketNumbers = new HashSet<>();
        while (issuedTicketNumbers.size() < LOTTO_TICKET_SIZE) {
            issuedTicketNumbers.add(getRandomNumber());
        }

        return Lotto.of(issuedTicketNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_SEPARATOR)));
    }

    private int getRandomNumber() {
        return random.nextInt(LOTTO_NUMBER_MAX_RANGE) + 1;
    }
}
