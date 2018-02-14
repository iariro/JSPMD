package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import ktool.datetime.*;
import kumagai.md.*;

/**
 * 録音情報追加ページ表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/addrecord1.jsp")
public class AddRecord1Action
{
	public ArrayList<IdAndName> idAndNames;
	public int newid;

	/**
	 * 今日の日付を取得。
	 * @return 今日の日付
	 */
	public String getToday()
	{
		return new DateTime().toString();
	}

	/**
	 * 録音情報追加ページ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("addrecord1")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		Statement statement = connection.createStatement();

		ResultSet results =
			statement.executeQuery
				("select composerid, name from composer order by name");

		idAndNames = new ArrayList<IdAndName>();

		while (results.next())
		{
			idAndNames.add(
				new IdAndName(
					results.getInt("composerid"),
					results.getString("name")));
		}

		results.close();
		statement.close();

		newid = DiskAndRecordCollection.getMaxDiskId(connection) + 1;

		connection.close();

		return "success";
	}
}
