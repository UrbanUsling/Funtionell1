package Skoaffären;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface StoredProceduren {
    void connectToAndQueryDatabase() throws SQLException;

    static void connectToSP(int id, int skorId, int kundId) throws SQLException {
        try (Connection con = DriverManager.getConnection(Data.getConnection(),
                Data.getUsername(),
                Data.getPassword());
             CallableStatement stmt3 = con.prepareCall("CALL AddToChart(?, ?, ?)");

        ) {
            stmt3.setInt(1,id);
            stmt3.setInt(2,skorId);
            stmt3.setInt(3,kundId);
            stmt3.executeQuery();
        }
    }
}

