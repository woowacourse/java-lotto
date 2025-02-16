package model.lottoNumber;

import static model.LottoConstants.*;

import java.util.ArrayList;
import java.util.List;
import model.purchase.Lotto;

public class LottoGenerator {

    private static final NumberPickStrategy numberPickStrategy = new RandomNumberPickStrategy();

    public static List<Lotto> generate(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < lottoCount) {
            createNotDuplicatedLotto(lottos);
        }
        return lottos;
    }

    private static void createNotDuplicatedLotto(List<Lotto> lottos) {
        try {
            List<Integer> randomNumbers = NumberGenerator.pickUniqueNumbersInRange(
                    MIN_NUMBER, MAX_NUMBER, NUMBER_COUNT, numberPickStrategy);
            validateDuplication(randomNumbers, lottos);
            lottos.add(new Lotto(randomNumbers));
        } catch (IllegalStateException e) {
        }
    }

    private static void validateDuplication(List<Integer> randomNumbers, List<Lotto> lottos) {
        lottos.stream().forEach(lotto -> {
            if (lotto.hasDuplicationWith(randomNumbers)) {
                throw new IllegalStateException();
            }
        });
    }
}
