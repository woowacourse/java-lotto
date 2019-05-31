package lotto.domain.generate;

import lotto.domain.Lotto;
import lotto.utils.Converter;

import java.util.ArrayList;
import java.util.List;

public class SelfLottoFactory {

    static List<Lotto> generateSelfLottos(List<String> inputs) {
        List<Lotto> lottos = new ArrayList<>();
        for (String input : inputs) {
            lottos.add(generateSelfLotto(input));
        }
        return lottos;
    }

    private static Lotto generateSelfLotto(String input) {
        return new Lotto(Converter.convertNumbers(input));
    }
}
