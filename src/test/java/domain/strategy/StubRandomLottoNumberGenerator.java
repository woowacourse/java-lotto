package domain.strategy;

import domain.LottoNumber;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StubRandomLottoNumberGenerator implements LottoNumberGenerateStrategy {
    private final List<Set<LottoNumber>> numbersSequences;
    private int sequenceCursor = 0;

    private StubRandomLottoNumberGenerator(List<Set<LottoNumber>> numbersSequences) {
        this.numbersSequences = numbersSequences;
    }

    public static StubRandomLottoNumberGenerator from(List<Set<LottoNumber>> numbersSequences) {
        return new StubRandomLottoNumberGenerator(numbersSequences);
    }

    public static StubRandomLottoNumberGenerator fromRawValues(List<Set<Integer>> numbersSequences) {
        List<Set<LottoNumber>> mapped = numbersSequences.stream()
                .map(StubRandomLottoNumberGenerator::mapNumberToLottoNumber)
                .collect(Collectors.toList());

        return new StubRandomLottoNumberGenerator(mapped);
    }

    private static Set<LottoNumber> mapNumberToLottoNumber(Set<Integer> numbers) {
        return numbers.stream().map(LottoNumber::from).collect(Collectors.toSet());
    }

    @Override
    public Set<LottoNumber> generateNumbers() {
        int currentCursor = sequenceCursor;
        sequenceCursor = (sequenceCursor + 1) % numbersSequences.size();

        return numbersSequences.get(currentCursor);
    }
}
