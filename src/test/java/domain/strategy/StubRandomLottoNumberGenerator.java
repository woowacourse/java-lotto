package domain.strategy;

import java.util.List;
import java.util.Set;

public class StubRandomLottoNumberGenerator implements LottoNumberGenerateStrategy {
    private final List<Set<Integer>> numbersSequences;
    private int sequenceCursor = 0;

    public StubRandomLottoNumberGenerator(List<Set<Integer>> numbersSequences) {
        this.numbersSequences = numbersSequences;
    }

    @Override
    public Set<Integer> generateNumbers() {
        int currentCursor = sequenceCursor;
        sequenceCursor = (sequenceCursor + 1) % numbersSequences.size();

        return numbersSequences.get(currentCursor);
    }
}
