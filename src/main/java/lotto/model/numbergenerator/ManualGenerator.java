package lotto.model.numbergenerator;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ManualGenerator implements LottoNumberGenerator {
    private final Iterator<Set<Integer>> manualLottos;

    public ManualGenerator(List<Set<Integer>> manualLottos) {
        List<Set<Integer>> copyOfManualLottos = List.copyOf(manualLottos);
        this.manualLottos = List.copyOf(copyOfManualLottos)
            .iterator();
    }

    @Override
    public Set<Integer> generate() {
        if (manualLottos.hasNext()) {
            return manualLottos.next();
        }
        return Collections.emptySet();
    }
}
