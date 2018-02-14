package kumagai.md;

import java.sql.*;
import java.util.*;

/**
 * 作曲家レコードコレクション。
 * @author kumagai
 */
public class ComposerRecordCollection
	extends ArrayList<ComposerRecord>
{
	/**
	 * DB値からコレクションを構築する。
	 * @param results DBレコードコレクション
	 * @throws SQLException
	 */
	public ComposerRecordCollection(ResultSet results)
		throws SQLException
	{
		int maxnumber2;
		String title;
		String name = null;
		String name2 = null;
		String memo = null;
		ComposerRecord record = null;

		while (results.next())
		{
			name = results.getString("name");
			title = results.getString("title");
			maxnumber2 = results.getInt("maxnumber");
			memo = results.getString("memo");

			if (record == null)
			{
				// 初回。

				record = new ComposerRecord(name, maxnumber2);
				name2 = record.composer;
			}

			if (name.equals(name2))
			{
				// 同じ作曲家。

				record.addTitle(title, memo);
			}
			else
			{
				// 次の作曲家。

				add(record);

				record = new ComposerRecord(name, maxnumber2);
				record.addTitle(title, memo);
			}

			name2 = name;
		}

		// ラスト分の処理。
		if (record != null)
		{
			// レコードは取得できた。

			add(record);
		}
	}
}
