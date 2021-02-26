package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoAnnouncementException;

public class Lotto {

    public static final String DIFFERENT_POSSESSION_MESSAGE = "로또 번호의 갯수가 기준과 다릅니다.";
    public static final String OVERLAPPED_WINNER_MESSAGE = "당첨 번호가 중복되었습니다.";
    public static final double BONUS_MATCHING_COUNT = 5.5;
    public static final int LOTTO_POSSESSION_NUMBER = 6;

    private final List<Number> numbers;

    public Lotto(List<Integer> selectedNumber) {
        checkValidNumbers(selectedNumber);
        numbers = new ArrayList<>();
        for (int integerNumber : selectedNumber) {
            numbers.add(Number.from(integerNumber));
        }
    }

    public LottoRank getLottoRank(LottoAnnouncement lottoAnnouncement) {
        double count = getMatchingCount(lottoAnnouncement);
        return LottoRank.getRank(count);
    }

    private double getMatchingCount(LottoAnnouncement lottoAnnouncement) {
        List <Number> winningNumbers = lottoAnnouncement.getWinners();
        Number bonusNumber = lottoAnnouncement.getBonusNumber();
        List <Number> combinedWinningNumbers = getCombinedWinNumbers(winningNumbers, bonusNumber);
        int count = getCount(combinedWinningNumbers);

        if (isSecondRank(count, bonusNumber)) {
            return BONUS_MATCHING_COUNT;
        }
        return count;
    }

    private List<Number> getCombinedWinNumbers(List<Number> winningNumbers,
        Number bonusNumber) {
        List<Number> integratedWinningNumbers = new ArrayList<>(winningNumbers);
        integratedWinningNumbers.add(bonusNumber);
        return integratedWinningNumbers;
    }

    private int getCount(List<Number> integratedWinningNumbers) {
        return (int) numbers.stream()
            .filter(integratedWinningNumbers::contains)
            .count();
    }

    private boolean isSecondRank(int count, Number bonusNumber) {
        return count == LOTTO_POSSESSION_NUMBER && numbers.contains(bonusNumber);
    }

    private void checkValidNumbers (List<Integer> winners) {
        checkOverlappedAmongWinners(winners);
        checkProperSize(winners);
    }

    private void checkOverlappedAmongWinners(List<Integer> winners) {
        Set<Integer> removedOverlappedWinners = new HashSet<>(winners);

        if (removedOverlappedWinners.size() != winners.size()) {
            throw new LottoAnnouncementException(OVERLAPPED_WINNER_MESSAGE);
        }
    }

    private void checkProperSize(List<Integer> winners) {
        if (winners.size() != Lotto.LOTTO_POSSESSION_NUMBER) {
            throw new LottoAnnouncementException(DIFFERENT_POSSESSION_MESSAGE);
        }
    }

    public List<Number> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}