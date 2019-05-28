package lottogame.domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private List<LottoNumber> lotto = new ArrayList<>();

    Lotto() {
        while (lotto.size() < LOTTO_SIZE) {
            addRandomLottoNumber();
        }
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
