package domain.strategy;

import java.util.List;

public class StubRandomLottoNumberGenerator implements LottoNumberGenerateStrategy {
    private final List<List<Integer>> numberSequences;
    private int sequenceCursor = 0;

    public StubRandomLottoNumberGenerator(List<List<Integer>> numberSequences) {
        this.numberSequences = numberSequences;
    }

    @Override
    public List<Integer> generateLottoNumbers() {
        int currentCursor = sequenceCursor;
        sequenceCursor = (sequenceCursor + 1) % numberSequences.size();

        return numberSequences.get(currentCursor);
    }
}
