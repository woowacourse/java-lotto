package domain;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class LottoFactory {

    public static final int LOTTO_SIZE = 6;

    public static Lotto createOneLotto() {
        List<Integer> keys = AllLottoNumbers.getLottoNumbersKeySet();
        Collections.shuffle(keys);
        return new Lotto(keys.stream()
                        .limit(LOTTO_SIZE)
                        .map(AllLottoNumbers::get)
                        .collect(toList()));
    }

    public static Lotto createOneManualLotto(String[] manualLotto) {
        return new Lotto(Arrays.stream(manualLotto)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .mapToObj(AllLottoNumbers::get)
                    .collect(toList()));
    }
}
