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
import model.Prize;

public class LottoService {

    private static final Integer LOTTO_PURCHASE_UNIT = 1_000;
    private static final Integer LOTTO_SIZE = 6;
    private static final Integer LOTTO_MAX_RANGE = 45;

    private final Integer ticketNumber;
    private final List<Lotto> issuedTickets;

    private final Random random;

    public static LottoService of(final Integer purchase) {
        return new LottoService(purchase / LOTTO_PURCHASE_UNIT);
    }

    private LottoService(final Integer ticketNumber) {
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
        prizeMap.remove(Prize.LAST_PLACE);
        return prizeMap;
    }

    private int checkLottoNumber(Lotto lotto, Lotto issuedTicket) {
        return (int) issuedTicket.getNumbers().stream()
                .filter(issuedNumber -> lotto.getNumbers().contains(issuedNumber))
                .count();
    }

    private boolean checkBonus(Integer bonusNumber, List<Integer> issuedTicketNumbers) {
        return issuedTicketNumbers.contains(bonusNumber);
    }

    public double getBenefit(EnumMap<Prize, Integer> enumMap) {
        Integer principalMoney = ticketNumber * LOTTO_PURCHASE_UNIT;
        Integer benefit = calculateBenefit(enumMap);

        return (double) benefit / principalMoney;
    }

    private Integer calculateBenefit(EnumMap<Prize, Integer> enumMap) {
        Integer benefit = 0;
        for (Prize prize : enumMap.keySet()) {
            benefit += enumMap.get(prize) * prize.getPrizeMoney();
        }
        return benefit;
    }

    private Integer getRandomNumber() {
        return random.nextInt(LOTTO_MAX_RANGE) + 1;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public List<Lotto> getIssuedTickets() {
        return issuedTickets;
    }
}
