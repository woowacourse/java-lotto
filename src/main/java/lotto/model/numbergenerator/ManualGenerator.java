package lotto.model.numbergenerator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ManualGenerator implements LottoNumberGenerator{
    private final Iterator<List<Integer>> manualLottos;

    public ManualGenerator(List<List<Integer>> manualLottos) {
        this.manualLottos = manualLottos.iterator();
    }

    @Override
    public List<Integer> generate() {
        if (manualLottos.hasNext()) {
            return manualLottos.next();
        }
        return Collections.emptyList();
    }
}
