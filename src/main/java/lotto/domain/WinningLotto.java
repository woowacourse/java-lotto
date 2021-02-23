package lotto.domain;

public class WinningLotto {
    
    private static final String ERROR_WINNING_LOTTO_HAS_BOUNS = "로또 번호가 보너스 볼을 포함해선 안 됩니다.";
    
    private final Lotto lotto;
    
    private final LottoNumber bonusNumber;
    
    private WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }
    
    public static WinningLotto of(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_WINNING_LOTTO_HAS_BOUNS);
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
