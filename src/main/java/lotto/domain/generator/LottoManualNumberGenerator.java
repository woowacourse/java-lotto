package lotto.domain.generator;

import java.util.Collections;
import java.util.List;

public class LottoManualNumberGenerator implements LottoNumberGenerator {

    private final List<List<Integer>> manualSelectedNumbers;
    private int emitNumbers;

    public LottoManualNumberGenerator(List<List<Integer>> manualSelectedNumbers) {
        this.manualSelectedNumbers = manualSelectedNumbers;
        emitNumbers = 0;
    }

    @Override
    public synchronized List<Integer> generateNumbers() {
        List<Integer> generateNumber = manualSelectedNumbers.get(emitNumbers);
        emitNumbers++;
        return Collections.synchronizedList(generateNumber);
    }
}
