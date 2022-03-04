package lotto.model.numbergenerator;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ManualGenerator implements LottoNumberGenerator {
    private final Iterator<List<Integer>> manualLottos;

    public ManualGenerator(List<List<Integer>> manualLottos) {
        List<List<Integer>> copyOfManualLottos = List.copyOf(manualLottos);
        this.manualLottos = List.copyOf(copyOfManualLottos.stream()
                .map(this::sorted)
                .collect(toList()))
            .iterator();
    }

    private List<Integer> sorted(List<Integer> integers) {
        return integers.stream()
            .sorted()
            .collect(toList());
    }

    @Override
    public List<Integer> generate() {
        if (manualLottos.hasNext()) {
            return manualLottos.next();
        }
        return Collections.emptyList();
    }
}
