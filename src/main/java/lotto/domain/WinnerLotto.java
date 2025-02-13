package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;

import java.util.HashSet;
import java.util.List;

public class WinnerLotto {
    private final List<LottoNumber> winnerNumbers;
    private final LottoNumber bonusNumber;

    public WinnerLotto(final List<LottoNumber> winnerNumbers, final LottoNumber bonusNumber) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
    }

    /**
     * // NOTICE 매개변수 사용에대한 의견 대립 List<LottoNumbers> - 필요한 것만 받을 수 있다. 즉 객체 전체를 넘겨주지 않아도 되기 때문에 Lotto 객체를 은닉화할 수 있다.
     * Lotto lotto - 강력한 타입 제한, Lotto의 객체를 통해 로또의 List<LottoNumbers>의 제약을 걸 수 있음.
     */
    public long getMatchCount(Lotto lotto) {
        return lotto.getLottoNumbers().stream().filter(winnerNumbers::contains).count();
    }

    public boolean hasBonus(Lotto lotto) {
        return lotto.getLottoNumbers().contains(bonusNumber);
    }

    public static void validateWinnerNumbers(List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != LOTTO_NUM_SIZE) {
            throw new IllegalArgumentException("중복은 불가능합니다.");
        }
    }

    public static void validateBonusNumbers(List<LottoNumber> winnerNumbers, LottoNumber bonusNumber) {
        if (winnerNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버가 당첨 번호에 중복됩니다.");
        }
    }
}
