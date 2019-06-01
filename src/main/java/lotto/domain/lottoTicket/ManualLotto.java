package lotto.domain.lottoTicket;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualLotto extends Lotto {
    public ManualLotto(List<Integer> userNumbers) {
        super(convertLottoNumbers(userNumbers));
    }

    public static List<LottoNumber> convertLottoNumbers(List<Integer> userNumbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        Collections.sort(userNumbers);
        for (Integer number : userNumbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }
}
