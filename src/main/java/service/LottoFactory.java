package service;

import static model.Prize.initializeMap;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.Bonus;
import model.Lotto;
import model.LottoConstant;
import model.Prize;

public class LottoFactory {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MAX_RANGE = 45;

    private final int ticketNumber;
    private final List<Lotto> issuedTickets;

    private final Random random;

    public static LottoFactory of(final int purchase) {
        return new LottoFactory(purchase / LottoConstant.TICKET_PRICE_UNIT);
    }

    private LottoFactory(final int ticketNumber) {
        this.ticketNumber = ticketNumber;
        this.random = new Random();
        this.issuedTickets = IntStream.range(0, ticketNumber)
                .mapToObj(i -> getIssueTicket())
                .collect(Collectors.toList());
    }

    private Lotto getIssueTicket() {
        HashSet<Integer> issuedTicket = new HashSet<>();
        while (issuedTicket.size() < LOTTO_SIZE) {
            issuedTicket.add(getRandomNumber());
        }
        return new Lotto(new ArrayList<>(issuedTicket));
    }

    public EnumMap<Prize, Integer> getStatistic(Lotto lotto, Bonus bonus) {
        EnumMap<Prize, Integer> prizeMap = initializeMap();
        for (Lotto issuedTicket : issuedTickets) {
            int matchCount = checkLottoNumber(lotto, issuedTicket);
            boolean matchesBonus = checkBonus(bonus.getNumber(), issuedTicket.getNumbers());
            Prize foundPrize = Prize.find(matchCount, matchesBonus);
            prizeMap.put(foundPrize, prizeMap.get(foundPrize) + 1);
        }
        prizeMap.remove(Prize.MATCH_NONE);
        return prizeMap;
    }

    private int checkLottoNumber(Lotto lotto, Lotto issuedTicket) {
        return (int) issuedTicket.getNumbers().stream()
                .filter(issuedNumber -> lotto.getNumbers().contains(issuedNumber))
                .count();
    }

    private boolean checkBonus(int bonusNumber, List<Integer> issuedTicketNumbers) {
        return issuedTicketNumbers.contains(bonusNumber);
    }

    public int calculateBenefit(EnumMap<Prize, Integer> enumMap) {
        int benefit = 0;
        for (Prize prize : enumMap.keySet()) {
            benefit += enumMap.get(prize) * prize.getPrizeAmount();
        }
        return benefit;
    }

    private int getRandomNumber() {
        return random.nextInt(LOTTO_MAX_RANGE) + 1;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public List<Lotto> getIssuedTickets() {
        return issuedTickets;
    }
}
