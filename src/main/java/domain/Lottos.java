package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(int lottoCount) {
        for (int i = 0; i < lottoCount; i++) {
            List<LottoNumber> lottoNumbers = LottoNumbersGenerator.generate();
            lottos.add(new Lotto(lottoNumbers));
        }
    }
}
