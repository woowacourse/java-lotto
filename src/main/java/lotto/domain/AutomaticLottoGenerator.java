package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.Lotto.LOTTO_START_INDEX;
import static lotto.domain.LottoNumber.MAXIMUM_CANDIDATE_NUMBER;
import static lotto.domain.LottoNumber.MINIMUM_CANDIDATE_NUMBER;

public class AutomaticLottoGenerator implements LottoGenerator {
    private static final List<LottoNumber> candidateNumbers = new ArrayList<>();

    static {
        for (int i = MINIMUM_CANDIDATE_NUMBER; i <= MAXIMUM_CANDIDATE_NUMBER; i++) {
            candidateNumbers.add(new LottoNumber(i));
        }
    }

    public Lottos createLottos(int numberOfLottoToCreate) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLottoToCreate; i++) {
            lottos.add(createLotto());
        }
        return new Lottos(lottos);
    }

    public Lotto createLotto() {
        Collections.shuffle(candidateNumbers);
        List<LottoNumber> numbers = candidateNumbers.subList(LOTTO_START_INDEX, LOTTO_SIZE);
        Collections.sort(numbers);

        return new Lotto(numbers);
    }
}
