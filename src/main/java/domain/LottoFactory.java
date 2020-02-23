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

    public static Lotto createOneManualLotto(String[] manualLotto) {
        return new Lotto(Arrays.stream(manualLotto)
                    .map(value -> AllLottoNumbers.get(Integer.parseInt(value)))
                    .collect(toList()));
    }
}
