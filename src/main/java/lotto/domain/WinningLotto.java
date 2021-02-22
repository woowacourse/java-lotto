package lotto.domain;

import java.util.List;

public class WinningLotto {
    
    private final Lotto lotto;
    
    private final LottoNumber bonusNumber;
    
    private WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }
    
    public static WinningLotto of(List<Integer> numbers, LottoNumber bonusNumber) {
        final Lotto lotto = Lotto.fromNumbers(numbers);
        
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
        
        return new WinningLotto(lotto, bonusNumber);
    }
    
    public Lotto getLotto() {
        return lotto;
    }
    
    public long countMatchingNumber(Lotto lotto) {
        return this.lotto.countMatchingNumber(lotto);
    }
    
    public boolean checkThatLottoHasBonusNumber(Lotto lotto) {
        return lotto.contains(this.bonusNumber);
    }
}
