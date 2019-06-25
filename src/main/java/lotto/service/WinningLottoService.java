package lotto.service;

import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.dto.WinningLottoDto;

import java.util.List;

public class WinningLottoService {
    private static final WinningLottoDao dao = WinningLottoDao.getDao();
    private static final TicketCreator ticketCreator = new LottoCreator();

    private WinningLottoService() {
    }

    public static WinningLotto insertWinningLotto(List<Integer> numbers, int bonus) {
        WinningLotto winningLotto = new WinningLotto(generate(numbers), toLottoNumber(bonus));
        dao.insertWinningLotto(new WinningLottoDto(winningLotto));
        return winningLotto;
    }

    public static WinningLotto selectWinningLotto(int round) {
        WinningLottoDto dto = dao.selectWinningLotto(round);
        return new WinningLotto(generate(dto.getNumbers()), toLottoNumber(dto.getBonus()));
    }

    public static WinningLotto currentWinnigLotto() {
        WinningLottoDto dto = dao.currentWinningLotto();
        return new WinningLotto(generate(dto.getNumbers()), toLottoNumber(dto.getBonus()));

    }

    private static Ticket generate(List<Integer> numbers) {
        return ticketCreator.create(numbers);
    }

    private static LottoNumber toLottoNumber(int number) {
        return new LottoNumber(number);
    }
}
