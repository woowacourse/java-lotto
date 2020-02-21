package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private static final List<Lotto> lottos = new ArrayList<>();

    public Lottos(){};

    public Lottos(final int lottoCount) {
        for (int index = 0; index < lottoCount; index++) {
            lottos.add(LottoFactory.createOneLotto());
        }
    }

    public void addLotto(Lotto lotto){
        lottos.add(lotto);
    }

    public int getDummySize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}