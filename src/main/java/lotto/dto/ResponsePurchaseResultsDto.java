package lotto.dto;

import java.util.List;
import lotto.domain.vo.Lotto;

public class ResponsePurchaseResultsDto {

    private final List<Lotto> manualLottos;
    private final List<Lotto> autoLottos;

    public ResponsePurchaseResultsDto(ResponsePurchaseResults manualResults, ResponsePurchaseResults autoResults) {
        this.manualLottos = manualResults.getLottos();
        this.autoLottos = autoResults.getLottos();
    }

    public List<Lotto> getManualLottos() {
        return manualLottos;
    }

    public List<Lotto> getAutoLottos() {
        return autoLottos;
    }

    public int getManualLottoCount() {
        return manualLottos.size();
    }

    public int getAutoLottoCount() {
        return autoLottos.size();
    }
}
