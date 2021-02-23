package lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoGroup {

    private final List<Lotto> lottos;
    private final int autoCount;
    private final int manualCount;

    public LottoGroup(List<Lotto> lottos, final int autoCount) {
        this(lottos, autoCount, 0);
    }

    public LottoGroup(List<Lotto> lottos, final int autoCount, final int manualCount) {
        this.lottos = lottos;
        this.autoCount = autoCount;
        this.manualCount = manualCount;
    }

    public int size() {
        return lottos.size();
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public List<Lotto> lottoGroup() {
        return Collections.unmodifiableList(lottos);
    }
}
