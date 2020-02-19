package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {

    static List<Lotto> generate(int lottosSize) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottosSize; i++) {
            Lotto lotto = new Lotto();
            lottos.add(lotto);
        }

        return lottos;
    }
}
