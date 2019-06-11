package lotto.dao;

import lotto.domain.Lotto;
import lotto.util.LottoDtoConverter;
import lotto.domain.LottoFactory;
import lotto.LottoDto;
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
    private static final LottosDao TURN_DAO;
    private static final int TEST_TURN = 2;

    static {
        TURN_DAO = new LottosDao();
        LOTTOS = new ArrayList<>();
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Lotto lotto = new LottoFactory().create(numbers);
        LOTTO_DTO = new LottoDtoConverter().convertLottoToDto(lotto);

        LOTTOS.add(LOTTO_DTO);
        LOTTOS.add(LOTTO_DTO);
        LOTTOS.add(LOTTO_DTO);

    }

    @BeforeEach
    public void setUp() {
        TURN_DAO.deleteAll();
        TURN_DAO.addAll(LOTTOS, TEST_TURN);
    }

    @Test
    public void add() {
        TURN_DAO.add(LOTTO_DTO, 1);
    }
    @Test
    public void addAll() {
        TURN_DAO.addAll(LOTTOS, 1);
        List<LottoDto> actual = TURN_DAO.findAllByTurn(1);

        assertEquals(LOTTOS, actual);
    }

    @Test
    public void findByTurn() {
        List<LottoDto> actual = TURN_DAO.findAllByTurn(TEST_TURN);

        assertEquals(LOTTOS, actual);
    }

    @Test
    public void deleteAll() {
        TURN_DAO.deleteAll();
        assertEquals( 0, TURN_DAO.findAllByTurn(TEST_TURN).size());
    }

    @AfterEach
    public void tearDown() {
        TURN_DAO.deleteAll();
    }
}