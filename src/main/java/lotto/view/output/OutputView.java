package lotto.view.output;

import lotto.dto.LottoMatchKindDto;
import lotto.dto.LottoNumbersDto;

import java.util.List;

public interface OutputView {
    void printPurchaseCount(final int purchaseCount);

    void printLottoNumbersGroup(final List<LottoNumbersDto> lottoNumbersGroup);

    void printCountOfWinningByMatchKind(final List<LottoMatchKindDto> winningResult);

    void printProfitRate(final double profitRate);
}
