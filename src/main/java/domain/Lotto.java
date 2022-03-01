package domain;

import java.util.List;

public interface Lotto {
    int countSameNumbers(List<LottoNumber> winningNumbers);
    boolean checkBonus(LottoNumber bonusNumber);
}
