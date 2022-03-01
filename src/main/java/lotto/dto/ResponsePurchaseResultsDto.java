package lotto.dto;

import java.util.Collections;
import java.util.List;
import lotto.domain.vo.Lotto;

public class ResponsePurchaseResultsDto {

    private final List<Lotto> lottos;
    private final int manualLottoCount;
    private final int autoLottoCount;

    public ResponsePurchaseResultsDto(List<Lotto> lottos, int manualLottoCount, int autoLottoCount) {
        this.lottos = Collections.unmodifiableList(lottos);
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = autoLottoCount;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
