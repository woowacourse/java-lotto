package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.WinningAggregator;
import com.woowacourse.lotto.domain.WinningLotto;
import com.woowacourse.lotto.persistence.dao.AggregationDao;
import com.woowacourse.lotto.persistence.dao.ConnectionFactory;
import com.woowacourse.lotto.persistence.dao.LottoDao;
import com.woowacourse.lotto.persistence.dao.WinningLottoDao;
import com.woowacourse.lotto.persistence.dto.AggregationDto;
import com.woowacourse.lotto.persistence.dto.LottoDto;
import com.woowacourse.lotto.persistence.dto.WinningLottoDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LottoService {
    private LottoDao lottoDao;
    private WinningLottoDao winningLottoDao;
    private AggregationDao aggregationDao;

    public LottoService() {
        Connection conn = ConnectionFactory.getConnection();
        lottoDao = new LottoDao(conn);
        winningLottoDao = new WinningLottoDao(conn);
        aggregationDao = new AggregationDao(conn);
    }

    public LottoDto addLotto(Lotto lotto) {
        try {
            LottoDto dto = lotto.toDto();
            long id = lottoDao.addLotto(dto);
            return lottoDao.findById(id).get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LottoDto findLottoById(long lottoId) {
        try {
            return lottoDao.findById(lottoId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 로또 id입니다."));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private WinningLottoDto addWinningLotto(WinningLotto winningLotto) {
        try {
            WinningLottoDto dto = winningLotto.toDto();
            long id = winningLottoDao.addWinningLotto(dto);
            return winningLottoDao.findById(id).get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AggregationDto addAggregation(WinningAggregator aggregator, WinningLotto winningLotto, List<Long> lottoIds) {
        try {
            AggregationDto dto = aggregator.toDto();
            dto.setLottoRound(aggregationDao.findLatestRound() + 1);
            long id = aggregationDao.addAggregation(dto, addWinningLotto(winningLotto).getId(), lottoIds);
            return aggregationDao.findById(id).get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AggregationDto findAggregationById(long id) {
        try {
            return aggregationDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
