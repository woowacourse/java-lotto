package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class WinningLotto {

    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> winningLotto;

    public WinningLotto(List<LottoNumber> winningLotto) {
        if(!isValidSize(winningLotto)){
            throw new InvalidLottoException("로또의 개수가 잘못되었습니다.");
        }
        if(isDuplicated(winningLotto)){
            throw new InvalidLottoException("중복된 번호가 있습니다.");
        }
        this.winningLotto = winningLotto;
    }

    private boolean isDuplicated(List<LottoNumber> scannedNumbers) {
        return scannedNumbers.size() != new HashSet<>(scannedNumbers).size();
    }

    private boolean isValidSize(List<LottoNumber> scannedNumbers){
        return scannedNumbers.size() == LOTTO_SIZE;
    }
    
}
