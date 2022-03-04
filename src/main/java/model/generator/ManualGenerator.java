package model.generator;

import java.util.Collections;
import java.util.List;
import model.Lotto;

public class ManualGenerator implements LottosGenerator {

    private final List<Lotto> manualLottos;

    public ManualGenerator(List<Lotto> manualLottos) {
        this.manualLottos = Collections.unmodifiableList(manualLottos);
    }

    @Override
    public List<Lotto> createLottos() {
        return manualLottos;
    }
}
