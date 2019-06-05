package lotto.domain.lottoTicket;

import lotto.domain.LottoNumber;
import lotto.domain.exception.OverlapLottoNumberException;

import java.util.*;

public class ManualLotto extends Lotto {
    public ManualLotto(List<Integer> userNumbers) {
        super(convertLottoNumbers(userNumbers));
    }

    public static Set<LottoNumber> convertLottoNumbers(List<Integer> userNumbers) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        userNumbers.stream().forEach(number -> lottoNumbers.add(new LottoNumber(number)));
        checkOverlap(userNumbers, lottoNumbers);
        return lottoNumbers;
    }

    private static void checkOverlap(List<Integer> userNumbers, Set<LottoNumber> convertNumbers) {
        if(userNumbers.size() != convertNumbers.size()) {
            throw new OverlapLottoNumberException("중복되는 로또 번호가 있습니다.");
        }
    }
}
