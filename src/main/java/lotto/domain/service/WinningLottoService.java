package lotto.domain.service;

import lotto.domain.dao.WinningLottoDAO;
import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningLottoService {

    private static final String LOTTO_SEPARATOR = ",";
    private static final int NEXT_ROUND = 1;

    public void addWinningLotto(final int round, final String bonusNumber, final String winningLotto) {
        List<Number> winningLottoNumbers = Stream.of(winningLotto.split(LOTTO_SEPARATOR))
                .map(number -> NumberSet.of(Integer.parseInt(number)))
                .collect(Collectors.toList());

        WinningLottoDTO winningLottoDTO = new WinningLottoDTO();
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
        winningLottoDTO.setRound(round);
        winningLottoDTO.setFirstNum(winningLottoNumbers.get(0));
        winningLottoDTO.setSecondNum(winningLottoNumbers.get(1));
        winningLottoDTO.setThirdNum(winningLottoNumbers.get(2));
        winningLottoDTO.setForthNum(winningLottoNumbers.get(3));
        winningLottoDTO.setFifthNum(winningLottoNumbers.get(4));
        winningLottoDTO.setSixthNum(winningLottoNumbers.get(5));
        winningLottoDTO.setBonusNum(NumberSet.of(Integer.parseInt(bonusNumber)));
        winningLottoDAO.addWinningLotto(winningLottoDTO);
    }

    public int getNewRound() {
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
        return winningLottoDAO.getLatestRound() + NEXT_ROUND;
    }
}
