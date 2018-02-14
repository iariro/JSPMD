package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * 交響曲一覧表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/symphonylist.jsp")
public class SymphonyListAction
{
	public boolean excludeNoNumber;
	public int namevalue;
	public int [] numbers;
	public ArrayList<ComposerRecord2> records;

	/**
	 * 交響曲表示欄最大数を取得。
	 * @return 交響曲表示欄最大数
	 */
	public int getMaxNumber()
	{
		return 15;
	}

	/**
	 * 交響曲一覧表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("symphonylist")
	public String execute()
		throws Exception
	{
		numbers = new int [getMaxNumber()];

		for (int i=0 ; i<numbers.length ; i++)
		{
			numbers[i] = i + 1;
		}

		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		Statement statement = connection.createStatement();

		String sql =
			"select name, title, maxnumber, memo from composer join record on composer.composerid=record.composerid ";

		if (excludeNoNumber)
		{
			// 番号なしのみ除外指定あり。

			sql += "where maxnumber>0 and symphony=1 ";
		}
		else
		{
			// 番号なしのみ除外指定なし。

			sql += "where symphony=1 ";
		}

		sql += String.format("and namevalue <= %d ", namevalue);

		sql += "order by name, title";

		ResultSet results = statement.executeQuery(sql);

		ArrayList<ComposerRecord> records1 =
			new ComposerRecordCollection(results);

		results.close();
		connection.close();

		records = new ArrayList<ComposerRecord2>();

		for (int i=0 ; i<records1.size() ; i++)
		{
			if (i % 20 == 0)
			{
				// ヘッダ挿入位置。

				// nullでヘッダ挿入位置を表す。
				records.add(null);
			}

			ComposerRecord2 record2 =
				new ComposerRecord2(records1.get(i), getMaxNumber());

			records.add(record2);

			for (int j=0 ; j<getMaxNumber() ; j++)
			{
				if (j < records1.get(i).record.length &&
					records1.get(i).record[j] > 0)
				{
					// 曲が存在しており録音した。

					if (records1.get(i).record[j] == 1)
					{
						// 途中。

						record2.setNumbers(j, 0);
					}
					else
					{
						// 完全。

						record2.setNumbers(j, 1);
					}
				}
				else
				{
					// 曲が存在しないまたは録音していない。

					if (j < records1.get(i).record.length)
					{
						// 録音していない。

						record2.setNumbers(j, 2);
					}
					else
					{
						// 曲が存在しない。

						record2.setNumbers(j, 3);
					}
				}
			}

			record2.etc = records1.get(i).getEtcList();
		}

		return "success";
	}
}
