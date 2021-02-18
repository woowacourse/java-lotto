package lotto.domain.lotto;

import static lotto.utils.Config.END;
import static lotto.utils.Config.START;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator {

    private static final ArrayList<LottoNumber> totalLottoNumberList = new ArrayList<>();

    public RandomLottoGenerator() {
        for (int i = START; i <= END; i++) {
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
