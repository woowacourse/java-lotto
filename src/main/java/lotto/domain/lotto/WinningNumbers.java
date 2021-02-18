package lotto.domain.lotto;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.PayOut;

public class WinningNumbers {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    private WinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers valueOf(String unparsedLottoNumbers, String unparsedBonusNumber) {
        LottoNumbers parsedLottoNumbers = LottoNumbers.valueOf(unparsedLottoNumbers);
        LottoNumber parsedBonusNumber = LottoNumber.valueOf(unparsedBonusNumber);

        validateDuplication(parsedLottoNumbers, parsedBonusNumber);

        return new WinningNumbers(parsedLottoNumbers, parsedBonusNumber);
    }

    private static void validateDuplication(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 달라야 합니다.");
        }
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public WinningStatistics getResult(LottoTicket lottoTicket, PayOut payOut) {
        Map<Integer, Long> result = lottoTicket.toLottoNumbersList().stream()
            .map(this::getRank)
            .filter(rank -> Rank.FAIL.getRank() != rank)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        IntStream.range(1, Rank.values().length)
            .filter(rank -> !result.containsKey(rank))
            .forEach(rank -> result.put(rank, 0L));

        return new WinningStatistics(result, payOut);
    }

    private int getRank(LottoNumbers lottoNumbers) {
        return Rank.getRank(
            this.lottoNumbers.getMatchCount(lottoNumbers),
            lottoNumbers.contains(bonusNumber)
        ).getRank();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningNumbers that = (WinningNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}