package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * 最近の録音リスト表示アクション用フォーム。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/recentrecord.jsp")
public class RecentRecordAction
{
	public ArrayList<DiskAndRecord> recentRecords;
	public YearMonthCountTable yearMonthCountTable;

	/**
	 * 最近の録音リスト表示アクション用フォーム。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("recentrecord")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		recentRecords = DiskAndRecordCollection.getRecentRecords(connection);

		ArrayList<YearMonthCount> perMonthCount =
			RecordCollection.getPerMonthCount(connection);

		connection.close();

		yearMonthCountTable = new YearMonthCountTable(perMonthCount);

		return "success";
	}
}
