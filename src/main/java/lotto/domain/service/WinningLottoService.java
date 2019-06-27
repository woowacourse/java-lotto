package lotto.domain.service;

import lotto.domain.dao.WinningLottoDAO;
import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningLottoService {

    private static final String LOTTO_SEPARATOR = ",";
    private static final int NEXT_ROUND = 1;
    private static final WinningLottoService INSTANCE = new WinningLottoService();

    private WinningLottoService() {
    }

    public static WinningLottoService getInstance() {
        return INSTANCE;
    }

    public void addWinningLotto(final int round, final String bonusNumber, final String winningLotto) {
        List<Number> winningLottoNumbers = Stream.of(winningLotto.split(LOTTO_SEPARATOR))
                .map(number -> NumberSet.of(Integer.parseInt(number)))
                .collect(Collectors.toList());

        WinningLottoDTO winningLottoDTO = new WinningLottoDTO();
        winningLottoDTO.setRound(round);
        winningLottoDTO.setWinningLotto(new Lotto(winningLottoNumbers));
        winningLottoDTO.setBonusNum(NumberSet.of(Integer.parseInt(bonusNumber)));
        WinningLottoDAO.getInstance().addWinningLotto(winningLottoDTO);
    }

    public int getNewRound() {
        return WinningLottoDAO.getInstance().getLatestRound() + NEXT_ROUND;
    }
}
