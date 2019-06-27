package lotto.service;

import lotto.dao.WinningLottoDao;
import lotto.domain.LottoCreator;
import lotto.domain.WinningLotto;
import lotto.dto.WinningLottoDto;

import java.util.List;

public class WinningLottoService {
    private static final WinningLottoDao dao = WinningLottoDao.getDao();

    private WinningLottoService() {
    }

    public static WinningLotto insertWinningLotto(int round, List<Integer> numbers, int bonus) {
        WinningLotto winningLotto = LottoCreator.getLottoCreator().createWinningLotto(numbers, bonus);
        WinningLottoDto dto = new WinningLottoDto(numbers, bonus);
        dao.insertWinningLotto(round, dto);
        return winningLotto;
    }

    public static WinningLotto selectWinningLotto(int round) {
        WinningLottoDto dto = dao.selectWinningLotto(round);
        return LottoCreator.getLottoCreator().createWinningLotto(dto.getNumbers(), dto.getBonus());
    }
}
