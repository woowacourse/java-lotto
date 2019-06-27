package lotto.domain.service;

import lotto.domain.dao.LottoDAO;
import lotto.domain.dto.LottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.utils.ShuffledNumberGenerator;

import java.util.List;

public class AutoLottoService {

    private static final AutoLottoService INSTANCE = new AutoLottoService();

    private AutoLottoService() {
    }

    public static AutoLottoService getInstance() {
        return INSTANCE;
    }

    public void addLotto(final int round, final String inputPurchaseLottoCount, final String inputManualLottoCount) {
        LottoDTO lottoDTO = new LottoDTO();
        int purchaseLottoCount = Integer.parseInt(inputPurchaseLottoCount);
        int manualLottoCount = Integer.parseInt(inputManualLottoCount);
        int autoLottoAvailableSize = purchaseLottoCount - manualLottoCount;

        for (int i = 0; i < autoLottoAvailableSize; i++) {
            List<Number> lotto = ShuffledNumberGenerator.getShuffledNumbers();
            lottoDTO.setRound(round);
            lottoDTO.setLotto(new Lotto(lotto));
            LottoDAO.getInstance().addLotto(lottoDTO);
        }
    }
}
