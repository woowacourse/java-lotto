package domain;

import static domain.LottoConstants.*;

import java.util.ArrayList;
import java.util.List;
import util.NumberGenerator;

public class LottoMachine {

    public List<Lotto> generate(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < lottoCount) {
            List<Integer> randomNumbers = getUniqueRandomNumbers(lottos);
            lottos.add(new Lotto(randomNumbers));
        }
        return lottos;
    }

    private List<Integer> getUniqueRandomNumbers(List<Lotto> lottos) {
        List<Integer> randomNumbers;
        do {
            randomNumbers = NumberGenerator.pickUniqueRandomNumbers(MIN_NUMBER, MAX_NUMBER, NUMBER_COUNT);
        } while (isDuplicated(randomNumbers, lottos));
        return randomNumbers;
    }

    private boolean isDuplicated(List<Integer> randomNumbers, List<Lotto> lottos) {
        return lottos.stream()
                .anyMatch(lotto -> lotto.isSameWith(randomNumbers));
    }
}
