package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.LottoException;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        checkSize(lotto);
        checkDuplication(lotto);
        this.lotto = new ArrayList<>(lotto);
    }

    private static void checkSize(List<LottoNumber> lotto) {
        if (!isCorrectSize(lotto)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_SIZE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectSize(List<LottoNumber> lotto) {
        return lotto.size() == LOTTO_SIZE;
    }

    private static void checkDuplication(List<LottoNumber> lotto) {
        if (isDuplication(lotto)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_DUPLICATION_ERROR_MESSAGE);
        }
    }

    private static boolean isDuplication(List<LottoNumber> lotto) {
        return new HashSet<>(lotto).size() != lotto.size();
    }

    public Rank getRank(WinningNumbers winningNumbers) {
        int winningNumberMatchCount = winningNumbers.getWinningLottoMatchCount(lotto);
        boolean bonusNumberMatch = winningNumbers.isBonusNumberContainedAt(lotto);
        return Rank.getRank(winningNumberMatchCount, bonusNumberMatch);
    }

    public int getMatchCount(List<LottoNumber> lottoToCompare) {
        return (int) lottoToCompare.stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean isContain(LottoNumber lottoNumberToCompare) {
        return lotto.contains(lottoNumberToCompare);
    }

    public List<Integer> getLottoToInteger() {
        return lotto.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }
}
