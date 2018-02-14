package kumagai.md.struts2;

import java.sql.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;

/**
 * 作曲家登録アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/addcomposer2.jsp")
public class AddComposer2Action
{
	public String composer;
	public String maxNumber;
	public int namevalue;

	public int newId;

	/**
	 * 作曲家登録アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("addcomposer2")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection = DriverManager.getConnection
			(context.getInitParameter("MDSqlserverUrl"));

		PreparedStatement statement =
			connection.prepareStatement(
				"insert into composer (name, maxnumber, namevalue) values (?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, composer);
		statement.setString(2, maxNumber);
		statement.setInt(3, namevalue);
		statement.executeUpdate();

		ResultSet keys = statement.getGeneratedKeys();
		if (keys.next())
		{
			newId = keys.getInt(1);
		}
		keys.close();

		statement.close();
		connection.close();

		return "success";
	}
}
