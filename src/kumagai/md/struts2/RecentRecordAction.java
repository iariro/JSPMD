package kumagai.md.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import kumagai.md.DiskAndRecord;
import kumagai.md.DiskAndRecordCollection;

/**
 * 最近の録音リスト表示アクション用フォーム。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/recentrecord.jsp")
public class RecentRecordAction
{
	public ArrayList<DiskAndRecord> recentRecords;

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

		connection.close();

		return "success";
	}
}
