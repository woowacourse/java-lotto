package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AutoTicket {

    private static final int LOTTO_NUMBER_LENGTH = 6;
    private static final String THREE = "THREE";
    private static final String FOUR = "FOUR";
    private static final String FIVE_BONUS = "FIVE_BONUS";
    private static final String FIVE = "FIVE";
    private static final String SIX = "SIX";
    private List<Integer> autoNumber = new ArrayList<>();
    private String matchCountResult = "";

    public AutoTicket() {
        Collections.shuffle(LottoNumbers.getLottoNumbers());
        for (int i = 0; i < LOTTO_NUMBER_LENGTH; i++) {
            autoNumber.add(LottoNumbers.getLottoNumbers().get(i));
        }
        autoNumber.sort(Comparator.naturalOrder());
    }

    public AutoTicket(List<Integer> input) {
        this.autoNumber = input;
    }

    public List<Integer> getAutoTicket() {
        return autoNumber;
    }

    public void setMatchCountResult(WinNumbers winNumbers, BonusBallNo bonusBallNo) {
        int count = (int) autoNumber.stream().filter(winNumbers::isContainNumber).count();
        isCountThree(count);
        isCountFour(count);
        isCountFive(winNumbers, bonusBallNo, count);
        isCountSix(count);
    }

    private void isCountThree(int count) {
        if (count == LottoResult.THREE.getCorrect()) {
            matchCountResult = THREE;
        }
    }

    private void isCountFour(int count) {
        if (count == LottoResult.FOUR.getCorrect()) {
            matchCountResult = FOUR;
        }
    }

    private void isCountFive(WinNumbers winNumbers, BonusBallNo bonusBallNo, int count) {
        if (count == LottoResult.FIVE.getCorrect()) {
            isCountFiveBonus(winNumbers, bonusBallNo);
        }
    }

    private void isCountFiveBonus(WinNumbers winNumbers, BonusBallNo bonusBallNo) {
        if (winNumbers.isContainNumber(bonusBallNo.getBonusBallNo())) {
            matchCountResult = FIVE_BONUS;
            return;
        }
        matchCountResult = FIVE;
    }

    private void isCountSix(int count) {
        if (count == LottoResult.SIX.getCorrect()) {
            matchCountResult = SIX;
        }
    }

    public void setLottoResultCount() {
        if (!matchCountResult.isEmpty()) {
            LottoResult.valueOf(matchCountResult).setCount();
        }
    }
}
