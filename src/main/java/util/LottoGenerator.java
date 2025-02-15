package util;

import static model.LottoConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Lotto;

public class LottoGenerator {

    public static List<Lotto> generate(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < lottoCount) {
            addNotDuplicatedLotto(lottos);
        }
        return lottos;
    }

    private static void addNotDuplicatedLotto(List<Lotto> lottos) {
        try {
            List<Integer> randomNumbers = NumberGenerator.pickUniqueNumbersInRange(
                    MIN_NUMBER, MAX_NUMBER, NUMBER_COUNT, new NumberPickStrategy() {
                        private final Random random = new Random();

                        @Override
                        public int pickInRange(int min, int max) {
                            return random.nextInt(max) + min;
                        }
                    });
            validateDuplication(randomNumbers, lottos);
            lottos.add(new Lotto(randomNumbers));
        } catch (IllegalStateException e) {
        }
    }

    private static void validateDuplication(List<Integer> randomNumbers, List<Lotto> lottos) {
        lottos.stream().forEach(lotto -> {
            if (lotto.checkDuplication(randomNumbers)) {
                throw new IllegalStateException();
            }
        });
    }
}
