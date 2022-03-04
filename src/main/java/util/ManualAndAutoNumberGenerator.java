package util;

import domain.ManualLotto;
import java.util.Iterator;
import java.util.List;

public class ManualAndAutoNumberGenerator extends LottoNumberGenerator implements NumberGenerator {

    private final Iterator<ManualLotto> manualLottoIterator;

    public ManualAndAutoNumberGenerator(List<ManualLotto> manualLottos) {
        this.manualLottoIterator = manualLottos.listIterator();
    }

    @Override
    public List<Integer> generate() {
        if (manualLottoIterator.hasNext()) {
            return manualLottoIterator.next().getManualLottoNumbers();
        }

        return super.generate();
    }
}
