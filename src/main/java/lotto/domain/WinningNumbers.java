package lotto.domain;

import static lotto.domain.enumeration.BallType.BONUS;
import static lotto.domain.enumeration.BallType.NORMAL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.enumeration.Rank;
import lotto.domain.vo.LottoNumber;

public class WinningNumbers {

    private static final int WINNING_NUMBER_SIZE = 6;

    private final List<WinningNumber> winningNumbers;

    private WinningNumbers(List<LottoNumber> normalWinningNumbers, LottoNumber bonusWinningNumber) {
        validateNormalWinningNumbers(normalWinningNumbers);
        validateDuplication(normalWinningNumbers, bonusWinningNumber);

        this.winningNumbers = getWinningNumbers(normalWinningNumbers, bonusWinningNumber);
    }

    private void validateNormalWinningNumbers(List<LottoNumber> normalWinningNumbers) {
        if (normalWinningNumbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplication(List<LottoNumber> normalWinningNumbers, LottoNumber bonusWinningNumber) {
        normalWinningNumbers.add(bonusWinningNumber);

        Set<LottoNumber> set = new HashSet<>(normalWinningNumbers);

        if (set.size() < normalWinningNumbers.size()) {
            throw new IllegalArgumentException("중복된 숫자가 존재할 수 없습니다.");
        }

        normalWinningNumbers.remove(bonusWinningNumber);
    }

    public static WinningNumbers create(List<LottoNumber> normalWinningNumbers, LottoNumber bonusWinningNumber) {
        return new WinningNumbers(normalWinningNumbers, bonusWinningNumber);
    }

    private List<WinningNumber> getWinningNumbers(List<LottoNumber> normalWinningNumbers, LottoNumber bonusWinningNumber) {
        List<WinningNumber> winningNumbers = new ArrayList<>();

        for (LottoNumber normalWinningNumber : normalWinningNumbers) {
            WinningNumber winningNumber = new WinningNumber(normalWinningNumber, NORMAL);
            winningNumbers.add(winningNumber);
        }

        winningNumbers.add(new WinningNumber(bonusWinningNumber, BONUS));

        return winningNumbers;
    }

    public Rank compare(LottoTicket lottoTicket) {
        return Rank.of(getCorrectCount(lottoTicket), isBonus(lottoTicket));
    }

    private int getCorrectCount(LottoTicket lottoTicket) {
        return (int) winningNumbers.stream()
                .filter(lottoTicket::isSame)
                .count();
    }

    private boolean isBonus(LottoTicket lottoTicket) {
        return winningNumbers.stream()
                .filter(lottoTicket::isSame)
                .anyMatch(WinningNumber::isBonus);
    }
}
