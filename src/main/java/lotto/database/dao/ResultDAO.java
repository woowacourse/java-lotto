package lotto.database.dao;

import lotto.dto.ResultDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultDAO {
    private final Connection connection;

    public ResultDAO(final Connection connection) {
        this.connection = connection;
    }

    public void addResult(final ResultDTO resultDTO) throws SQLException {
        String query = "INSERT INTO result (round, miss, fifth, fourth, third, second, first,rate) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        int index = 1;
        pstmt.setInt(index++, resultDTO.getRound());
        for (int num : resultDTO.getRanks()) {
            pstmt.setInt(index++, num);
        }
        pstmt.setDouble(index, resultDTO.getWinningRate());
        pstmt.executeUpdate();
    }
}
