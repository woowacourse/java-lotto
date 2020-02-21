package lotto.domain.result;

import lotto.domain.result.win.prize.PrizeGroup;
import lotto.view.dto.PrizeResponseBundleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class LottoResultBundle {
    private final List<LottoResult> lottoResults;

    public LottoResultBundle(List<LottoResult> lottoResults) {
        this.lottoResults = lottoResults;
    }

    public PrizeResponseBundleDTO getStatistics() {
        return new PrizeResponseBundleDTO(getPrizeGroup());
    }

    private List<PrizeGroup> getPrizeGroup() {
        return this.lottoResults.stream()
                .map(PrizeGroup::findPrizeByLottoResult)
                .collect(Collectors.toList());
    }
}
