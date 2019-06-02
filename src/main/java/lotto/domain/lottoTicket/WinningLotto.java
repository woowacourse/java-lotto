package lotto.domain.lottoTicket;

import lotto.domain.LottoNumber;
import lotto.domain.exception.OverlapLottoBonusNumberException;

import java.util.List;

public class WinningLotto extends Lotto {
    private final LottoNumber bonus;

    public WinningLotto(List<Integer> winningNumbers, int bonus) {
        super(convertLottoNumbers(winningNumbers));
        if (winningNumbers.contains(bonus)) {
            throw new OverlapLottoBonusNumberException("당첨 번호와 중복된 번호 입니다.");
        }
        this.bonus = new LottoNumber(bonus);
    }

    public boolean isContainBonus(Lotto lotto) {
        return lotto.isContainNumber(bonus);
    }

    private static List<LottoNumber> convertLottoNumbers(List<Integer> winningNumbers) {
        return ManualLotto.convertLottoNumbers(winningNumbers);
    }
}
