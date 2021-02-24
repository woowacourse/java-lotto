package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoAnnouncementException;

public class LottoAnnouncement {

    private static final int UPPER_LIMIT = 45;
    private static final int LOWER_LIMIT = 1;

    private final List<Integer> winners;
    private final int bonusNumber;

    public LottoAnnouncement(List<Integer> winners, int bonusNumber) {
        winners.forEach(this::checkValidNumber);
        checkProperSize(winners);
        checkValidNumber(bonusNumber);
        checkOverlapped(winners, bonusNumber);
        this.winners = winners;
        this.bonusNumber = bonusNumber;
    }

    private void checkOverlapped(List<Integer> winners, int bonusNumber) {
        checkOverlappedAmongWinners(winners);
        checkOverlappedWinnersToBonus(winners, bonusNumber);
    }

    private void checkOverlappedAmongWinners(List<Integer> winners) {
        Set<Integer> removedOverlappedWinners = new HashSet<>(winners);

        if (removedOverlappedWinners.size() != winners.size()) {
            throw new LottoAnnouncementException("당첨 번호가 중복되었습니다.");
        }
    }

    private void checkOverlappedWinnersToBonus(List<Integer> winners, int bonusNumber) {
        long overlappedCount = winners.stream()
            .filter(element -> element == bonusNumber)
            .count();

        if (overlappedCount != 0) {
            throw new LottoAnnouncementException("보너스 번호가 중복되었습니다.");
        }
    }

    private void checkProperSize(List<Integer> winners) {
        if (winners.size() != Lotto.LOTTO_POSSESSION_NUMBER) {
            throw new LottoAnnouncementException("로또 번호의 갯수가 기준과 다릅니다.");
        }
    }

    private void checkValidNumber(int targetNumber) {
        boolean criteria = (targetNumber < LOWER_LIMIT) | (targetNumber > UPPER_LIMIT);

        if (criteria) {
            throw new LottoAnnouncementException("범위를 벗어난 숫자입니다.");
        }
    }

    public List<Integer> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
