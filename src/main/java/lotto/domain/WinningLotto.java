package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private static final int LOTTO_SIZE = 6;

    public WinningLotto(List<LottoNumber> winningLotto) {
        super(winningLotto);
        if (!isValidSize(winningLotto)) {
            throw new InvalidLottoException("로또의 개수가 잘못되었습니다.");
        }
    }

    @Override
    boolean isValidSize(List<LottoNumber> scannedNumbers) {
        return scannedNumbers.size() == LOTTO_SIZE;
    }
}
