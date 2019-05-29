package lottogame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private List<LottoNumber> lotto = new ArrayList<>();

    Lotto() {
        while (lotto.size() < LOTTO_SIZE) {
            addRandomLottoNumber();
        }
        Collections.sort(lotto);
    }

    private void addRandomLottoNumber() {
        LottoNumber randomLottoNumber = LottoNumber.getRandomLottoNumber();
        if (!lotto.contains(randomLottoNumber)) {
            lotto.add(randomLottoNumber);
        }
    }

    public int size() {
        return lotto.size();
    }

    public Rank getMatchResult(WinningLotto winningLotto) {
        int numberOfMatch = (int) lotto.stream()
                .filter(winningLotto::isContain)
                .count();
        boolean bonusBallMatch = lotto.stream()
                .anyMatch(winningLotto::isBonusBallMatch);
        return Rank.valueOf(numberOfMatch, bonusBallMatch);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (LottoNumber lottoNumber : lotto) {
            stringBuilder.append(lottoNumber).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
