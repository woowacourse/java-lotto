package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static List<Number> lottoNumbers = generateLottoNumber();

    public static LottoContainer generateLottoContainer(Price price) {
        List<Lotto> lottoContainer = new ArrayList<>();
        for (int i = 0; i < price.getCountOfLotto(); i++) {
            lottoContainer.add(generateLotto());
        }
        return new LottoContainer(lottoContainer);
    }

    private static Lotto generateLotto() {
        Collections.shuffle(lottoNumbers);
        List<Number> numbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            numbers.add(lottoNumbers.get(i));
        }
        return new Lotto(numbers);
    }

    private static List<Number> generateLottoNumber() {
        List<Number> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(Number.of(i));
        }
        return numbers;
    }


}
