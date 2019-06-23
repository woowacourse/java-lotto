package lotto.persistence;

import java.util.List;

public interface RoundDAO {
    static RoundDAO getInstance() {
        return RoundDAOImpl.getInstance();
    }

    void addRound(int prize, double interestRate);

    int getPrizeOfId(int id);

    double getInterestRateOfId(int id);

    int getLatestRoundId();

    List<Integer> getAllIds();

    void removeRoundById(int roundId);
}
