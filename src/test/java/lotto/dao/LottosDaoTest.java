package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.dto.LottoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottosDaoTest {
    private static final List<LottoDto> LOTTOS;
    private static final LottoDto LOTTO_DTO;
    private static final LottosDao LOTTOS_DAO;
    private static final int TEST_ROUND = 2;

    static {
        LOTTOS_DAO = LottosDao.getInstance();
        LOTTOS = new ArrayList<>();
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Lotto lotto = new LottoFactory().create(numbers);
        LOTTO_DTO = LottoDto.of(lotto);

        LOTTOS.add(LOTTO_DTO);
        LOTTOS.add(LOTTO_DTO);
        LOTTOS.add(LOTTO_DTO);

    }

    @BeforeEach
    public void setUp() {
        LOTTOS_DAO.deleteAll();
        LOTTOS_DAO.addAll(LOTTOS, TEST_ROUND);
    }

    @Test
    public void add() {
        LOTTOS_DAO.add(LOTTO_DTO, 1);
    }
    @Test
    public void addAll() {
        LOTTOS_DAO.addAll(LOTTOS, 1);
        List<LottoDto> actual = LOTTOS_DAO.findAllByRound(1);

        assertEquals(LOTTOS, actual);
    }

    @Test
    public void findByRound() {
        List<LottoDto> actual = LOTTOS_DAO.findAllByRound(TEST_ROUND);

        assertEquals(LOTTOS, actual);
    }

    @Test
    public void deleteAll() {
        LOTTOS_DAO.deleteAll();
        assertEquals( 0, LOTTOS_DAO.findAllByRound(TEST_ROUND).size());
    }

    @AfterEach
    public void tearDown() {
        LOTTOS_DAO.deleteAll();
    }
}