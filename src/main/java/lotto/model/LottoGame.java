package lotto.model;

import static lotto.ValidationUtils.*;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";

    private final Lottos lottos;
    private final LottoMoney lottoMoney;

    public LottoGame(LottoMoney lottoMoney, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoMoney = lottoMoney;
        this.lottos = buyLottos(lottoNumberGenerator);
    }

    private Lottos buyLottos(LottoNumberGenerator lottoNumberGenerator) {
        return new Lottos(lottoNumberGenerator, lottoMoney.getLottoSize());
    }

    public LottoResult generateLottoResult(List<Integer> integers, int bonusNumber) {
        List<Integer> winningNumbers = new ArrayList<>(integers);
        validateEmptyCollection(winningNumbers);
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        return new LottoResult(lottos, new Lotto(winningNumbers), new LottoNumber(bonusNumber));
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    public Yield calculateYield(LottoResult lottoResult) {
        return new Yield(lottoMoney, lottoResult.getTotalWinningMoney());
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
