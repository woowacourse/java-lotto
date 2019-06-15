package lotto.domain.lotto;

import lotto.exception.DuplicateLottoNumberException;

import java.util.EnumMap;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        this.bonusNumber = LottoNumber.getNumber(bonusNumber);
        if (winningLotto.contains(this.bonusNumber)) {
            throw new DuplicateLottoNumberException("로또숫자와 보너스숫자가 중복으로 입력되었습니다");
        }
        this.winningLotto = winningLotto;
    }

    public Result match(LottoTickets lottoTickets) {
        return new Result(calculateLottoResult(lottoTickets));
    }

    private Map<Rank, Long> calculateLottoResult(LottoTickets lottoTickets) {
        return lottoTickets.getAllLottoTickets()
                .stream()
                .map(this::matchRank)
                .collect(groupingBy(identity(),
                        () -> new EnumMap<>(Rank.class),
                        counting()));
    }

    private Rank matchRank(Lotto lotto) {
        return Rank.valueOf(countMatchedLottoNumber(lotto), contains(lotto));
    }

    private int countMatchedLottoNumber(Lotto lotto) {
        return lotto.countMatchedLottoNumber(winningLotto);
    }

    private boolean contains(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public String toString() {
        return "WinningLotto{" +
                "winningLotto=" + winningLotto +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
