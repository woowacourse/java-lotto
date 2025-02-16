package domain;

import exception.LottoException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final String INVALID_LOTTO_SIZE = "로또 번호는 6개여야 합니다.";
    private static final String DUPLICATE_LOTTO_NUMBERS = "로또 번호는 중복될 수 없습니다!";
    private static final int LOTTO_LENGTH = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .sorted()
                .toList();
    }

    public int compare(WinningNumber winningNumber) {
        return (int) lottoNumbers.stream().filter(lottoNumber ->
            winningNumber.isContain(lottoNumber)
        ).count();
    }

    public boolean compareBonusNumber(BonusNumber bonusNumber) {
        return lottoNumbers.stream()
            .anyMatch(lottoNumber -> bonusNumber.isContain(lottoNumber));
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
            .map(LottoNumber::getLottoNumber)
            .toList();
    }

    private void validateLottoNumbers(List<Integer> lottoNumbers) {
        Set<Integer> duplicationSet = new HashSet<>(lottoNumbers);
        if(lottoNumbers.size() != duplicationSet.size()) {
            throw new LottoException(DUPLICATE_LOTTO_NUMBERS);
        }
        if(lottoNumbers.size() != LOTTO_LENGTH){
            throw new LottoException(INVALID_LOTTO_SIZE);
        }
    }

}
