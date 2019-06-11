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
import java.util.List;
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
        TEMP_AGGREGATION.setLottoRound(999);
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
    void findLatestN() throws Exception {
        // Test data * 2
        int latestRound = aggregationDao.findLatestRound();
        LottoDto lottoDto1 = createLottoDto(1, 2, 3, 4, 5, 6);
        WinningLottoDto winning1 = createWinningLotto(1, 2, 3, 4, 5, 6, 7);
        long lottoId1 = lottoDao.addLotto(lottoDto1);
        long winningId1 = winningLottoDao.addWinningLotto(winning1);
        long aggId1 = aggregationDao.addAggregation(createAggregation(++latestRound, 1, 0, 0, 0, 0, 0, 2_000_000_000L),
            winningId1, Arrays.asList(lottoId1));
        LottoDto lottoDto2 = createLottoDto(1, 2, 3, 4, 5, 6);
        WinningLottoDto winning2 = createWinningLotto(7, 1, 2, 3, 4, 5, 6);
        long lottoId2 = lottoDao.addLotto(lottoDto2);
        long winningId2 = winningLottoDao.addWinningLotto(winning2);
        long aggId2 = aggregationDao.addAggregation(createAggregation(++latestRound, 0, 1, 0, 0, 0, 0, 2_000_000_000L),
            winningId2, Arrays.asList(lottoId2));
        List<AggregationDto> result = aggregationDao.find(2);
        assertThat(result).hasSize(2);
        assertThat(result.get(1).getCntFirst()).isEqualTo(1);
        assertThat(result.get(0).getCntSecond()).isEqualTo(1);
        aggregationDao.deleteById(aggId1);
        aggregationDao.deleteById(aggId2);
        lottoDao.deleteById(lottoId1);
        lottoDao.deleteById(lottoId2);
        winningLottoDao.deleteById(winningId1);
        winningLottoDao.deleteById(winningId2);
    }

    @Test
    void findLatestRound() throws Exception {
        assertThat(aggregationDao.findLatestRound()).isEqualTo(999);
    }

    @Test
    void deleteById() throws Exception {
        assertThat(aggregationDao.deleteById(lastGeneratedAggregationId)).isEqualTo(1);
        assertThat(aggregationDao.findById(lastGeneratedAggregationId).isPresent()).isFalse();
    }

    private static LottoDto createLottoDto(int number0, int number1, int number2, int number3, int number4, int number5) {
        LottoDto lotto = new LottoDto();
        lotto.setNumber0(number0);
        lotto.setNumber0(number1);
        lotto.setNumber0(number2);
        lotto.setNumber0(number3);
        lotto.setNumber0(number4);
        lotto.setNumber0(number5);
        lotto.setPrice(Lotto.UNIT_PRICE);
        return lotto;
    }

    private static AggregationDto createAggregation(int round, int first, int second, int third, int fourth, int fifth, int none, long prizeMoneySum) {
        AggregationDto aggregation = new AggregationDto();
        aggregation.setLottoRound(round);
        aggregation.setCntFirst(first);
        aggregation.setCntSecond(second);
        aggregation.setCntThird(third);
        aggregation.setCntFourth(fourth);
        aggregation.setCntFifth(fifth);
        aggregation.setCntNone(none);
        aggregation.setPrizeMoneySum(prizeMoneySum);
        return aggregation;
    }

    private static WinningLottoDto createWinningLotto(int number0, int number1, int number2, int number3, int number4, int number5, int bonus) {
        WinningLottoDto winningLotto = new WinningLottoDto();
        winningLotto.setWinningNumber0(number0);
        winningLotto.setWinningNumber1(number1);
        winningLotto.setWinningNumber2(number2);
        winningLotto.setWinningNumber3(number3);
        winningLotto.setWinningNumber4(number4);
        winningLotto.setWinningNumber5(number5);
        winningLotto.setWinningBonusNumber(bonus);
        return winningLotto;
    }
}
