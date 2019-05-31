package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoMachine {

    public static Lottos generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return new Lottos(lottos);
    }

    public static Lottos generateLottos(List<Lotto> manualLottos) {
        return new Lottos(manualLottos);
    }

    private static Lotto generateLotto() {
        return new Lotto(generateRandomLottoNumbers());
    }

    private static List<Integer> generateRandomLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < Lotto.NUMBER_LENGTH) {
            numbers.add(generateRandomLottoNumber());
        }
        return new ArrayList<>(numbers);
    }

    private static int generateRandomLottoNumber() {
        return (int) (Math.random()
                * (LottoNumber.MAXIMUM_NUMBER - LottoNumber.MINIMUM_NUMBER + 1)
                + LottoNumber.MINIMUM_NUMBER);
    }

}
