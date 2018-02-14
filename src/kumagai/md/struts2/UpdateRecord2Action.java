package kumagai.md.struts2;

import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;

/**
 * 録音情報編集アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/updaterecord2.jsp")
public class UpdateRecord2Action
{
	public String recordId;
	public String diskId;
	public String composerId;
	public String title;
	public String memo;
	public String symphony;
	public String date;

	/**
	 * 録音情報編集アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("updaterecord2")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		String sql =
			"update record set diskid=?, composerid=?, title=?, memo=?, symphony=?, date=? where recordid=?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, diskId);
		statement.setString(2, composerId);
		statement.setString(3, title);
		statement.setString(4, memo);
		statement.setString(5, symphony);
		statement.setDate(6, Date.valueOf(date.replace('/', '-')));
		statement.setString(7, recordId);

		statement.executeUpdate();

		statement.close();
		connection.close();

		return "success";
	}
}
