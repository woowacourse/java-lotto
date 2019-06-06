package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.persistence.dto.AggregationDto;
import com.woowacourse.lotto.persistence.dto.LottoDto;
import com.woowacourse.lotto.persistence.dto.WinningLottoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AggregationDaoTest {
    private static final AggregationDto TEMP_AGGREGATION;
    private static final WinningLottoDto TEMP_WINNING_LOTTO;
    private static final LottoDto TEMP_LOTTO;

    private AggregationDao aggregationDao;
    private WinningLottoDao winningLottoDao;
    private LottoDao lottoDao;
    private long lastGeneratedAggregationId;
    private long lastGeneratedWinningLottoId;
    private long lastGeneratedLottoId;


    static {
        TEMP_WINNING_LOTTO = new WinningLottoDto();
        TEMP_WINNING_LOTTO.setWinningNumber0(1);
        TEMP_WINNING_LOTTO.setWinningNumber1(2);
        TEMP_WINNING_LOTTO.setWinningNumber2(3);
        TEMP_WINNING_LOTTO.setWinningNumber3(4);
        TEMP_WINNING_LOTTO.setWinningNumber4(5);
        TEMP_WINNING_LOTTO.setWinningNumber5(6);
        TEMP_WINNING_LOTTO.setWinningBonusNumber(7);
        TEMP_AGGREGATION = new AggregationDto();
        TEMP_AGGREGATION.setCntFirst(0);
        TEMP_AGGREGATION.setCntSecond(0);
        TEMP_AGGREGATION.setCntThird(0);
        TEMP_AGGREGATION.setCntFourth(1);
        TEMP_AGGREGATION.setCntFifth(2);
        TEMP_AGGREGATION.setCntNone(5);
        TEMP_AGGREGATION.setPrizeMoneySum(LottoResult.FOURTH.getPrizeMoney() * 1 +
            LottoResult.FIFTH.getPrizeMoney() * 2 +
            LottoResult.NONE.getPrizeMoney() * 5);
        TEMP_LOTTO = new LottoDto();
        TEMP_LOTTO.setNumber0(1);
        TEMP_LOTTO.setNumber1(2);
        TEMP_LOTTO.setNumber2(3);
        TEMP_LOTTO.setNumber3(4);
        TEMP_LOTTO.setNumber4(5);
        TEMP_LOTTO.setNumber5(6);
        TEMP_LOTTO.setPrice(Lotto.UNIT_PRICE);
    }

    @BeforeEach
    void setup() throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        aggregationDao = new AggregationDao(conn);
        winningLottoDao = new WinningLottoDao(conn);
        lottoDao = new LottoDao(conn);
        lastGeneratedWinningLottoId = winningLottoDao.addWinningLotto(TEMP_WINNING_LOTTO);
        lastGeneratedLottoId = lottoDao.addLotto(TEMP_LOTTO);
        lastGeneratedAggregationId = aggregationDao.addAggregation(TEMP_AGGREGATION, lastGeneratedWinningLottoId, Arrays.asList(lastGeneratedLottoId));
    }

    @AfterEach
    void cleanup() throws Exception {
        aggregationDao.deleteById(lastGeneratedAggregationId);
        winningLottoDao.deleteById(lastGeneratedWinningLottoId);
        lottoDao.deleteById(lastGeneratedLottoId);
    }

    @Test
    void insert() {
        assertThat(lastGeneratedWinningLottoId).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findById() throws Exception {
        Optional<AggregationDto> maybeFound = aggregationDao.findById(lastGeneratedAggregationId);
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
    void deleteById() throws Exception {
        assertThat(aggregationDao.deleteById(lastGeneratedAggregationId)).isEqualTo(1);
        assertThat(aggregationDao.findById(lastGeneratedAggregationId).isPresent()).isFalse();
    }
}
