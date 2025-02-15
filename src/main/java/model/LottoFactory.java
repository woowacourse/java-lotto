package model;

import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_PURCHASE_UNIT;
import static constant.LottoConstant.LOTTO_TICKET_SIZE;
import static model.Prize.initializeMap;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


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
            int matchCount = compareLottoNumbers(lotto, issuedTicket);
            boolean matchesBonus = compareBonusNumber(bonus.getNumber(), issuedTicket.getNumbers());
            Prize foundPrize = Prize.find(matchCount, matchesBonus);
            prizes.put(foundPrize, prizes.get(foundPrize) + 1);
        }
        return prizes;
    }

    public double getProfit(EnumMap<Prize, Integer> enumMap) {
        int principalMoney = lottoCount * LOTTO_PURCHASE_UNIT;
        int profit = calculateProfit(enumMap);

        return (double) profit / principalMoney;
    }

    private List<Lotto> issueLottoTickets() {
        List<Lotto> issuedLottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            issuedLottoTickets.add(issueLottoTicket());
        }
        return issuedLottoTickets;
    }

    private Lotto issueLottoTicket() {
        HashSet<Integer> issuedTicket = new HashSet<>();
        while (issuedTicket.size() < LOTTO_TICKET_SIZE) {
            issuedTicket.add(getRandomNumber());
        }
        return new Lotto(new ArrayList<>(issuedTicket));
    }

    private int getRandomNumber() {
        return random.nextInt(LOTTO_NUMBER_MAX_RANGE) + 1;
    }

    private int compareLottoNumbers(Lotto lotto, Lotto issuedTicket) {
        return (int) issuedTicket.getNumbers().stream()
                .filter(issuedNumber -> lotto.getNumbers().contains(issuedNumber))
                .count();
    }

    private boolean compareBonusNumber(int bonusNumber, List<Integer> issuedTicketNumbers) {
        return issuedTicketNumbers.contains(bonusNumber);
    }

    private int calculateProfit(EnumMap<Prize, Integer> prizes) {
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
