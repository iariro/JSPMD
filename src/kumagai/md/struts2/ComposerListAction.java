package kumagai.md.struts2;

import java.util.*;
import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * 作曲家一覧表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/composerlist.jsp")
public class ComposerListAction
{
	public ArrayList<Composer> composers;

	/**
	 * 作曲家一覧表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("composerlist")
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
				("select composerid, name, maxnumber from composer order by name");

		composers = new ArrayList<Composer>();

		while (results.next())
		{
			composers.add(new Composer(results));
		}

		results.close();
		statement.close();
		connection.close();

		return "success";
	}
}
