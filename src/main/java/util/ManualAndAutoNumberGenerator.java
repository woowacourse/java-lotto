package util;

import static domain.Lotto.LOTTO_NUMBER_MAXIMUM;
import static domain.Lotto.LOTTO_NUMBER_MINIMUM;

import domain.ManualLotto;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManualAndAutoNumberGenerator implements LottoNumberGenerator {

    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.range(LOTTO_NUMBER_MINIMUM, LOTTO_NUMBER_MAXIMUM + 1)
            .boxed()
            .collect(Collectors.toList());
    private final Iterator<ManualLotto> manualLottoIterator;

    public ManualAndAutoNumberGenerator(List<ManualLotto> manualLottos) {
        this.manualLottoIterator = manualLottos.listIterator();
    }

    public List<Integer> generate() {
        if (manualLottoIterator.hasNext()) {
            return manualLottoIterator.next().getManualLottoNumbers();
        }
        Collections.shuffle(CANDIDATE_NUMBERS);
        return CANDIDATE_NUMBERS.stream()
                .limit(LOTTO_NUMBER_LENGTH)
                .sorted()
                .collect(Collectors.toList());
    }
}
