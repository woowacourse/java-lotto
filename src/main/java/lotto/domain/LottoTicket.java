package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoTicket {
    private static final int LOTTO_NUMBERS = 6;

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new TreeSet<>(lottoNumbers);
        validateLotto();
    }

    private void validateLotto() {
        if (lottoNumbers.size() != LOTTO_NUMBERS) {
            throw new IllegalArgumentException("로또 번호는 중복없는 6개 숫자만 가능합니다.");
        }
    }

    //TODO 갈끔하게 수정해보자
    public int getSameCount(LottoTicket otherLottoTicket) {
        int sameCount = 0;
        for (LottoNumber lottoNumber : otherLottoTicket.lottoNumbers) {
            sameCount += checkNumber(lottoNumber);
        }
        return sameCount;
    }

    private int checkNumber(LottoNumber lottoNumber) {
        System.out.println(lottoNumber);
        if (lottoNumbers.contains(lottoNumber)) {
            return 1;
        }
        return 0;
    }
}