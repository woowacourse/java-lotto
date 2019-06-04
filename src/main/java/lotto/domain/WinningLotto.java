package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinningLotto {
    private Lotto winningLotto;
    private BonusNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, BonusNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또숫자와 보너스숫자가 중복으로 입력되었습니다");
        }

        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Result produceResult(LottoTickets lottoTickets) {
        Map<Rank, Integer> map = lottoTickets.stream()
                .map(ticket -> Rank.valueOf(ticket.match(winningLotto), ticket.contains(bonusNumber)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(rank -> 1)));
        return new Result(map);
    }
}
