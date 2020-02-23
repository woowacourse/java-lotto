package lotto.domain.result;

import lotto.domain.result.win.rank.Rank;
import lotto.view.dto.ResultDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoResultBundle {
    private final List<LottoResult> lottoResults;

    public LottoResultBundle(List<LottoResult> lottoResults) {
        this.lottoResults = lottoResults;
    }

    public ResultDTO createResultDTO() {
        return new ResultDTO(convertLottoResultsToRanks());
    }

    private List<Rank> convertLottoResultsToRanks() {
        return this.lottoResults.stream()
                .map(Rank::findRankByLottoResult)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResultBundle that = (LottoResultBundle) o;
        return Objects.equals(lottoResults, that.lottoResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoResults);
    }
}
