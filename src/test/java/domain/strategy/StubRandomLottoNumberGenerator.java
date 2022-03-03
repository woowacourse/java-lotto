package domain.strategy;

import java.util.List;
import java.util.Set;

public class StubRandomLottoNumberGenerator implements LottoNumberGenerateStrategy {
    private final List<Set<Integer>> numberSequences;
    private int sequenceCursor = 0;

    public StubRandomLottoNumberGenerator(List<Set<Integer>> numberSequences) {
        this.numberSequences = numberSequences;
    }

    @Override
    public Set<Integer> generateNumbers() {
        int currentCursor = sequenceCursor;
        sequenceCursor = (sequenceCursor + 1) % numberSequences.size();

        return numberSequences.get(currentCursor);
    }
}
