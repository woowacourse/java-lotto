package domain.service;

import static java.util.stream.Collectors.toList;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import java.util.List;
import java.util.stream.IntStream;

@FunctionalInterface
public interface LottoService {
    int LOTTO_NUMBERS_SIZE = 6;
    int MINIMUM_LOTTO_NUMBER = 1;
    int MAXIMUM_LOTTO_NUMBER = 45;
    int MINIMUM_LOTTO_QUANTITY = 1;
    String ERROR_MESSAGE_FOR_INVALID_QUANTITY = "자동 로또 생성 수량은 1 이상으로 입력해주세요";
    List<Integer> lottoNumbers = IntStream
            .rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
            .boxed()
            .collect(toList());

    List<LottoNumber> getLottoNumbers();

    default Lottos createLottosByQuantity(int quantity) {
        validateQuantity(quantity);

        return new Lottos(getLottosByQuantity(quantity));
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_LOTTO_QUANTITY) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_QUANTITY);
        }
    }

    private List<Lotto> getLottosByQuantity(int quantity) {
        return IntStream.rangeClosed(1, quantity)
                .mapToObj(i -> new Lotto(getLottoNumbers()))
                .collect(toList());
    }
}
