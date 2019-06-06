package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.persistence.dao.ConnectionFactory;
import com.woowacourse.lotto.persistence.dao.LottoDao;
import com.woowacourse.lotto.persistence.dto.LottoDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private LottoDao lottoDao;

    public LottoService() {
        lottoDao = new LottoDao(ConnectionFactory.getConnection());
    }

    public LottoDto addLotto(Lotto lotto) {
        try {
            LottoDto dto = convertLottoToDto(lotto);
            long id = lottoDao.addLotto(dto);
            return lottoDao.findById(id).get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LottoDto convertLottoToDto(Lotto lotto) {
        LottoDto dto = new LottoDto();
        List<Integer> numList = new ArrayList<>();
        lotto.forEachNums(numList::add);
        dto.setNumber0(numList.get(0));
        dto.setNumber1(numList.get(1));
        dto.setNumber2(numList.get(2));
        dto.setNumber3(numList.get(3));
        dto.setNumber4(numList.get(4));
        dto.setNumber5(numList.get(5));
        dto.setPrice(Lotto.UNIT_PRICE);
        return dto;
    }
}
