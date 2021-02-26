package lotto.domain;

import java.util.List;
import lotto.exception.LottoAnnouncementException;

public class LottoAnnouncement {

    public static final String OVERLAPPED_WINNER_MESSAGE = "당첨 번호가 중복되었습니다.";
    public static final String OVERLAPPED_BONUS_MESSAGE = "보너스 번호가 중복되었습니다.";

    private final Lotto winners;
    private final Number bonusNumber;

    public LottoAnnouncement(List<Integer> rawWinners, int bonusNumber) {
        checkOverlappedWinnersToBonus(rawWinners, bonusNumber);
        this.winners = new Lotto(rawWinners);
        this.bonusNumber = Number.from(bonusNumber);
    }

    private void checkOverlappedWinnersToBonus(List<Integer> winners, int bonusNumber) {
        long overlappedCount = winners.stream()
            .filter(element -> element == bonusNumber)
            .count();

        if (overlappedCount != 0) {
            throw new LottoAnnouncementException(OVERLAPPED_BONUS_MESSAGE);
        }
    }

    public Lotto getWinners() {
        return this.winners;
    }

    public Number getBonusNumber() {
        return bonusNumber;
    }
}
