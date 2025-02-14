package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;

import java.util.HashSet;
import java.util.List;

public class LottoNumbers {

    private final List<LottoNumber> item;

    public LottoNumbers(List<LottoNumber> item) {
        validLottoNumbers(item);
        this.item = sorted(item);
    }

    public static LottoNumbers from(List<LottoNumber> lottoNumbers) {
        return new LottoNumbers(lottoNumbers);
    }

    public boolean contain(LottoNumber lottoNumber) {
        return item.contains(lottoNumber);
    }

    public long getMatchCount(LottoNumbers winnerNumbers) {
        return item.stream().filter(winnerNumbers::contain).count();
    }


    private void validLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUM_SIZE) {
            throw new IllegalArgumentException("로또 번호 6개를 입력해주세요.");
        }

        if (new HashSet<>(lottoNumbers).size() != LOTTO_NUM_SIZE) {
            throw new IllegalStateException("중복은 불가능합니다.");
        }
    }

    private List<LottoNumber> sorted(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .toList();
    }

    public List<LottoNumber> getItem() {
        return item;
    }
}
