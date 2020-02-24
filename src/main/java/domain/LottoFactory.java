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

    public static Lotto createOneManualLotto(final String[] manualLotto) {
            try {
                return new Lotto(Arrays.stream(manualLotto)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .mapToObj(AllLottoNumbers::get)
                        .collect(toList()));
            } catch (NumberFormatException e) {
                throw new NumberFormatException(String.format("로또 넘버는 숫자여야 합니다."));
            }
    }
}
