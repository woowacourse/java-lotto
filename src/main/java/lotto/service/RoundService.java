package lotto.service;

import lotto.dao.RoundDAO;

import java.util.List;

public class RoundService {
    private final RoundDAO roundDAO = new RoundDAO();

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
