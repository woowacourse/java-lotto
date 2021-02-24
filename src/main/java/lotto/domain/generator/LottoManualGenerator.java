package lotto.domain.generator;

import java.util.Collections;
import java.util.List;

public class LottoManualGenerator implements LottoGenerator {

    private final List<List<Integer>> manualSelectedNumbers;
    private int emitNumbers;

    public LottoManualGenerator(List<List<Integer>> manualSelectedNumbers) {
        this.manualSelectedNumbers = manualSelectedNumbers;
        emitNumbers = 0;
    }

    @Override
    public List<Integer> generateNumbers() {
        List<Integer> generateNumber = manualSelectedNumbers.get(emitNumbers);
        emitNumbers++;
        return Collections.synchronizedList(generateNumber);
    }
}
