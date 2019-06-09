package lotto.domain;

import java.util.*;
import java.util.stream.IntStream;

public class AutoLottoGenerator {

    private static final int FIRST_LOTTO_INDEX = 0;
    private static final int LAST_LOTTO_INDEX = 5;
    private static final int ONE = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final List<LottoNumber> allLottoNumbers = new ArrayList<>();

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(number->allLottoNumbers.add(new LottoNumber(number)));
    }

    public static Lotto generateAutoLotto() {
        Collections.shuffle(allLottoNumbers);
        Set<LottoNumber> generatedLotto = new TreeSet<>(allLottoNumbers.subList(FIRST_LOTTO_INDEX, LAST_LOTTO_INDEX + ONE));
        return new Lotto(generatedLotto);
    }

    public static UserLottos generateAutoLottos(int autoLottoSize) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < autoLottoSize; i++) {
            autoLottos.add(generateAutoLotto());
        }
        return new UserLottos(autoLottos);
    }
}
