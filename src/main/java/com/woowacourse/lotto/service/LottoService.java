package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.WinningAggregator;
import com.woowacourse.lotto.domain.WinningLotto;
import com.woowacourse.lotto.persistence.DataSourceFactory;
import com.woowacourse.lotto.persistence.dao.AggregationDao;
import com.woowacourse.lotto.persistence.dao.LottoDao;
import com.woowacourse.lotto.persistence.dao.WinningLottoDao;
import com.woowacourse.lotto.persistence.dto.AggregationDto;
import com.woowacourse.lotto.persistence.dto.LottoDto;
import com.woowacourse.lotto.persistence.dto.WinningLottoDto;

import javax.sql.DataSource;
import java.util.List;

public class LottoService {
    private LottoDao lottoDao;
    private WinningLottoDao winningLottoDao;
    private AggregationDao aggregationDao;

    public LottoService() {
        DataSource ds = DataSourceFactory.createDataSource();
        lottoDao = LottoDao.getInstance(ds);
        winningLottoDao = WinningLottoDao.getInstance(ds);
        aggregationDao = AggregationDao.getInstance(ds);
    }

    public LottoDto addLotto(Lotto lotto) {
        LottoDto dto = lotto.toDto();
        long id = lottoDao.addLotto(dto);
        return lottoDao.findById(id).get();
    }

    public LottoDto findLottoById(long lottoId) {
        return lottoDao.findById(lottoId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 로또 id입니다."));
    }

    public List<LottoDto> findLottosByAggregationId(long aggregationId) {
        return lottoDao.findByAggregationId(aggregationId);
    }

    private WinningLottoDto addWinningLotto(WinningLotto winningLotto) {
        WinningLottoDto dto = winningLotto.toDto();
        long id = winningLottoDao.addWinningLotto(dto);
        return winningLottoDao.findById(id).get();
    }

    public WinningLottoDto findWinningLottoByAggregationId(long aggregationId) {
        return winningLottoDao.findByAggregationId(aggregationId)
            .orElseThrow(() -> new IllegalArgumentException("일치하는 당첨 로또를 찾을 수 없습니다."));
    }

    public AggregationDto addAggregation(WinningAggregator aggregator, WinningLotto winningLotto, List<Long> lottoIds) {
        AggregationDto dto = aggregator.toDto();
        dto.setLottoRound(aggregationDao.findLatestRound() + 1);
        long id = aggregationDao.addAggregation(dto, addWinningLotto(winningLotto).getId(), lottoIds);
        return aggregationDao.findById(id).get();
    }

    public AggregationDto findAggregationById(long id) {
        return aggregationDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다"));
    }

    public List<AggregationDto> findLatestNAggregation(int n) {
        return aggregationDao.findLatestN(n);
    }
}
