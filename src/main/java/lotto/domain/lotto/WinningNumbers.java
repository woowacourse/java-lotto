package lotto.domain.lotto;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.PayOut;
import lotto.domain.rank.Rank;

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
        Map<Rank, Long> statistics = lottoTicket.toLottoNumbersList().stream()
            .map(this::getRank)
            .filter(rank -> Rank.FAIL != rank)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return new WinningStatistics(fillUnrankedCount(statistics), payOut);
    }

    private Rank getRank(LottoNumbers lottoNumbers) {
        return Rank.getRank(
            this.lottoNumbers.getMatchCount(lottoNumbers),
            lottoNumbers.contains(bonusNumber)
        );
    }

    private Map<Rank, Long> fillUnrankedCount(Map<Rank, Long> statistics) {
        Rank.getAllPossibleRanks().stream()
            .filter(rank -> !statistics.containsKey(rank))
            .forEach(rank -> statistics.put(rank, 0L));

        return statistics;
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