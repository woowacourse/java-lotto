package lotto.domain;

import lotto.LottoDto;
import lotto.dao.LottosDao;
import lotto.dao.TurnDao;
import lotto.util.LottoDtoConverter;

import java.util.List;

public class LottoService {
    private final LottoMachine lottoMachine;
    private final LottosDao lottosDao;

    public LottoService() {
        lottoMachine = new LottoMachine();
        lottosDao = new LottosDao();
    }

    public void charge(final int money) {
        lottoMachine.charge(money);
    }

    public void buy(final Lotto lotto) {
        lottoMachine.buy();
        LottoDtoConverter converter = new LottoDtoConverter();
        lottosDao.add(converter.convertLottoToDto(lotto), new TurnDao().findNext());
    }

    public boolean canBuy() {
        return lottoMachine.isRemainMoney();
    }

    public GameResult gameResult() {
        LottoDtoConverter converter = new LottoDtoConverter();
        List<Lotto> lottos = converter.convertDtoToLottos(getLottos());
        return GameResult.of(lottos);
    }

    public List<LottoDto> getLottos() {
        return lottosDao.findAllByTurn(new TurnDao().findNext());
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
