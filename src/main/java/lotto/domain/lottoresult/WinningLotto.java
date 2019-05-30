package lotto.domain.lottoresult;

import lotto.domain.lotto.InvalidLottoNumberException;
import lotto.domain.lotto.InvalidLottoTicketException;
import lotto.domain.lotto.LottoTicket;
import lotto.util.StringConverter;

import java.util.List;

public class WinningLotto{
    private final LottoTicket winningLotto;

    private WinningLotto(LottoTicket winningLotto) {
        this.winningLotto = winningLotto;
    }

    public static WinningLotto create(String lottoNumbersText) {
        try {
            return create(StringConverter.toNumbers(lottoNumbersText));
        } catch (NumberFormatException e) {
            throw new InvalidWinningLottoException("번호는 숫자만 가능합니다.");
        } catch (InvalidLottoTicketException e) {
            throw new InvalidWinningLottoException("로또 번호 형식이 아닙니다.");
        } catch (InvalidLottoNumberException e) {
            throw new InvalidWinningLottoException("로또 번호 범위에 벗어납니다.");
        }
    }
    public static WinningLotto create(List<Integer> lottoNumbers) {
        return new WinningLotto(LottoTicket.create(lottoNumbers));
    }

    public LottoRank checkLottoRank(LottoTicket userLottoTicket) {
        return LottoRank.rankOf(winningLotto.countOfMatch(userLottoTicket));
    }
}
