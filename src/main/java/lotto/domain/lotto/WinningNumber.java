package lotto.domain.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.Number;

public class WinningNumber {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumber(String lottoNumber, String bonusNumber) {
        validateBonusNumberFormat(bonusNumber);
        this.bonusNumber = new LottoNumber(new Number(bonusNumber));

        lottoNumbers = getLottoNumbersFromStringLottoNumberList(getSplitLottoNumber(lottoNumber));
    }

    private List<String> getSplitLottoNumber(String lottoNumber) {
        return Arrays.asList(lottoNumber.split(",", -1));
    }

    private LottoNumbers getLottoNumbersFromStringLottoNumberList(List<String> splitLottoNumber) {
        return new LottoNumbers(
            splitLottoNumber.stream()
                .map(v -> new LottoNumber(new Number(v.trim())))
                .collect(Collectors.toList())
        );
    }

    private void validateBonusNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 볼의 하나의 숫자로 이루어져야 합니다.");
        }
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningNumber that = (WinningNumber) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
