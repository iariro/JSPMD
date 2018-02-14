package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * 検索ページ表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/search1.jsp")
public class Search1Action
{
	public ArrayList<IdAndName> idAndNames;

	/**
	 * 検索ページ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("search1")
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
				new IdAndName(results.getInt(1), results.getString(2)));
		}

		results.close();
		statement.close();
		connection.close();

		return "success";
	}
}
