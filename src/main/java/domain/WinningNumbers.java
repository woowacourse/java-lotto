package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final int WINNING_TICKET_SIZE = 6;
    private static final int BONUS_NUMBER_SIZE = 1;

    private static final String WINNING_TICKET_SIZE_ERROR = "당첨 번호의 개수가 맞지 않습니다.";
    private static final String BONUS_DUPLICATE_ERROR = "당첨 번호에 보너스 번호가 포함되어 있습니다.";
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    private WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        this.winningTicket = LottoTicket.valueOf(winningNumbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    public static WinningNumbers valueOf(final List<Integer> winningNumbers, final int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        return new WinningNumbers(winningNumbers, bonusNumber);
    }

    private static void validate(final List<Integer> winningNumbers, final int bonusNumber) {
        validateSize(winningNumbers);
        validateDistinctBonus(winningNumbers, bonusNumber);
    }

    private static void validateSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != WINNING_TICKET_SIZE) {
            throw new IllegalArgumentException(WINNING_TICKET_SIZE_ERROR);
        }
    }

    private static void validateDistinctBonus(final List<Integer> winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_DUPLICATE_ERROR);
        }
    }

    public List<Ranking> getRankings(final List<LottoTicket> lottoTickets) {
        List<Ranking> rankings = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            rankings.add(lottoTicket.checkRanking(winningTicket, bonusNumber));
        }
        return rankings;
    }
}
