package domain;

import java.util.ArrayList;
import java.util.List;

public class PassiveLottoFactory implements LottoFactory{

    List<Lotto> lottos;

    public PassiveLottoFactory(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    @Override
    public List<Lotto> create() {
        return lottos;
    }
}
