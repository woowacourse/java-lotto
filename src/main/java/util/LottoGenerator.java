package util;

import static model.LottoConstants.*;

import java.util.ArrayList;
import java.util.List;
import model.Lotto;

public class LottoGenerator {

    public static List<Lotto> generate(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        while (true) {
            try {
                List<Integer> randomNumbers = NumberGenerator.pickUniqueRandomNumbers(MIN_NUMBER, MAX_NUMBER, NUMBER_COUNT);
                validateDuplication(randomNumbers, lottos);
                lottos.add(new Lotto(randomNumbers));
                if (lottos.size() == lottoCount) {
                    return lottos;
                }
            }
            catch (IllegalStateException e) {
            }
        }
    }

    private static void validateDuplication(List<Integer> randomNumbers, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            if (lotto.checkDuplication(randomNumbers)) {
                throw new IllegalStateException();
            }
        }
    }
}
