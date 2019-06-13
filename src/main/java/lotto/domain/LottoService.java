package lotto.domain;

import lotto.dto.LottoDto;
import lotto.dao.LottosDao;
import lotto.dao.TurnDao;
import lotto.util.RandomNumbersGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final LottoService INSTANCE = new LottoService();
    private static final int START_COUNT = 0;

    private final LottoMachine lottoMachine;
    private final LottosDao lottosDao;
    private final TurnDao turnDao;

    private LottoService() {
        lottoMachine = new LottoMachine();
        lottosDao = LottosDao.getInstance();
        turnDao = TurnDao.getInstance();
    }

    public static LottoService getInstance() {
        return INSTANCE;
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

    public GameResultMatcher gameResult() {
        List<Lotto> lottos = new ArrayList<>();
        for (LottoDto lotto : getLottos()) {
            lottos.add(lotto.lottoValue());
        }
        return GameResultMatcher.of(lottos);
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

    public int assignManualCount(final List<Lotto> lottos) {
        int manualCount = START_COUNT;
        for (final Lotto lotto : lottos) {
            manualCount = calculateManualCount(manualCount, lotto);
        }
        return manualCount;
    }

    private int calculateManualCount(int manualCount, final Lotto lotto) {
        if (lottoMachine.isRemainMoney()) {
            buy(lotto);
            manualCount++;
        }
        return manualCount;
    }

    public int assignAutoPurchaseCount() {
        RandomNumbersGenerator generator = RandomNumbersGenerator.getInstance();
        int autoPurchaseCount = START_COUNT;
        for (; lottoMachine.isRemainMoney(); autoPurchaseCount++) {
            Lotto lotto = new LottoFactory().create(generator.generate());
            buy(lotto);
        }
        return autoPurchaseCount;
    }
}
