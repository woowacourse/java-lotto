package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerators implements LottoGenerator{

    private final ManualLottoGenerator manualLottoGenerator;
    private final AutoLottoGenerator autoLottoGenerator;

    public LottoGenerators(ManualLottoGenerator manualLottoGenerator, AutoLottoGenerator autoLottoGenerator) {
        this.manualLottoGenerator = manualLottoGenerator;
        this.autoLottoGenerator = autoLottoGenerator;
    }


    @Override
    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottoGenerator.generate());
        lottos.addAll(autoLottoGenerator.generate());
        return lottos;
    }

}
