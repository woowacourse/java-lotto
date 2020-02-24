package lotto.controller;

import java.util.HashMap;
import java.util.Map;
import lotto.model.*;

public class LottoManager {

    private static final String FIFTH_PRIZE_NAME = "THREE";
    private static final String FOURTH_PRIZE_NAME = "FOUR";
    private static final String THIRD_PRIZE_NAME = "FIVE";
    private static final String SECOND_PRIZE_NAME = "FIVE_BONUS";
    private static final String FIRST_PRIZE_NAME = "SIX";
    public static final Map<String, Integer> lottoResultMap = new HashMap<>();

    static {
        lottoResultMap.put(FIFTH_PRIZE_NAME, 0);
        lottoResultMap.put(FOURTH_PRIZE_NAME, 0);
        lottoResultMap.put(THIRD_PRIZE_NAME, 0);
        lottoResultMap.put(SECOND_PRIZE_NAME, 0);
        lottoResultMap.put(FIRST_PRIZE_NAME, 0);
    }

    public static void lotto(Tickets tickets, WinNumbers winNumbers) {
        for (Ticket ticket : tickets.getTickets()) {
            int count = winNumbers.matchCount(ticket);
            checkMinimumCount(winNumbers, ticket, count);
        }
    }

    private static void checkMinimumCount(WinNumbers winNumbers, Ticket ticket, int count) {
        if (count >= 3) {
            String key = createKey(count, ticket.contains(winNumbers.getBonusBallNumber()));
            lottoResultMap.put(key, lottoResultMap.get(key) + 1);
        }
    }

    private static String createKey(int count, boolean hasBonusBallNumber) {
        if (count == 3) {
            return FIFTH_PRIZE_NAME;
        }
        if (count == 4) {
            return FOURTH_PRIZE_NAME;
        }
        if (count == 5) {
            return checkHasBonusBallNumber(hasBonusBallNumber);
        }
        return FIRST_PRIZE_NAME;
    }

    private static String checkHasBonusBallNumber(boolean hasBonusBallNumber) {
        if (hasBonusBallNumber) {
            return SECOND_PRIZE_NAME;
        }
        return THIRD_PRIZE_NAME;
    }
}