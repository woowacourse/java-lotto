package lotto.domain;

import java.util.List;

public class LottoFactory {
    public static Lotto generateAutoLotto(){
        List<LottoNumber> autoLottoNumbers = LottoNumbers.getAutoLottoNumbers();
        return new Lotto(autoLottoNumbers);
    }
}
