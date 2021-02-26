package lottogame.domain.lotto;

import lottogame.utils.DuplicateLottoNumberException;
import lottogame.utils.InvalidBonusBallNumberException;
import lottogame.utils.InvalidLottoNumberRangeException;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(List<Integer> numbers, int bonusBall) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(number -> LottoNumber.valueOf(number))
                .collect(Collectors.toList());
        duplicateBonusBall(numbers, bonusBall);
        lotto = new Lotto(lottoNumbers);
        this.bonusBall = makeBonusBall(bonusBall);
    }

    private LottoNumber makeBonusBall(int bonusBall) {
        try {
            return LottoNumber.valueOf(bonusBall);
        } catch (InvalidLottoNumberRangeException e) {
            throw new InvalidBonusBallNumberException();
        }
    }

    private void duplicateBonusBall(List<Integer> numbers, int bonusBall) {
        if (numbers.contains(bonusBall)) {
            throw new DuplicateLottoNumberException();
        }
    }

    public int matchNumbers(List<LottoNumber> lottoNumbers) {
        return lotto.matchNumberCount(lottoNumbers);
    }

    public boolean matchBonusBall(Lotto lotto) {
        return lotto.matchBonusBall(bonusBall);
    }
}
