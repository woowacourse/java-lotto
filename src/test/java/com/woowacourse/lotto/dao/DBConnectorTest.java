package com.woowacourse.lotto.dao;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectorTest {
	@Test
	void connention() {
		Connection connection = new DBConnector().getConnection();
		assertNotNull(connection);
	}
}
