package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WinningNumbersDto {

    private final Set<LottoNumber> lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbersDto(Set<Integer> lottoNumbers, int bonusNumber) {
        validateDuplicatedBonusNumber(lottoNumbers, bonusNumber);
        this.bonusNumber = LottoNumber.of(bonusNumber);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::of)
                .collect(toSet());
    }

    private void validateDuplicatedBonusNumber(Set<Integer> lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return this.lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return this.bonusNumber;
    }
}
