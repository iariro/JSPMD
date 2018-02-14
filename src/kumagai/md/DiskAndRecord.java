package kumagai.md;

import java.sql.*;

/**
 * ディスク情報と曲録音情報。
 * @author kumagai
 */
public class DiskAndRecord
	extends DiskAndRecordCore
{
	private boolean symphony;

	/**
	 * DB取得レコードからオブジェクトを構築する。
	 * @param results DB取得レコード
	 * @throws SQLException
	 */
	public DiskAndRecord(ResultSet results)
		throws SQLException
	{
		super(results);

		symphony = results.getBoolean("symphony");
	}

	/**
	 * 交響曲フラグを取得。
	 * @return 交響曲フラグ
	 */
	public boolean isSymphony()
	{
		return symphony;
	}
}
