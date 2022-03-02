package util;

import domain.ManualLotto;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ManualAndAutoNumberGenerator implements LottoNumberGenerator {

    private final Iterator<ManualLotto> manualLottoIterator;

    public ManualAndAutoNumberGenerator(List<ManualLotto> manualLottos) {
        this.manualLottoIterator = manualLottos.listIterator();
    }

    public List<Integer> generate() {
        if (manualLottoIterator.hasNext()) {
            return manualLottoIterator.next().getManualLottoNumbers();
        }
        return LottoNumberGenerator.super.generate();
    }
}
