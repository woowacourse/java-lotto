package lotto.domain.service;

import lotto.domain.dao.LottoDAO;
import lotto.domain.model.Lotto;
import lotto.domain.model.NumberSet;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManualLottoService {

    private static final String NUMBER_SEPARATOR = ",";
    private static final String LOTTOS_SEPARATOR = "/";

    public void addLotto(final String manualLotto, final String round) {
        LottoDAO lottoDao = new LottoDAO();
        Stream.of(manualLotto.split(LOTTOS_SEPARATOR))
                .map(lottoNumbers ->
                        Stream.of(lottoNumbers.split(NUMBER_SEPARATOR))
                                .map(number ->
                                        NumberSet.of(Integer.parseInt(number))
                                ).collect(Collectors.toList())
                ).forEach(lotto ->
                lottoDao.addLotto(new Lotto(lotto), Integer.parseInt(round))
        );
    }
}
