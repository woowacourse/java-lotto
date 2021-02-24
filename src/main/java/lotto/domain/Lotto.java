package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoAnnouncementException;

public class Lotto {

    public static final String DIFFERENT_POSESSION_MESSAGE = "로또 번호의 갯수가 기준과 다릅니다.";
    public static final String OVERLAPPED_WINNER_MESSAGE = "당첨 번호가 중복되었습니다.";
    public static final String EXCESS_NUMBER_MESSAGE = "범위를 벗어난 숫자입니다.";
    public static final double BONUS_MATCHING_COUNT = 5.5;
    public static final int UPPER_LIMIT = 45;
    public static final int LOWER_LIMIT = 1;
    public static final int LOTTO_POSSESSION_NUMBER = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> selectedNumber) {
        checkValidNumbers(selectedNumber);
        numbers = new ArrayList<>(selectedNumber);
    }

    public LottoRank getLottoRank(LottoAnnouncement lottoAnnouncement) {
        double count = getMatchingCount(lottoAnnouncement);
        return LottoRank.getRank(count);
    }

    private double getMatchingCount(LottoAnnouncement lottoAnnouncement) {
        List <Integer> winningNumbers = lottoAnnouncement.getWinners();
        int bonusNumber = lottoAnnouncement.getBonusNumber();
        List<Integer> combinedWinningNumbers = getCombinedWinNumbers(winningNumbers, bonusNumber);
        int count = getCount(combinedWinningNumbers);

        if (isSecondRank(count, bonusNumber)) {
            return BONUS_MATCHING_COUNT;
        }
        return count;
    }

    private List<Integer> getCombinedWinNumbers(List<Integer> winningNumbers,
        int bonusNumber) {
        List<Integer> integratedWinningNumbers = new ArrayList<>(winningNumbers);
        integratedWinningNumbers.add(bonusNumber);
        return integratedWinningNumbers;
    }

    private int getCount(List<Integer> integratedWinningNumbers) {
        return (int) numbers.stream()
            .filter(integratedWinningNumbers::contains)
            .count();
    }

    private boolean isSecondRank(int count, int bonusNumber) {
        return count == LOTTO_POSSESSION_NUMBER && numbers.contains(bonusNumber);
    }

    private void checkValidNumbers (List<Integer> winners) {
        checkOverlappedAmongWinners(winners);
        checkProperSize(winners);
        winners.forEach(this::checkValidNumber);
    }

    private void checkOverlappedAmongWinners(List<Integer> winners) {
        Set<Integer> removedOverlappedWinners = new HashSet<>(winners);

        if (removedOverlappedWinners.size() != winners.size()) {
            throw new LottoAnnouncementException(OVERLAPPED_WINNER_MESSAGE);
        }
    }

    private void checkProperSize(List<Integer> winners) {
        if (winners.size() != Lotto.LOTTO_POSSESSION_NUMBER) {
            throw new LottoAnnouncementException(DIFFERENT_POSESSION_MESSAGE);
        }
    }

    private void checkValidNumber(int targetNumber) {
        boolean criteria = (targetNumber < LOWER_LIMIT) | (targetNumber > UPPER_LIMIT);

        if (criteria) {
            throw new LottoAnnouncementException(EXCESS_NUMBER_MESSAGE);
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}