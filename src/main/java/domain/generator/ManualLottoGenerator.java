package domain.generator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {

    private final int[] inputNumbers;

    public ManualLottoGenerator(final int[] inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    @Override
    public Set<Integer> generateNumbers() {
        return new HashSet<>(Arrays.stream(inputNumbers)
                .boxed().collect(Collectors.toList()));
    }

}
