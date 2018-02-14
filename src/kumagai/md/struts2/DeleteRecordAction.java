package kumagai.md.struts2;

import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;

/**
 * 録音情報削除アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/deleterecord.jsp")
public class DeleteRecordAction
{
	public String recordId;
	public String diskId;
	public String name;
	public String title;

	/**
	 * 録音情報削除アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("deleterecord")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		PreparedStatement statement =
			connection.prepareStatement
				("delete from record where recordid=?");

		statement.setString(1, recordId);

		statement.executeUpdate();

		statement.close();
		connection.close();

		return "success";
	}
}
