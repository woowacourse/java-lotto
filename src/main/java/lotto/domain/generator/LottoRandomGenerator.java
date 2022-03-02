package lotto.domain.generator;

import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.vo.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.util.constants.Lotto.LAST_LOTTO_NUMBER;

public class LottoRandomGenerator implements LottoGenerator {
    private static final int BONUS_NUMBER_INDEX = 6;

    private final List<LottoNumber> basicNumbers;

    public LottoRandomGenerator() {
        basicNumbers = IntStream.range(0, LAST_LOTTO_NUMBER)
                .mapToObj(index -> LottoNumber.from(String.valueOf(index + 1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Lotto> generateLottosExceptManualGenerated(int numberOfGenerating, List<Lotto> manualGenerated) {
        final Set<Lotto> generatedNumbersGroup = new HashSet<>(manualGenerated);
        return generateLottos(generatedNumbersGroup, numberOfGenerating);
    }

    private List<Lotto> generateLottos(final Set<Lotto> generatedNumbersGroup, final int numberOfGenerating) {
        while (generatedNumbersGroup.size() < numberOfGenerating) {
            generatedNumbersGroup.add(generateLotto());
        }
        return new ArrayList<>(generatedNumbersGroup);
    }

    private Lotto generateLotto() {
        Collections.shuffle(basicNumbers);
        final Set<LottoNumber> generatedNumbers = basicNumbers.stream()
                .limit(BONUS_NUMBER_INDEX)
                .collect(Collectors.toUnmodifiableSet());
        return new Lotto(generatedNumbers);
    }
}
