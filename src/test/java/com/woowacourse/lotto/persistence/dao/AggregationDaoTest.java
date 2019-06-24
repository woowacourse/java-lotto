package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.persistence.TestDataSourceFactory;
import com.woowacourse.lotto.persistence.dto.AggregationDto;
import com.woowacourse.lotto.persistence.dto.LottoDto;
import com.woowacourse.lotto.persistence.dto.WinningLottoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AggregationDaoTest {
    private static final AggregationDto TEMP_AGGREGATION;
    private static final WinningLottoDto TEMP_WINNING_LOTTO;
    private static final LottoDto TEMP_LOTTO;

    private AggregationDao aggregationDao;
    private WinningLottoDao winningLottoDao;
    private LottoDao lottoDao;
    private Queue<Long> aggregationIdsToDelete = new LinkedList<>();
    private Queue<Long> winningLottoIdsToDelete = new LinkedList<>();
    private Queue<Long> lottoIdsToDelete = new LinkedList<>();


    static {
        TEMP_WINNING_LOTTO = new WinningLottoDto();
        WinningLottoDto.of(0, 1, 2, 3, 4, 5,
            6, 7, null);
        TEMP_AGGREGATION = AggregationDto.of(0, 9999, 0, 0, 0, 1, 2, 5,
            LottoResult.FOURTH.getPrizeMoney() + LottoResult.FIFTH.getPrizeMoney() * 2 + LottoResult.NONE.getPrizeMoney() * 5,
            null, null, null);
        TEMP_LOTTO = new LottoDto();
        LottoDto.of(0, 1, 2, 3, 4, 5, 6, Lotto.UNIT_PRICE, null);
    }

    @BeforeEach
    void setup() throws Exception {
        DataSource ds = TestDataSourceFactory.createDataSource();
        aggregationDao = AggregationDao.getInstance(ds);
        winningLottoDao = WinningLottoDao.getInstance(ds);
        lottoDao = LottoDao.getInstance(ds);
        long insertedWinningLottoId = winningLottoDao.addWinningLotto(TEMP_WINNING_LOTTO);
        long insertedLottoId = lottoDao.addLotto(TEMP_LOTTO);
        winningLottoIdsToDelete.add(insertedWinningLottoId);
        lottoIdsToDelete.add(insertedLottoId);
        aggregationIdsToDelete.add(aggregationDao.addAggregation(TEMP_AGGREGATION, insertedWinningLottoId, Arrays.asList(insertedLottoId)));
    }

    @AfterEach
    void cleanup() throws Exception {
        while (!aggregationIdsToDelete.isEmpty()) {
            aggregationDao.deleteById(aggregationIdsToDelete.poll());
        }
        while (!winningLottoIdsToDelete.isEmpty()) {
            winningLottoDao.deleteById(winningLottoIdsToDelete.poll());
        }
        while (!lottoIdsToDelete.isEmpty()) {
            lottoDao.deleteById(lottoIdsToDelete.poll());
        }
    }

    @Test
    void insert() {
        assertThat(aggregationIdsToDelete.peek()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findById() throws Exception {
        Optional<AggregationDto> maybeFound = aggregationDao.findById(aggregationIdsToDelete.peek());
        assertThat(maybeFound.isPresent()).isTrue();
        AggregationDto found = maybeFound.get();
        assertThat(found.getCntFirst()).isEqualTo(TEMP_AGGREGATION.getCntFirst());
        assertThat(found.getCntSecond()).isEqualTo(TEMP_AGGREGATION.getCntSecond());
        assertThat(found.getCntThird()).isEqualTo(TEMP_AGGREGATION.getCntThird());
        assertThat(found.getCntFourth()).isEqualTo(TEMP_AGGREGATION.getCntFourth());
        assertThat(found.getCntFifth()).isEqualTo(TEMP_AGGREGATION.getCntFifth());
        assertThat(found.getCntNone()).isEqualTo(TEMP_AGGREGATION.getCntNone());
        assertThat(found.getPrizeMoneySum()).isEqualTo(TEMP_AGGREGATION.getPrizeMoneySum());
    }

    @Test
    void findLatestN() throws Exception {
        // Test data * 2
        int latestRound = aggregationDao.findLatestRound();
        LottoDto lottoDto1 = LottoDto.of(0, 1, 2, 3, 4, 5, 6, Lotto.UNIT_PRICE, null);
        WinningLottoDto winning1 = WinningLottoDto.of(0, 1, 2, 3, 4,
            5, 6, 7, null);
        lottoDto1.setId(lottoDao.addLotto(lottoDto1));
        winning1.setId(winningLottoDao.addWinningLotto(winning1));
        long aggId1 = aggregationDao.addAggregation(AggregationDto.of(0, ++latestRound, 1, 0, 0, 0, 0, 0,
            2_000_000_000L, Arrays.asList(lottoDto1), winning1, null), winning1.getId(), Arrays.asList(lottoDto1.getId()));
        LottoDto lottoDto2 = LottoDto.of(0, 1, 2, 3, 4, 5, 6, Lotto.UNIT_PRICE, null);
        WinningLottoDto winning2 = WinningLottoDto.of(0, 7, 1, 2, 3,
            4, 5, 6, null);
        lottoDto2.setId(lottoDao.addLotto(lottoDto2));
        winning2.setId(winningLottoDao.addWinningLotto(winning2));
        long aggId2 = aggregationDao.addAggregation(AggregationDto.of(0, ++latestRound, 0, 1, 0, 0, 0, 0,
            2_000_000_000L, Arrays.asList(lottoDto2), winning2, null), winning2.getId(), Arrays.asList(lottoDto2.getId()));
        List<AggregationDto> result = aggregationDao.findLatestN(2);
        assertThat(result).hasSize(2);
        assertThat(result.get(1).getCntFirst()).isEqualTo(1);
        assertThat(result.get(0).getCntSecond()).isEqualTo(1);
        aggregationIdsToDelete.add(aggId1);
        aggregationIdsToDelete.add(aggId2);
        lottoIdsToDelete.add(lottoDto1.getId());
        lottoIdsToDelete.add(lottoDto2.getId());
        winningLottoIdsToDelete.add(winning1.getId());
        winningLottoIdsToDelete.add(winning2.getId());
    }

    @Test
    void findLatestRound() throws Exception {
        assertThat(aggregationDao.findLatestRound()).isEqualTo(TEMP_AGGREGATION.getLottoRound());
    }

    @Test
    void deleteById() throws Exception {
        long inserted = aggregationIdsToDelete.peek();
        assertThat(aggregationDao.deleteById(inserted)).isEqualTo(1);
        assertThat(aggregationDao.findById(inserted).isPresent()).isFalse();
    }
}
