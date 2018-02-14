package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * １作曲家分の交響曲録音状況一覧表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/composeronlylist.jsp")
public class ComposerOnlyListAction
{
	public String composerName;
	public String composerId;
	public String maxNumber;
	public String perDisk;
	public ArrayList<RecordFlagAndColor> array;

	/**
	 * １作曲家分の交響曲録音状況一覧表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("composeronlylist")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		int composer = Integer.valueOf(this.composerId);
		int max = Integer.valueOf(this.maxNumber);
		int perDisk = Integer.valueOf(this.perDisk);

		String sql =
			"select recordid, diskid, name, title, date, symphony, memo from composer join record on composer.composerid=record.composerid where record.composerid=? order by title";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, composer);

		boolean [] flagArray = new boolean [max];

		ResultSet results = statement.executeQuery();

		while (results.next())
		{
			String title = results.getString(4);

			try
			{
				flagArray[Integer.valueOf(title) - 1] = true;
			}
			catch (NumberFormatException exception)
			{
				// 番号なしタイトルの場合。
			}
		}

		results.close();
		statement.close();
		connection.close();

		this.array = new ArrayList<RecordFlagAndColor>();

		for (int i=0 ; i<max ; i++)
		{
			String color;

			switch ((i % (perDisk * 5)) / perDisk)
			{
				case 0:
					color = "#ffcccc";
					break;
				case 1:
					color = "#ffffcc";
					break;
				case 2:
					color = "#ccffcc";
					break;
				case 3:
					color = "#ccccff";
					break;
				case 4:
					color = "#00ccff";
					break;
				default:
					throw new Exception();
			}

			this.array.add(new RecordFlagAndColor(i + 1, flagArray[i], color));
		}

		return "success";
	}
}
