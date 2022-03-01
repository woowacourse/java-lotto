package domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static final List<Integer> lottoNumbers;
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final String ERROR_MESSAGE_FOR_INVALID_QUANTITY = "자동 로또 생성 수량은 1 이상으로 입력해주세요";
    private static final int MINIMUM_AUTO_LOTTO_QUANTITY = 1;

    static {
        lottoNumbers = new ArrayList<>();
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    public static Lottos createAutoLottosByQuantity(int quantity) {
        validateQuantity(quantity);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lottos.add(createSingleAutoLotto());
        }

        return new Lottos(lottos);
    }

    private static void validateQuantity(int quantity) {
        if (quantity < MINIMUM_AUTO_LOTTO_QUANTITY) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_QUANTITY);
        }
    }

    private static Lotto createSingleAutoLotto() {
        return new Lotto(getLottoNumbers());
    }

    private static List<LottoNumber> getLottoNumbers() {
        List<Integer> lottoNumbersForNewLotto = new ArrayList<>(lottoNumbers);
        Collections.shuffle(lottoNumbersForNewLotto);

        return lottoNumbersForNewLotto.stream()
                .limit(6)
                .sorted()
                .map(LottoNumberRepository::getLottoNumberByInt)
                .collect(toList());
    }
}
