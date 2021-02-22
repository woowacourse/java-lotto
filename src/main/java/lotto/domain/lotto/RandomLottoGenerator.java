package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.domain.lotto.LottoNumber.LOTTO_FINAL_NUMBER;
import static lotto.domain.lotto.LottoNumber.LOTTO_START_NUMBER;

public class RandomLottoGenerator {
    private static final ArrayList<LottoNumber> totalLottoNumberList = new ArrayList<>();

    public RandomLottoGenerator() {
        for (int i = LOTTO_START_NUMBER; i <= LOTTO_FINAL_NUMBER; i++) {
            totalLottoNumberList.add(new LottoNumber(i));
        }
    }

    public List<LottoNumber> createLottoLine() {
        Collections.shuffle(totalLottoNumberList);
        List<LottoNumber> picked = totalLottoNumberList.subList(0, 6);
        Collections.sort(picked);
        return picked;
    }
}
