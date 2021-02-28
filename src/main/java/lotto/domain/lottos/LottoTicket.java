package lotto.domain.lottos;

import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.lottos.winnerlotto.LottoWinner;
import lotto.util.ManualNumberGenerator;
import lotto.util.RandomNumberGenerator;

import java.util.*;

public class LottoTicket {

    public static final int LOTTO_NUMBER_SIZE = 6;
    public static final String NULL_ERROR_MESSAGE = "null 값은 허용하지 않습니다.";
    public static final String EMPTY_ERROR_MESSAGE = "로또 숫자는 하나 이상이어야 합니다.";
    public static final String COUNT_ERROR_MESSAGE = "로또 숫자는 %d개 여야 합니다.";
    public static final String DUPLICATE_ERROR_MESSAGE = "중복된 숫자가 존재합니다.";

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(final List<LottoNumber> lottoNumbers) {
        Objects.requireNonNull(lottoNumbers, NULL_ERROR_MESSAGE);
        validateLottoTicket(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket createAutoLottoTicket() {
        return new LottoTicket(new RandomNumberGenerator().generateNumbers());
    }

    public static LottoTicket createManualLottoTicket(final String input) {
        return new LottoTicket(new ManualNumberGenerator(input).generateNumbers());
    }

    public LottoRank getRank(final LottoWinner lottoWinner) {
        int matchCount = calculateMatchCount(lottoWinner.getLottoWinnerTicket());
        boolean matchBonusNumber = this.isContain(lottoWinner.getLottoWinnerBonusNumber());
        return LottoRank.matchLottoRank(matchCount, matchBonusNumber);
    }

    private int calculateMatchCount(final LottoTicket lottoWinnerTicket) {
        return (int) lottoWinnerTicket.getLottoNumbers()
                .stream()
                .filter(this::isContain)
                .count();
    }

    public boolean isContain(final LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }

    public boolean isContain(final int number) {
        return this.lottoNumbers.contains(new LottoNumber(number));
    }

    private void validateLottoTicket(final List<LottoNumber> lottoNumbers) {
        validateEmptyTicket(lottoNumbers);
        validateCount(lottoNumbers);
        validateDuplicate(lottoNumbers);
    }

    private void validateEmptyTicket(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateDuplicate(final List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> uniqueLottoNumbers = new HashSet<>(lottoNumbers);
        if (uniqueLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private void validateCount(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() == LOTTO_NUMBER_SIZE) {
            return;
        }
        throw new IllegalArgumentException(String.format(COUNT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
