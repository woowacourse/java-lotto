package lotto.domain;

import lotto.DtoConverter;
import lotto.dao.LottosDao;
import lotto.view.LottoDto;

import java.util.List;

public class LottoService {
    private final LottoMachine lottoMachine;
    private final Lottos lottos;
    private final LottosDao lottosDao;

    public LottoService() {
        lottoMachine = new LottoMachine();
        lottos = new Lottos();
        lottosDao = new LottosDao();
    }

    public void charge(final int money) {
        lottoMachine.charge(money);
    }

    public void buy(final Lotto lotto) {
        lottoMachine.buy();
        lottos.add(lotto);
        lottosDao.add(new DtoConverter().convertLottoToDto(lotto), 1);
    }

    public boolean canBuy() {
        return lottoMachine.isRemainMoney();
    }

    public LottoGameResult gameResult() {
        return LottoGameResult.of(lottos.getLottos());
    }

    public List<LottoDto> getLottos() {
        return lottosDao.findAllByTurn(1);
    }
}
