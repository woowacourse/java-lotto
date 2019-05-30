package lotto;

import java.util.*;

public class LottoMachine {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MAX_NUM = 45;
    private static final int MIN_NUM = 1;

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
        return new Lotto(generateRandomLottoNumberIndexes());
    }

    private static List<Integer> generateRandomLottoNumberIndexes() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_NUMBER_COUNT) {
            numbers.add(generateRandomLottoNumberIndex());
        }
        return new ArrayList<>(numbers);
    }

    private static int generateRandomLottoNumberIndex() {
        return (int)(Math.random() * (MAX_NUM) + MIN_NUM);
    }

}
