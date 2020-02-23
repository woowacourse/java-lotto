package domain;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class LottoFactory {

    public static final int LOTTO_SIZE = 6;

    public static Lotto createOneLotto() {
        List<Integer> keys = AllLottoNumbers.getLottoNumbersKeySet();
        Collections.shuffle(keys);
        List<LottoNumber> lotto = keys.stream()
                                    .limit(LOTTO_SIZE)
                                    .map(AllLottoNumbers::get)
                                    .collect(toList());
        return new Lotto(lotto);
    }
}
