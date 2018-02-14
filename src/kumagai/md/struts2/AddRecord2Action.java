package kumagai.md.struts2;

import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;

/**
 * 録音情報追加アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/addrecord2.jsp")
public class AddRecord2Action
{
	public String diskId;
	public String composerId;
	public String title;
	public String memo;
	public String symphony;
	public String date;

	/**
	 * 録音情報追加アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("addrecord2")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		if (diskId.length() > 0 && title.length() > 0)
		{
			// 必須項目指定あり。

			DriverManager.registerDriver(new SQLServerDriver());

			Connection connection = DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

			PreparedStatement statement;

			if (date.length() > 0)
			{
				// 日付指定あり。

				statement =
					connection.prepareStatement(
						"insert into record (diskid, composerid, title, symphony, memo, date) values (?, ?, ?, ?, ?, ?)");
			}
			else
			{
				// 日付指定なし。

				statement =
					connection.prepareStatement(
						"insert into record (diskid, composerid, title, symphony, memo, date) values (?, ?, ?, ?, ?, getdate())");
			}

			statement.setString(1, diskId);
			statement.setString(2, composerId);
			statement.setString(3, title);
			statement.setString(4, symphony);
			statement.setString(5, memo);

			if (date.length() > 0)
			{
				// 日付指定あり。

				statement.setString(6, date);
			}

			statement.executeUpdate();

			statement.close();
			connection.close();

			return "success";
		}
		else
		{
			// 必須項目指定なし。

			return "error";
		}
	}
}
