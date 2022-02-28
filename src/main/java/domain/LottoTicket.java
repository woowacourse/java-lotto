package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.LottoNumberGenerator;

public class LottoTicket {

    private final List<Lotto> lottos = new ArrayList<>();

    public LottoTicket(int lottoCount, LottoNumberGenerator generatorPolicy) {
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(generatorPolicy.generate()));
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
