package lotto.dao;

import lotto.DtoConverter;
import lotto.domain.LottoFactory;
import lotto.view.LottoDto;
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
    private static final LottosDao DAO;
    private static final int TEST_TURN = 2;

    static {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoFactory factory = new LottoFactory();
        DtoConverter converter = new DtoConverter();
        LOTTOS = new ArrayList<>();
        LOTTO_DTO = converter.convertLottoToDto(factory.create(numbers));

        LOTTOS.add(LOTTO_DTO);
        LOTTOS.add(LOTTO_DTO);
        LOTTOS.add(LOTTO_DTO);

        DAO = new LottosDao();
    }

    @BeforeEach
    public void setUp() {
        DAO.addAll(LOTTOS, TEST_TURN);
    }

    @Test
    public void add() {
        DAO.add(LOTTO_DTO, 1);
    }
    @Test
    public void addAll() {
        DAO.addAll(LOTTOS, 1);
        List<LottoDto> actual = DAO.findAllByTurn(1);

        assertEquals(LOTTOS, actual);
    }

    @Test
    public void findByTurn() {
        List<LottoDto> actual = DAO.findAllByTurn(TEST_TURN);

        assertEquals(LOTTOS, actual);
    }

    @Test
    public void deleteAll() {
        DAO.deleteAll();
        assertEquals( 0, DAO.findAllByTurn(TEST_TURN).size());
    }

    @AfterEach
    public void tearDown() {
        DAO.deleteAll();
    }
}