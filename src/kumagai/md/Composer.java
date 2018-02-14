package kumagai.md;

import java.sql.*;

/**
 * 作曲家情報。交響曲の曲数情報付き。
 * @author kumagai
 */
public class Composer
{
	private final int id;
	private final String name;
	private final int maxNumber;

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

	/**
	 * 交響曲の曲数
	 * @return 交響曲の曲数
	 */
	public int getMaxNumber()
	{
		return maxNumber;
	}

	/**
	 * DBレコード値からオブジェクトを構築。
	 * @param results DBレコード
	 * @throws SQLException
	 */
	public Composer(ResultSet results)
		throws SQLException
	{
		id = results.getInt("composerid");
		name = results.getString("name");
		maxNumber = results.getInt("maxnumber");
	}
}
