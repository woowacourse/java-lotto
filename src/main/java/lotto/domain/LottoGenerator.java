package lotto.domain;

import java.util.*;

public class LottoGenerator {
    public static Lottos createRandomLottos(int numOfLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numOfLottos; i++) {
            lottos.add(Lotto.create(new RandomLottoNumberGenerator()));
        }
        return new Lottos(lottos);
    }

    public static Lottos createCustomLottos(List<List<Integer>> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> integers : numbers) {
            lottos.add(Lotto.create(new CustomLottoNumberGenerator(integers)));
        }
        return new Lottos(lottos);
    }
}
