package lotto.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnDaoTest {
    private static final TurnDao turnDao;

    static {
        turnDao = new TurnDao();
    }

    @BeforeEach
    public void setUp() {
        turnDao.deleteAll();
    }

    @Test
    public void getLatestTurnIfNull() {
        assertEquals(1, turnDao.findNext());
    }

    @Test
    public void findLast() {
        turnDao.add();
        assertEquals(2, turnDao.findNext());
        turnDao.add();
        assertEquals(3, turnDao.findNext());
    }

    @Test
    public void findAllTurn() {
        turnDao.add();
        turnDao.add();
        turnDao.add();

        final List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, turnDao.findAll());
    }

    @AfterEach
    public void tearDown() {
        turnDao.deleteAll();
    }
}