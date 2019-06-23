package lotto.domain.service;

import lotto.domain.dao.LottoDAO;
import lotto.domain.dto.LottoDTO;
import lotto.domain.model.Number;
import lotto.domain.utils.ShuffledNumberGenerator;

import java.util.List;


public class AutoLottoService {
    public void addLotto(final int round, final String inputPurchaseLottoCount, final String inputManualLottoCount) {
        LottoDAO lottoDao = new LottoDAO();
        LottoDTO lottoDTO = new LottoDTO();
        int purchaseLottoCount = Integer.parseInt(inputPurchaseLottoCount);
        int manualLottoCount = Integer.parseInt(inputManualLottoCount);
        int autoLottoAvailableSize = purchaseLottoCount - manualLottoCount;

        for (int i = 0; i < autoLottoAvailableSize; i++) {
            List<Number> lotto = ShuffledNumberGenerator.getShuffledNumbers();
            lottoDTO.setRound(round);
            lottoDTO.setFirstNum(lotto.get(0));
            lottoDTO.setSecondNum(lotto.get(1));
            lottoDTO.setThirdNum(lotto.get(2));
            lottoDTO.setForthNum(lotto.get(3));
            lottoDTO.setFifthNum(lotto.get(4));
            lottoDTO.setSixthNum(lotto.get(5));
            lottoDao.addLotto(lottoDTO);
        }
    }
}
