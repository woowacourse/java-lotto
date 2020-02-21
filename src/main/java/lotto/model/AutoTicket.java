package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AutoTicket {

    private static final int LOTTO_NUMBER_LENGTH = 6;
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
        if (count == LottoResult.THREE.getCorrect()) {
            matchCountResult = "THREE";
        }
        if (count == LottoResult.FOUR.getCorrect()) {
            matchCountResult = "FOUR";
        }
        if (count == LottoResult.FIVE.getCorrect()) {
            if (winNumbers.isContainNumber(bonusBallNo.getBonusBallNo())) {
                matchCountResult = "FIVE_BONUS";
            } else {
                matchCountResult = "FIVE";
            }
        }
        if (count == LottoResult.SIX.getCorrect()) {
            matchCountResult = "SIX";
        }
    }

    public void setLottoResultCount() {
        if (!matchCountResult.isEmpty()) {
            LottoResult.valueOf(matchCountResult).setCount();
        }
    }
}
