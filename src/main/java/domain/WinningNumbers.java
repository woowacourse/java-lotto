package domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

    private static final String BONUS_DUPLICATE_ERROR = "당첨 번호에 보너스 번호가 포함되어 있습니다.";

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    private WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        this.winningTicket = LottoTicket.valueOf(winningNumbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    public static WinningNumbers valueOf(final List<Integer> winningNumbers, final int bonusNumber) {
        validateDistinctBonus(winningNumbers, bonusNumber);
        return new WinningNumbers(winningNumbers, bonusNumber);
    }

    private static void validateDistinctBonus(final List<Integer> winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_DUPLICATE_ERROR);
        }
    }

    public List<Ranking> calculateRankings(final LottoTickets lottoTickets) {
        return lottoTickets.toList().stream()
            .map(this::checkRanking)
            .collect(Collectors.toList());
    }

    private Ranking checkRanking(LottoTicket lottoTicket) {
        int matching = winningTicket.countMatchingNumbers(lottoTicket);
        boolean bonusMatching = lottoTicket.contains(bonusNumber);
        return Ranking.select(matching, bonusMatching);
    }
}
