package com.woowacourse.lotto.service;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.WinningAggregator;
import com.woowacourse.lotto.domain.WinningLotto;
import com.woowacourse.lotto.persistence.dao.AggregationDao;
import com.woowacourse.lotto.persistence.dao.LottoDao;
import com.woowacourse.lotto.persistence.dao.WinningLottoDao;
import com.woowacourse.lotto.persistence.dto.AggregationDto;
import com.woowacourse.lotto.persistence.dto.LottoDto;
import com.woowacourse.lotto.persistence.dto.WinningLottoDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class LottoService {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "wtc_lotto_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private LottoDao lottoDao;
    private WinningLottoDao winningLottoDao;
    private AggregationDao aggregationDao;

    private DataSource createDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl("jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        ds.setUser(DB_USER);
        ds.setPassword(DB_PASSWORD);
        ds.setDatabaseName(DB_NAME);
        return ds;
    }

    public LottoService() {
        DataSource ds = createDataSource();
        lottoDao = LottoDao.getInstance(ds);
        winningLottoDao = WinningLottoDao.getInstance(ds);
        aggregationDao = AggregationDao.getInstance(ds);
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

    public List<LottoDto> findLottosByAggregationId(long aggregationId) {
        try {
            return lottoDao.findByAggregationId(aggregationId);
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

    public WinningLottoDto findWinningLottoByAggregationId(long aggregationId) {
        try {
            return winningLottoDao.findByAggregationId(aggregationId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 당첨 로또를 찾을 수 없습니다."));
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

    public List<AggregationDto> findLatestNAggregation(int n) {
        try {
            return aggregationDao.findLatestN(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
