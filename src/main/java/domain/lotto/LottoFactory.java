package domain.lotto;

import java.util.List;

public class LottoFactory {
    public static Lotto createLotto(final List<LottoNumber> lottoNumbers) {
        return Lotto.from(lottoNumbers);
    }

    public static WinNumbers createWinNums(final List<LottoNumber> lottoNumbers, LottoNumber bonus) {
        return WinNumbers.of(createLotto(lottoNumbers), bonus);
    }
}