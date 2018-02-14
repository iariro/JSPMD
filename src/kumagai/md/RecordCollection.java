package kumagai.md;

import java.sql.*;
import java.util.*;

/**
 * 録音情報コレクション。
 * @author kumagai
 */
public class RecordCollection
{
	/**
	 * 年月ごとのカウント集計を取得。
	 * @param connection DB接続オブジェクト
	 * @return 年月ごとのカウント集計
	 * @throws SQLException
	 */
	static public ArrayList<YearMonthCount> getPerMonthCount
		(Connection connection)
		throws SQLException
	{
		String sql =
			"select year(date) as year, MONTH(date) as month, COUNT(*) as count from record group by YEAR(date), MONTH(date) order by YEAR(date), MONTH(date)";

		PreparedStatement statement = connection.prepareStatement(sql);

		ArrayList<YearMonthCount> list = new ArrayList<YearMonthCount>();
		ResultSet results = statement.executeQuery();

		while (results.next())
		{
			list.add(
				new YearMonthCount
					(results.getInt(1), results.getInt(2), results.getInt(3)));
		}

		connection.close();

		return list;
	}
}
