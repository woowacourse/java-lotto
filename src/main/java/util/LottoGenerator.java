package util;

import static model.LottoConstants.*;

import java.util.ArrayList;
import java.util.List;
import model.Lotto;

public class LottoGenerator {

    public static List<Lotto> generate(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < lottoCount) {
            try {
                List<Integer> randomNumbers = NumberGenerator.pickUniqueRandomNumbers(MIN_NUMBER, MAX_NUMBER, NUMBER_COUNT);
                validateDuplication(randomNumbers, lottos);
                lottos.add(new Lotto(randomNumbers));
            }
            catch (IllegalStateException e) {
            }
        }
        return lottos;
    }

    private static void validateDuplication(List<Integer> randomNumbers, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            if (lotto.checkDuplication(randomNumbers)) {
                throw new IllegalStateException();
            }
        }
    }
}
