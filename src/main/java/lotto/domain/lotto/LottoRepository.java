package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRepository {
    private final List<Lotto> manualLottos;
    private final List<Lotto> autoLottos;

    public LottoRepository() {
        this.manualLottos = new ArrayList<>();
        this.autoLottos = new ArrayList<>();
    }

    public void addManualLotto(Lotto lotto) {
        manualLottos.add(lotto);
    }

    public void addAutoLottos(Lotto lotto) {
        autoLottos.add(lotto);
    }

    public void addAllManualLottos(List<Lotto> lottos) {
        manualLottos.addAll(lottos);
    }

    public void addAllAutoLottos(List<Lotto> lottos) {
        autoLottos.addAll(lottos);
    }

    public List<Lotto> getManualLottos() {
        return Collections.unmodifiableList(manualLottos);
    }

    public List<Lotto> getAutoLottos() {
        return Collections.unmodifiableList(autoLottos);
    }
}
