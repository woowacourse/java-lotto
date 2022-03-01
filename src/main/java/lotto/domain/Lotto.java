package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.LottoException;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lotto;

    private Lotto(List<LottoNumber> lotto) {
        this.lotto = new ArrayList<>(lotto);
    }

    public static Lotto generateLottoByAuto() {
        return new Lotto(LottoNumber.getRandomLottoNumbers(LOTTO_SIZE));
    }

    public static Lotto generateLottoByManual(List<Integer> numbers) {
        checkSize(numbers);
        checkDuplication(numbers);
        return new Lotto(convertToLottoNumbers(numbers));
    }

    private static void checkSize(List<Integer> numbers) {
        if (!isCorrectSize(numbers)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_SIZE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectSize(List<Integer> numbers) {
        return numbers.size() == LOTTO_SIZE;
    }

    private static void checkDuplication(List<Integer> numbers) {
        if (isDuplication(numbers)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_DUPLICATION_ERROR_MESSAGE);
        }
    }

    private static boolean isDuplication(List<Integer> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }

    private static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::getByNumber)
                .sorted()
                .collect(Collectors.toList());
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
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
