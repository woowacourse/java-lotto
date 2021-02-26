package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoAnnouncementException;

public class LottoAnnouncement {

    public static final String OVERLAPPED_WINNER_MESSAGE = "당첨 번호가 중복되었습니다.";
    public static final String OVERLAPPED_BONUS_MESSAGE = "보너스 번호가 중복되었습니다.";

    private final List<Number> winners;
    private final Number bonusNumber;

    public LottoAnnouncement(List<Integer> rawWinners, int bonusNumber) {
        checkProperSize(rawWinners);
        checkOverlapped(rawWinners, bonusNumber);
        this.winners = wrappedWinners(rawWinners);
        this.bonusNumber = Number.from(bonusNumber);
    }

    private List<Number> wrappedWinners(List<Integer> rawWinners) {
        List<Number> winners = new ArrayList<>();
        for (int rawWinner : rawWinners) {
            winners.add(Number.from(rawWinner));
        }
        return winners;
    }

    private void checkOverlapped(List<Integer> winners, int bonusNumber) {
        checkOverlappedAmongWinners(winners);
        checkOverlappedWinnersToBonus(winners, bonusNumber);
    }

    private void checkOverlappedAmongWinners(List<Integer> winners) {
        Set<Integer> removedOverlappedWinners = new HashSet<>(winners);

        if (removedOverlappedWinners.size() != winners.size()) {
            throw new LottoAnnouncementException(OVERLAPPED_WINNER_MESSAGE);
        }
    }

    private void checkOverlappedWinnersToBonus(List<Integer> winners, int bonusNumber) {
        long overlappedCount = winners.stream()
            .filter(element -> element == bonusNumber)
            .count();

        if (overlappedCount != 0) {
            throw new LottoAnnouncementException(OVERLAPPED_BONUS_MESSAGE);
        }
    }

    private void checkProperSize(List<Integer> winners) {
        if (winners.size() != Lotto.LOTTO_POSSESSION_NUMBER) {
            throw new LottoAnnouncementException(Lotto.DIFFERENT_POSSESSION_MESSAGE);
        }
    }

    public List<Number> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    public Number getBonusNumber() {
        return bonusNumber;
    }
}
