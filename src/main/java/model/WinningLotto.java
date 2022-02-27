package model;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    static final String WINNING_NUMBERS_CONTAIN_BONUS_BALL = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다.";

    private final Lotto winningLotto;
    private final LottoNumber bonusBall;

    private WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        this.winningLotto = lotto;
        this.bonusBall = bonusBall;
    }

    public static WinningLotto of(List<Integer> lottoNumbers, int bonusBallNumber) {
        if (lottoNumbers.contains(bonusBallNumber)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_CONTAIN_BONUS_BALL);
        }

        return new WinningLotto(Lotto.from(lottoNumbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList())), LottoNumber.valueOf(bonusBallNumber));
    }

    public int countMatching(Lotto lotto) {
        return winningLotto.countMatching(lotto);
    }

    public boolean containBonusBall(Lotto lotto) {
        return lotto.contains(bonusBall);
    }
}
