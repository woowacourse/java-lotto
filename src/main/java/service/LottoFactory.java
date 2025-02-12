package service;

import static model.Prize.prizeIntegerEnumMap;

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

public class LottoFactory {

    private Integer ticketNumber;
    private List<Lotto> issuedTickets;

    Random random = new Random();

    public static LottoFactory of(final Integer purchase) {
        return new LottoFactory(purchase / 1000);
    }

    private LottoFactory(final Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
        List<Lotto> issuedTickets = IntStream.range(0, ticketNumber).mapToObj(i -> getIssueTicket())
                .collect(Collectors.toList());
        this.issuedTickets = issuedTickets;
    }

    private Lotto getIssueTicket() {
        HashSet<Integer> issuedTicket = new HashSet<>();
        while (issuedTicket.size() < 6) {
            issuedTicket.add(getRandomNumber());
        }
        return new Lotto(new ArrayList<>(issuedTicket));
    }

    private Integer getRandomNumber() {
        return random.nextInt(45) + 1;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public List<Lotto> getIssuedTickets() {
        return issuedTickets;
    }

    public EnumMap<Prize, Integer> getStatistic(Lotto lotto, Bonus bonus) {
        EnumMap<Prize, Integer> prizeMap = prizeIntegerEnumMap();
        for (int i = 0; i < issuedTickets.size(); i++) {
            int matchCount = 0;
            boolean matchesBonusNumber = false;
            Lotto issuedTicket = issuedTickets.get(i);
            if (issuedTicket.getNumbers().contains(bonus.getNumber())) {
                matchesBonusNumber = true;
            }
            for (int j = 0; j < issuedTicket.getNumbers().size(); j++) {
                if (lotto.getNumbers().contains(issuedTicket.getNumbers().get(j))) {
                    matchCount++;
                }
            }

            Prize foundPrize = Prize.find(matchCount, matchesBonusNumber);
            prizeMap.put(foundPrize, prizeMap.get(foundPrize) + 1);
        }

        prizeMap.remove(Prize.match_none);
        return prizeMap;
    }
}
