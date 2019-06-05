package com.woowacourse.lotto.domain.dao;

import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.dto.AggregationDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AggregationDaoTest {
    private static final AggregationDto TEMP_AGGREGATION;

    private AggregationDao dao;
    private long lastGeneratedId;

    static {
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
    }

    @BeforeEach
    void setup() {
        dao = new AggregationDao(ConnectionFactory.getConnection());
    }

    @AfterEach
    void cleanup() throws Exception {
        dao.deleteById(lastGeneratedId);
    }

    @Test
    void insert() throws Exception {
        long insertedId = dao.addAggregation(TEMP_AGGREGATION);
        lastGeneratedId = insertedId;
        assertThat(insertedId).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findById() throws Exception {
        long insertedId = dao.addAggregation(TEMP_AGGREGATION);
        lastGeneratedId = insertedId;
        Optional<AggregationDto> maybeFound = dao.findById(insertedId);
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
        long insertedId = dao.addAggregation(TEMP_AGGREGATION);
        lastGeneratedId = insertedId;
        assertThat(dao.deleteById(insertedId)).isEqualTo(1);
        assertThat(dao.findById(insertedId).isPresent()).isFalse();
    }
}
