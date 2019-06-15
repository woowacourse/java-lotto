package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoRepository {
    private final List<Lotto> lottos;

    public LottoRepository() {
        this.lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public void addAll(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

//
//    public List<Lotto> getManualLottos() {
//        List<Lotto> manual = lottos.stream().filter(lotto -> !lotto.isAuto()).collect(Collectors.toList());
//        return Collections.unmodifiableList(manual);
//    }
//
//    public List<Lotto> getAutoLottos() {
//        List<Lotto> auto = lottos.stream().filter(Lotto::isAuto).collect(Collectors.toList());
//        return Collections.unmodifiableList(auto);
//    }
}
