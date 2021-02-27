package lotto.domain.generator;

import java.util.Collections;
import java.util.List;
import lotto.domain.Number;

public class LottoManualNumberGenerator implements LottoNumberGenerator {

    private final List<List<Number>> manualSelectedNumbers;
    private int emitNumbers;

    public LottoManualNumberGenerator(List<List<Number>> manualSelectedNumbers) {
        this.manualSelectedNumbers = manualSelectedNumbers;
        emitNumbers = 0;
    }

    @Override
    public synchronized List<Number> generateNumbers() {
        List<Number> generateNumber = manualSelectedNumbers.get(emitNumbers);
        emitNumbers++;
        return Collections.synchronizedList(generateNumber);
    }
}
