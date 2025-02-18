package domain;

import static domain.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static domain.exception.ExceptionMessage.INVALID_FORMAT;
import static domain.exception.ExceptionMessage.INVALID_RANGE;

import java.util.List;

public class WinningLotto{
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public static WinningLotto of(final List<Integer> numbers, final int bonusNumber){
        Lotto lotto = Lotto.from(numbers);
        LottoNumber bonus =  LottoNumber.from(bonusNumber);
        validateBonusDuplicate(lotto, bonus);

        return new WinningLotto(Lotto.from(numbers),bonus);
    }

    private WinningLotto(final Lotto lotto, final LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank countMatchNumbers(final Lotto lotto) {
        int count = lotto.matchCount(this);
        return Rank.matchRank(count, lotto.contains(bonusNumber));
    }

    public boolean contains(final LottoNumber number) {
        return lotto.contains(number);
    }

    private static void validateBonusDuplicate(final Lotto lotto, final LottoNumber bonusNumber) {
        if(lotto.contains(bonusNumber)){
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

}
