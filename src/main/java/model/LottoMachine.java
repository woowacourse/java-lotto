package model;

import generator.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final NumberGenerator numberGenerator;

    public LottoMachine(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> issueLottos(final int lottoCount) {
        final List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < lottoCount) {
            lottos.add(Lotto.from(numberGenerator.pickNumbersInRange(Lotto.LOTTO_NUMBER_COUNT)));
        }

        return lottos;
    }

    public LottoRank checkWinningRank(
        final Lotto lotto,
        final WinningNumbers winningNumbers
    ) {
        final int lottoMatchCount = winningNumbers.calculateLottoMatchCount(lotto.getLottoNumbers());
        final boolean bonusBallMatch = winningNumbers.matchBonusNumber(lotto.getLottoNumbers());

        return LottoRank.of(lottoMatchCount, bonusBallMatch);
    }
}
