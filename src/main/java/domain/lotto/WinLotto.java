package domain.lotto;

import java.util.HashSet;
import java.util.List;

public class WinLotto extends Lotto {
    private static final String BONUS_DUPLICATED_ERROR_MESSAGE = "보너스 번호는 로또 번호와 중복될 수 없습니다.";
    private static final String LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE = "로또 번호는 중복될 수 없습니다.";

    private final LottoNumber bonus;

    WinLotto(final List<LottoNumber> lottoNumbers, LottoNumber bonus) {
        super(lottoNumbers);
        validate(lottoNumbers, bonus);
        this.bonus = bonus;
    }

    private static void validate(List<LottoNumber> lottoNumbers, LottoNumber bonus) {
        HashSet<LottoNumber> compareNums = new HashSet<>(lottoNumbers);
        if (compareNums.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE);
        }
        if (lottoNumbers.contains(bonus)) {
            throw new IllegalArgumentException(BONUS_DUPLICATED_ERROR_MESSAGE);
        }
    }

    public LottoNumber getBonus() {
        return bonus;
    }

}