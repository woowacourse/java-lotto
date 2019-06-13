package lotto.domain.service;

import lotto.domain.dao.WinningLottoDAO;
import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;

import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningLottoService {

    private static final String LOTTO_SEPARATOR = ",";

    public void addWinningLotto(final String inputRound, final String inputBonusNumber, final String inputWinningLotto) throws SQLException {
        int round = Integer.parseInt(inputRound);
        Number bonusNumber = NumberSet.of(Integer.parseInt(inputBonusNumber));
        Lotto winningLotto = new Lotto(Stream.of(inputWinningLotto.split(LOTTO_SEPARATOR))
                .map(number -> NumberSet.of(Integer.parseInt(number)))
                .collect(Collectors.toList()));

        WinningLottoDTO winningLottoDTO = new WinningLottoDTO();
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
        winningLottoDTO.setRound(round);
        winningLottoDTO.setBonusNumber(bonusNumber);
        winningLottoDTO.setWinningLotto(winningLotto);
        winningLottoDAO.addWinningLotto(winningLottoDTO);
    }
}
