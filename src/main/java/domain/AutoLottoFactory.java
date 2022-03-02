package domain;

import util.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoFactory implements LottoFactory {
    List<Lotto> lottos;

    public AutoLottoFactory(LottoNumberGenerator generator, int generateCount) {
        this.lottos = new ArrayList<>();
        for (int i = 0; i < generateCount; i++) {
            lottos.add(new Lotto(generator.generate()));
        }
    }

    @Override
    public List<Lotto> create() {
        return lottos;
    }
}
