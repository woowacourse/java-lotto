package lotto.domain;

import lotto.LottoDto;
import lotto.util.GameResultDtoConverter;
import lotto.util.LottoDtoConverter;
import lotto.dao.LottosDao;
import lotto.dao.TurnDao;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final LottoMachine lottoMachine;
    private final LottosDao lottosDao;
    private final TurnDao turnDao;

    public LottoService() {
        lottoMachine = new LottoMachine();
        lottosDao = new LottosDao();
        turnDao = new TurnDao();
    }

    public void charge(final int money) {
        lottoMachine.charge(money);
    }

    public void buy(final Lotto lotto) {
        lottoMachine.buy();
        lottosDao.add(LottoDto.of(lotto), turnDao.findNext());
    }

    public boolean canBuy() {
        return lottoMachine.isRemainMoney();
    }

    public GameResult gameResult() {
        List<Lotto> lottos = new ArrayList<>();
        for (LottoDto lotto : getLottos()) {
            lottos.add(lotto.lottoValue());
        }
        return GameResult.of(lottos);
    }

    public List<LottoDto> getLottos() {
        return lottosDao.findAllByTurn(turnDao.findNext());
    }

    public void vacateMoney() {
        lottoMachine.vacate();
    }

    public void deleteAll() {
        lottosDao.deleteAll();
    }

    public List<LottoDto> findAllByTurn(final int turn) {
        return lottosDao.findAllByTurn(turn);
    }
}
