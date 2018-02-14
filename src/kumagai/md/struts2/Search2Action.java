package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * 検索アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/search2.jsp")
public class Search2Action
{
	public String composerId;
	public String title;
	public String symphony;
	public ArrayList<DiskAndRecordCore> diskAndRecords;

	/**
	 * 検索アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("search2")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		String sql =
			"select recordid, diskid, name, title, date, memo from record join composer on record.composerid=composer.composerid where record.composerid=? and symphony=?";

		if (title.length() > 0)
		{
			// タイトルの指定あり。

			sql += " and title=?";
		}

		sql += " order by name, title";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, composerId);
		statement.setString(2, symphony);

		if (title.length() > 0)
		{
			// タイトルの指定あり。

			statement.setString(3, title);
		}

		ResultSet results = statement.executeQuery();

		diskAndRecords = new ArrayList<DiskAndRecordCore>();

		while (results.next())
		{
			diskAndRecords.add(new DiskAndRecordCore(results));
		}

		results.close();
		statement.close();
		connection.close();

		return "success";
	}
}
