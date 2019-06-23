package lotto.domain.service;

import lotto.domain.dao.LottoDAO;
import lotto.domain.dto.LottoDTO;
import lotto.domain.model.NumberSet;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManualLottoService {

    private static final String NUMBER_SEPARATOR = ",";
    private static final String LOTTOS_SEPARATOR = "/";

    public void addLotto(int round, String manualLotto) {
        LottoDAO lottoDAO = new LottoDAO();
        LottoDTO lottoDTO = new LottoDTO();
        Stream.of(manualLotto.split(LOTTOS_SEPARATOR))
                .map(lottoNumbers ->
                        Stream.of(lottoNumbers.split(NUMBER_SEPARATOR))
                                .map(number ->
                                        NumberSet.of(Integer.parseInt(number))
                                ).collect(Collectors.toList())
                ).forEach(lotto -> {
                    lottoDTO.setRound(round);
                    lottoDTO.setFirstNum(lotto.get(0));
                    lottoDTO.setSecondNum(lotto.get(1));
                    lottoDTO.setThirdNum(lotto.get(2));
                    lottoDTO.setForthNum(lotto.get(3));
                    lottoDTO.setFifthNum(lotto.get(4));
                    lottoDTO.setSixthNum(lotto.get(5));
                    lottoDAO.addLotto(lottoDTO);
                }
        );
    }
}
