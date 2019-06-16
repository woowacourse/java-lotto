package com.woowacourse.lotto.persistence.dao;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.persistence.DataSourceFactory;
import com.woowacourse.lotto.persistence.TestDataSourceFactory;
import com.woowacourse.lotto.persistence.dto.AggregationDto;
import com.woowacourse.lotto.persistence.dto.LottoDto;
import com.woowacourse.lotto.persistence.dto.WinningLottoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
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
        TEMP_WINNING_LOTTO.setWinningNumber0(1);
        TEMP_WINNING_LOTTO.setWinningNumber1(2);
        TEMP_WINNING_LOTTO.setWinningNumber2(3);
        TEMP_WINNING_LOTTO.setWinningNumber3(4);
        TEMP_WINNING_LOTTO.setWinningNumber4(5);
        TEMP_WINNING_LOTTO.setWinningNumber5(6);
        TEMP_WINNING_LOTTO.setWinningBonusNumber(7);
        TEMP_AGGREGATION = new AggregationDto();
        TEMP_AGGREGATION.setLottoRound(9999);
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
        DataSource ds = new TestDataSourceFactory().createDataSource();
        aggregationDao = new AggregationDao(ds);
        winningLottoDao = new WinningLottoDao(ds);
        lottoDao = new LottoDao(ds);
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
        List<AggregationDto> result = aggregationDao.findLatestN(2);
        assertThat(result).hasSize(2);
        assertThat(result.get(1).getCntFirst()).isEqualTo(1);
        assertThat(result.get(0).getCntSecond()).isEqualTo(1);
        aggregationIdsToDelete.add(aggId1);
        aggregationIdsToDelete.add(aggId2);
        lottoIdsToDelete.add(lottoId1);
        lottoIdsToDelete.add(lottoId2);
        winningLottoIdsToDelete.add(winningId1);
        winningLottoIdsToDelete.add(winningId2);
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
