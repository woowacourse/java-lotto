package lotto.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundDaoTest {
    private static final RoundDao ROUND_DAO;

    static {
        ROUND_DAO = RoundDao.getInstance();
    }

    @BeforeEach
    public void setUp() {
        ROUND_DAO.deleteAll();
    }

    @Test
    public void getLatestTurnIfNull() {
        assertEquals(1, ROUND_DAO.findNext());
    }

    @Test
    public void findLast() {
        ROUND_DAO.add();
        assertEquals(2, ROUND_DAO.findNext());
        ROUND_DAO.add();
        assertEquals(3, ROUND_DAO.findNext());
    }

    @Test
    public void findAllTurn() {
        ROUND_DAO.add();
        ROUND_DAO.add();
        ROUND_DAO.add();

        final List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, ROUND_DAO.findAll());
    }

    @AfterEach
    public void tearDown() {
        ROUND_DAO.deleteAll();
    }
}