package domain;

import java.util.Collections;
import java.util.List;

public class LottoFactory {

    public static final int START_INDEX = 0;
    public static final int LAST_INDEX = 6;

    public static Lotto createOneLotto() {
        List<LottoNumber> allLottoNumbers = AllLottoNumbers.getAllLottoNumbers();
        Collections.shuffle(allLottoNumbers);
        return new Lotto(allLottoNumbers.subList(START_INDEX, LAST_INDEX));
    }
}
