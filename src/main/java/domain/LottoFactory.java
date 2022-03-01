package domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static final List<Integer> lottoNumbers;
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    static {
        lottoNumbers = new ArrayList<>();
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    public static Lottos createAutoLottosByQuantity(int quantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lottos.add(createSingleAutoLotto());
        }

        return new Lottos(lottos);
    }

    private static Lotto createSingleAutoLotto() {
        return new Lotto(getLottoNumbers());
    }

    private static List<LottoNumber> getLottoNumbers() {
        Collections.shuffle(lottoNumbers);

        return lottoNumbers.stream()
                .limit(6)
                .sorted()
                .map(LottoNumberRepository::getLottoNumberByInt)
                .collect(toList());
    }
}
