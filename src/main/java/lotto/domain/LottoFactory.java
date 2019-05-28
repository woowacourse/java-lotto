package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static List<Integer> lottoNumbers = generateLottoNumber();

    public static LottoContainer generateLottoContainer(List<Lotto> selfLotto,Price price) {
        List<Lotto> lottoContainer = new ArrayList<>();
        for(int i = 0; i < selfLotto.size(); i++){
            lottoContainer.add(selfLotto.get(i));
        }
        for (int i = selfLotto.size(); i < price.getCountOfLotto(); i++) {
            lottoContainer.add(generateLotto());
        }
        return new LottoContainer(lottoContainer);
    }

    private static Lotto generateLotto() {
        Collections.shuffle(lottoNumbers);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            numbers.add(lottoNumbers.get(i));
        }
        return new Lotto(numbers);
    }

    private static List<Integer> generateLottoNumber() {
        IntStream stream = IntStream.range(1,45);
        return stream.boxed().collect(Collectors.toList());
    }

}
