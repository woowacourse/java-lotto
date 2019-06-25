package lotto.service;

import lotto.dao.RoundDAO;

import java.util.List;

public class RoundService {
    private final RoundDAO roundDAO = RoundDAO.getInstance();

    private RoundService() {}

    private static class RoundServiceHolder {
        static final RoundService ROUND_SERVICE = new RoundService();
    }

    public static RoundService getInstance() {
        return RoundServiceHolder.ROUND_SERVICE;
    }

    public void addRound(int amount) {
        roundDAO.addRound(amount);
    }

    public int findMaxRound() {
        return roundDAO.findMaxRound();
    }

    public List<Integer> findAllRounds() {
        return roundDAO.findAllRounds();
    }
}
