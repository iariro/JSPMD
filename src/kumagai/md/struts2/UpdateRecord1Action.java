package kumagai.md.struts2;

import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * 録音情報更新ページ表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/updaterecord1.jsp")
public class UpdateRecord1Action
{
	static private final SimpleDateFormat formatDate;

	/**
	 * 日付書式オブジェクトを生成。
	 */
	static
	{
		formatDate = new SimpleDateFormat();
		formatDate.applyPattern("yyyy/MM/dd");
	}

	public String recordId;
	public String composerId;
	public int diskId;
	public int symphony;
	public String title;
	public String memo;
	public Date date;
	public ArrayList<Composer2> composers;

	/**
	 * 録音日付を取得。
	 * @return 録音日付
	 */
	public String getDate()
	{
		return formatDate.format(date);
	}

	/**
	 * 録音情報更新ページ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("updaterecord1")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		String sql =
			"select diskid, composerid, title, date, symphony, memo from record where recordid=?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, recordId);

		ResultSet results = statement.executeQuery();

		if (results.next())
		{
			// レコード取得成功。

			composerId = results.getString("composerid");
			date = results.getDate("date");
			diskId = results.getInt("diskid");
			memo = results.getString("memo");
			symphony = results.getInt("symphony");
			title = results.getString("title");
		}

		statement.close();

		Statement statement2 = connection.createStatement();

		results =
			statement2.executeQuery
				("select composerid, name from composer order by name");

		composers = new ArrayList<Composer2>();

		while (results.next())
		{
			composers.add(new Composer2(results));
		}

		results.close();
		statement2.close();
		connection.close();

		return "success";
	}
}
