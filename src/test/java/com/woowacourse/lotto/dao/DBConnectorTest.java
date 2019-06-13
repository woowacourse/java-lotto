package com.woowacourse.lotto.dao;

import java.sql.Connection;
import java.util.*;

import com.woowacourse.lotto.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectorTest {
	private DBConnector DBConnector;
	private WinningLotto winningLotto;

	@BeforeEach
	void init() {
		DBConnector = new DBConnector();
		List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
		winningLotto = new WinningLotto(numbers, 7);
	}

	@Test
	void connention() {
		Connection connection = DBConnector.getConnection();
		assertNotNull(connection);
	}
}
