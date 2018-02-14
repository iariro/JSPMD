package kumagai.md;

import java.sql.*;
import java.util.*;

/**
 * ディスク情報と曲録音情報のコレクション。
 * @author kumagai
 */
public class DiskAndRecordCollection
	extends ArrayList<DiskAndRecord>
{
	/**
	 * １年以内の登録分情報を取得。
	 * @param connection DB接続
	 * @return １年以内の登録分情報
	 * @throws SQLException
	 */
	static public ArrayList<DiskAndRecord> getRecentRecords
		(Connection connection)
		throws SQLException
	{
		String sql =
			"select recordid, diskid, composer.composerid, name, title, date, symphony, memo from composer join record on composer.composerid=record.composerid where year(date)*100 + month(date) > (year(getdate())-1)*100 + month(getdate()) order by date desc, diskid, name, title";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet results = statement.executeQuery();

		ArrayList<DiskAndRecord> list = new ArrayList<DiskAndRecord>();

		while (results.next())
		{
			list.add(new DiskAndRecord(results));
		}

		results.close();

		return list;
	}

	/**
	 * 最大ディスクIDを取得
	 * @param connection 接続オブジェクト
	 * @return 最大ディスクID
	 * @throws Exception
	 */
	static public int getMaxDiskId(Connection connection)
		throws Exception
	{
		String sql = "select max(diskid) as id from record";

		Statement statement = connection.createStatement();

		ResultSet results = statement.executeQuery(sql);

		int id;

		if (results.next())
		{
			// 取得成功。

			id = results.getInt("id");
		}
		else
		{
			// 取得失敗。

			throw new Exception();
		}

		results.close();

		return id;
	}

	/**
	 * DBからデータを取得しコレクションを構築する。
	 * @param connection DB接続
	 * @param startdisk 開始ID
	 * @param enddisk 終了ID
	 * @throws SQLException
	 */
	public DiskAndRecordCollection
		(Connection connection, String startdisk, String enddisk)
		throws SQLException
	{
		String sql =
			"select recordid, diskid, composer.composerid, name, title, date, symphony, memo from composer join record on composer.composerid=record.composerid ";

		if (startdisk != null && enddisk == null)
		{
			// 開始指定あり。

			sql += "where diskid >= ? ";
		}
		else if (startdisk == null && enddisk != null)
		{
			// 終了指定あり。

			sql += "where diskid <= ? ";
		}
		else if (startdisk != null && enddisk != null)
		{
			// 開始・終了指定あり。

			sql += "where diskid between ? and ? ";
		}

		sql += "order by diskid, date, name, title";

		PreparedStatement statement;

		statement = connection.prepareStatement(sql);

		if (startdisk != null && enddisk == null)
		{
			// 開始指定あり。

			statement.setString(1, startdisk);
		}
		else if (startdisk == null && enddisk != null)
		{
			// 終了指定あり。

			statement.setString(1, enddisk);
		}
		else if (startdisk != null && enddisk != null)
		{
			// 開始・終了指定あり。

			statement.setString(1, startdisk);
			statement.setString(2, enddisk);
		}

		ResultSet results = statement.executeQuery();

		while (results.next())
		{
			add(new DiskAndRecord(results));
		}

		connection.close();
	}
}
