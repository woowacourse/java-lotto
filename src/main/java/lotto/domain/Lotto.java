package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validLotto(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validLotto(List<LottoNumber> lotto) {
        checkLottoSize(lotto);
        checkSameLottoNumber(lotto);
    }

    private void checkLottoSize(List<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE){
            throw new InvalidLottoException("로또는 1장에 6개의 숫자로 이루어져 있습니다.");
        }
    }

    private void checkSameLottoNumber(List<LottoNumber> lotto) {
        if (lotto.size() != new HashSet<>(lotto).size()){
            throw new InvalidLottoException("로또에 중복되는 숫자가 있습니다.");
        }
    }

    boolean matchNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    int matchNumbers(Lotto lotto){
        return (int) lottoNumbers.stream()
                .filter(lotto::matchNumber)
                .count();
    }
}
