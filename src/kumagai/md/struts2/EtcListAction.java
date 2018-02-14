package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * 交響曲以外の曲一覧表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/etclist.jsp")
public class EtcListAction
{
	public ArrayList<DiskAndRecordCore> records;

	/**
	 * 交響曲以外の曲一覧表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("etclist")
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
			statement.executeQuery(
				"select recordid, diskid, name, title, date, memo from composer join record on composer.composerid=record.composerid where symphony=0 order by name, title");

		records = new ArrayList<DiskAndRecordCore>();

		while (results.next())
		{
			records.add(new DiskAndRecordCore(results));
		}

		results.close();
		statement.close();
		connection.close();

		return "success";
	}
}
