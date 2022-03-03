package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerators implements LottoGenerator{

    private final ManualLottoGenerator manualLottoGenerator;
    private final RandomLottoGenerator randomLottoGenerator;

    public LottoGenerators(ManualLottoGenerator manualLottoGenerator, RandomLottoGenerator randomLottoGenerator) {
        this.manualLottoGenerator = manualLottoGenerator;
        this.randomLottoGenerator = randomLottoGenerator;
    }


    @Override
    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottoGenerator.generate());
        lottos.addAll(randomLottoGenerator.generate());
        return lottos;
    }

}
