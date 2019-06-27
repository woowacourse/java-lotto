package lotto.domain.service;

import lotto.domain.dao.LottoDAO;
import lotto.domain.dto.LottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManualLottoService {

    private static final String NUMBER_SEPARATOR = ",";
    private static final String LOTTOS_SEPARATOR = "/";

    private static final ManualLottoService INSTANCE = new ManualLottoService();

    private ManualLottoService() {
    }

    public static ManualLottoService getInstance() {
        return INSTANCE;
    }

    public void addLotto(int round, String manualLotto) {
        LottoDTO lottoDTO = new LottoDTO();
        for (String lottoNumbers : manualLotto.split(LOTTOS_SEPARATOR)) {
            List<Number> lotto = Stream.of(lottoNumbers.split(NUMBER_SEPARATOR))
                    .map(number ->
                            NumberSet.of(Integer.parseInt(number))
                    ).collect(Collectors.toList());
            lottoDTO.setRound(round);
            lottoDTO.setLotto(new Lotto(lotto));
            LottoDAO.getInstance().addLotto(lottoDTO);
        }
    }
}
