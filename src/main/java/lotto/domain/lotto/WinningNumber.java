package lotto.domain.lotto;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.PayOut;
import lotto.domain.rank.RankFactory;

public class WinningNumber {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumber(String lottoNumber, String bonusNumber) {
        LottoNumbers extractedLottoNumbers = LottoNumbers.valueOf(lottoNumber);
        validateBonusNumberFormat(bonusNumber);

        LottoNumber extractedBonusNumber = new LottoNumber(bonusNumber);
        validateDuplicateBonusNumberWithLottoNumbers(extractedLottoNumbers, extractedBonusNumber);

        this.lottoNumbers = extractedLottoNumbers;
        this.bonusNumber = extractedBonusNumber;
    }

    private void validateBonusNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 볼의 하나의 숫자로 이루어져야 합니다.");
        }
    }

    private void validateDuplicateBonusNumberWithLottoNumbers(LottoNumbers lottoNumbers,
                                                              LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 달라야 합니다.");
        }
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public AnalysedLottos analysingLottos(LottoGroup lottoGroup, PayOut payOut) {
        Map<RankFactory, Long> rankAndCount = lottoGroup.calculateLottoResult(lottoNumbers, bonusNumber);

        Arrays.stream(RankFactory.values())
                .filter(rank -> !rank.equals(RankFactory.FAIL) && !rankAndCount.containsKey(rank))
                .forEach(rank -> rankAndCount.put(rank, 0L));

        return new AnalysedLottos(rankAndCount, payOut);
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