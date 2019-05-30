package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    public static Lottos createRandomLottos(int numOfLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numOfLottos; i++) {
            List<Integer> numbers = possibleLottoNumbers();
            Collections.shuffle(numbers);
            numbers = numbers.subList(0, Lotto.getSizeOfLottoNumbers());
            Collections.sort(numbers);
            lottos.add(new Lotto(numbers));
        }
        return new Lottos(lottos);
    }

    private static List<Integer> possibleLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int j = LottoNumber.getMinLottoNumber(); j <= LottoNumber.getMaxLottoNumber(); j++) {
            numbers.add(j);
        }
        return numbers;
    }

    public static Lottos createCustomLottos(List<List<Integer>> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> integers : numbers) {
            lottos.add(new Lotto(integers));
        }
        return new Lottos(lottos);
    }
}
