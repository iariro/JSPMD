package kumagai.md;

import java.sql.*;
import java.text.*;

/**
 * 録音情報基底部。
 * @author kumagai
 */
public class DiskAndRecordCore
{
	static protected final SimpleDateFormat formatDate;

	/**
	 * 日付書式初期化。
	 */
	static
	{
		formatDate = new SimpleDateFormat();
		formatDate.applyPattern("yyyy/MM/dd(E)");
	}

	protected final int recordId;
	protected final int diskId;
	protected final String name;
	protected final String title;
	protected final Date date;
	protected final String memo;

	/**
	 * 録音IDを取得。
	 * @return 録音ID
	 */
	public int getRecordId()
	{
		return recordId;
	}

	/**
	 * ディスクIDを取得。
	 * @return ディスクID
	 */
	public int getDiskId()
	{
		return diskId;
	}

	/**
	 * 作曲家名を取得。
	 * @return 作曲家名
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 曲タイトルを取得。
	 * @return 曲タイトル
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 録音日を取得。
	 * @return 録音日
	 */
	public String getDate()
	{
		return formatDate.format(date);
	}

	/**
	 * 備考を取得。
	 * @return 備考
	 */
	public String getMemo()
	{
		return memo;
	}

	/**
	 * DBレコードからオブジェクトを構築。
	 * @param results DBレコード
	 * @throws SQLException
	 */
	public DiskAndRecordCore(ResultSet results)
		throws SQLException
	{
		recordId = results.getInt("recordid");
		diskId = results.getInt("diskid");
		name = results.getString("name");
		title = results.getString("title");
		date = results.getDate("date");
		memo = results.getString("memo");
	}
}
