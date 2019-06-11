package lotto.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnDaoTest {
    private static final TurnDao DAO;

    static {
        DAO = new TurnDao();
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void getLatestTurnIfNull() {
        assertEquals(1, DAO.findNext());
    }

    @Test
    public void findLast() {
        DAO.add();
        assertEquals(2, DAO.findNext());
        DAO.add();
        assertEquals(3, DAO.findNext());
    }

    @Test
    public void findAllTurn() {
        DAO.add();
        DAO.add();
        DAO.add();

        final List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, DAO.findAll());
    }

    @AfterEach
    public void tearDown() {
        DAO.deleteAll();
    }
}