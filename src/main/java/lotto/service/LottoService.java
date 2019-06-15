package lotto.service;

import lotto.dao.LottoDAO;
import lotto.dao.RoundDAO;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.dto.LottosDTO;
import lotto.util.StringUtil;

import java.util.List;

public class LottoService {
    private static final String NEXTLINE = "\r\n";

    private final RoundDAO roundDAO = new RoundDAO();
    private final LottoDAO lottoDAO = new LottoDAO();

    public LottosDTO.Create createLottos(String manualLottoNumbers) {
        List<String> manualLottos = StringUtil.convertToList(manualLottoNumbers, NEXTLINE);
        int countOfPurchase = roundDAO.findAmountByRound(roundDAO.findMaxRound()) / 1000;
        List<Lotto> lottos = LottoFactory.createLottos(manualLottos, countOfPurchase);

        lottoDAO.addLottos(lottos);

        return new LottosDTO.Create(lottos);
    }

    public LottosDTO.Create findLottosByRound(int round) {
        List<Lotto> lottos = lottoDAO.findLottosByRound(round);
        return new LottosDTO.Create(lottos);
    }
}
