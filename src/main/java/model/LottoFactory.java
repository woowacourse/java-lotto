package model;

import static constant.LottoConstant.DAMAGE;
import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_PURCHASE_UNIT;
import static constant.LottoConstant.LOTTO_SEPARATOR;
import static constant.LottoConstant.LOTTO_TICKET_SIZE;
import static constant.LottoConstant.PROFIT;
import static model.Prize.initializeMap;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class LottoFactory {

    private final int lottoCount;
    private final List<Lotto> issuedLottoTickets;

    private final Random random = new Random();

    public static LottoFactory of(final int input) {
        return new LottoFactory(input / LOTTO_PURCHASE_UNIT);
    }

    private LottoFactory(final int lottoCount) {
        this.lottoCount = lottoCount;
        this.issuedLottoTickets = issueLottoTickets();
    }

    public EnumMap<Prize, Integer> getStatistic(Lotto lotto, Bonus bonus) {
        EnumMap<Prize, Integer> prizes = initializeMap();
        for (Lotto issuedTicket : issuedLottoTickets) {
            int matchCount = lotto.matchCount(issuedTicket);
            boolean matchesBonus = bonus.isContainedIn(lotto);
            Prize foundPrize = Prize.find(matchCount, matchesBonus);
            prizes.put(foundPrize, prizes.get(foundPrize) + 1);
        }
        return prizes;
    }

    public double getWinningAmount(EnumMap<Prize, Integer> prizes) {
        int principalMoney = lottoCount * LOTTO_PURCHASE_UNIT;
        int winningAmount = calculateWinningAmount(prizes);

        return (double) winningAmount / principalMoney;
    }

    public String lossOrGain(double winningAmount) {
        if (winningAmount >= 1) {
            return PROFIT;
        }
        return DAMAGE;
    }

    private List<Lotto> issueLottoTickets() {
        List<Lotto> issuedLottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            issuedLottoTickets.add(issueLottoTicket());
        }
        return issuedLottoTickets;
    }

    private Lotto issueLottoTicket() {
        HashSet<Integer> issuedTicketSet = new HashSet<>();
        while (issuedTicketSet.size() < LOTTO_TICKET_SIZE) {
            issuedTicketSet.add(getRandomNumber());
        }

        String issuedTicket = issuedTicketSet.stream()
                .map(String::valueOf)  // int → String 변환
                .collect(Collectors.joining(LOTTO_SEPARATOR));

        return Lotto.of(issuedTicket);
    }

    private int getRandomNumber() {
        return random.nextInt(LOTTO_NUMBER_MAX_RANGE) + 1;
    }

    private int calculateWinningAmount(EnumMap<Prize, Integer> prizes) {
        return prizes.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public List<Lotto> getIssuedLottoTickets() {
        return issuedLottoTickets;
    }
}
