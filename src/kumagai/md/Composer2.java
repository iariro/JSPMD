package kumagai.md;

import java.sql.*;

/**
 * 作曲家情報。
 * @author kumagai
 */
public class Composer2
{
	private final int id;
	private final String name;

	/**
	 * DBレコード値からオブジェクトを構築。
	 * @param results DBレコード
	 * @throws SQLException
	 */
	public Composer2(ResultSet results)
		throws SQLException
	{
		this.id = results.getInt(1);
		this.name = results.getString(2);
	}

	/**
	 * 作曲家IDを取得。
	 * @return 作曲家ID
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * 作曲家名を取得。
	 * @return 作曲家名
	 */
	public String getName()
	{
		return name;
	}
}
