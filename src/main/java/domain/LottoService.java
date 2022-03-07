package domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public interface LottoService {
    int LOTTO_NUMBERS_SIZE = 6;
    int MINIMUM_LOTTO_NUMBER = 1;
    int MAXIMUM_LOTTO_NUMBER = 45;
    int MINIMUM_LOTTO_QUANTITY = 1;
    String ERROR_MESSAGE_FOR_INVALID_QUANTITY = "로또 생성 수량은 " + MINIMUM_LOTTO_QUANTITY + "이상으로 입력해주세요";

    List<Integer> lottoNumbers = IntStream
            .rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
            .boxed()
            .collect(toList());

    static Lottos createAuto(int quantity) {
        validateQuantity(quantity);

        return new Lottos(getAutoLottos(quantity));
    }

    private static void validateQuantity(int quantity) {
        if (quantity < MINIMUM_LOTTO_QUANTITY) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_QUANTITY);
        }
    }

    private static List<Lotto> getAutoLottos(int quantity) {
        return IntStream.rangeClosed(1, quantity)
                .mapToObj(i -> new Lotto(getAutoLottoNumbers()))
                .collect(toList());
    }

    private static List<LottoNumber> getAutoLottoNumbers() {
        List<Integer> lottoNumbersForNewLotto = new ArrayList<>(lottoNumbers);
        Collections.shuffle(lottoNumbersForNewLotto);

        return lottoNumbersForNewLotto.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .sorted()
                .map(LottoNumber::getInstance)
                .collect(toList());
    }

    static Lottos createManual(List<List<Integer>> lottoNumbers) {
        validateQuantity(lottoNumbers.size());

        return new Lottos(getManualLottos(lottoNumbers));
    }

    private static List<Lotto> getManualLottos(List<List<Integer>> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoService::getManualLottoNumbers)
                .map(Lotto::new)
                .collect(toList());
    }

    private static List<LottoNumber> getManualLottoNumbers(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getInstance)
                .collect(toList());
    }
}
