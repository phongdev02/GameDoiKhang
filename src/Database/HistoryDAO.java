package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int st;

	public int insertLichSu(LichSuDau ls) {
		connection = ConnectionFactory.getConnection();
		try {
			String queryString = "INSERT INTO lichsudau "
					+ "(NguoiChoi1, NguoiChoi2, NguoiChoiWin, HP, MP, Crit, CritDM) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, ls.getPlayer1());
			preparedStatement.setString(2, ls.getPlayer2());
			preparedStatement.setString(3, ls.getPlayerWin());
			preparedStatement.setInt(4, ls.getHP());
			preparedStatement.setInt(5, ls.getMP());
			preparedStatement.setInt(6, ls.getCrit());
			preparedStatement.setInt(7, ls.getCritDame());

			st = preparedStatement.executeUpdate();
			System.out.println("Inserted Lich su dau" + st);

		} catch (SQLException e) {
			e.printStackTrace();
			st = -2;
		}

		return st;
	}

	public List<LichSuDau> toStringLSDau() {
		String queryString = "SELECT * FROM lichsudau";

		List<LichSuDau> lstLichSuDaus = new ArrayList<>();

		try {
			connection = ConnectionFactory.getConnection();

			ps = connection.prepareStatement(queryString);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				String nguoiChoi1 = rs.getString("NguoiChoi1");
				String nguoiChoi2 = rs.getString("NguoiChoi2");
				String nguoiChoiWin = rs.getString("NguoiChoiWin");
				int HP = rs.getInt("HP");
				int MP = rs.getInt("MP");
				int Crit = rs.getInt("Crit");
				int CritDM = rs.getInt("CritDM");

				LichSuDau lSuDau = new LichSuDau(nguoiChoi1, nguoiChoi2, nguoiChoiWin, HP, MP, Crit, CritDM);

				lstLichSuDaus.add(lSuDau);
			}

			for(var item : lstLichSuDaus) {
				System.out.println(item.toString());
			}
			
			// Đóng resultSet và statement sau khi sử dụng
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (ps != null) {
//					ps.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

		return lstLichSuDaus;
	}

}
