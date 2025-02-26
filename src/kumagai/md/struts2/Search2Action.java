package kumagai.md.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import kumagai.md.DiskAndRecordCore;

/**
 * 検索アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/search2.jsp")
public class Search2Action
{
	public static void main(String[] args) throws Exception
	{
		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				("jdbc:sqlserver://localhost:2144;DatabaseName=MD;User=sa;Password=p@ssw0rd;");

		ArrayList<DiskAndRecordCore> diskAndRecords = executeCore("", "83", "1", connection);
		for (DiskAndRecordCore diskAndRecord : diskAndRecords)
		{
			System.out.printf("%s %s\n", diskAndRecord.getName(), diskAndRecord.getTitle());
		}
	}

	public String composerId;
	public String title;
	public String symphony;
	public ArrayList<DiskAndRecordCore> diskAndRecords;

	/**
	 * 検索アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("search2")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		diskAndRecords = executeCore(title, composerId, symphony, connection);

		return "success";
	}

	static ArrayList<DiskAndRecordCore> executeCore(String title, String composerId, String symphony, Connection connection) throws SQLException
	{
		String sql =
			"select recordid, diskid, name, title, date, memo from record join composer on record.composerid=composer.composerid where record.composerid=? and symphony=?";

		if (title.length() > 0)
		{
			// タイトルの指定あり。

			sql += " and title=?";
		}

		sql += " order by name, title";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, composerId);
		statement.setString(2, symphony);

		if (title.length() > 0)
		{
			// タイトルの指定あり。

			statement.setString(3, title);
		}

		ResultSet results = statement.executeQuery();

		ArrayList<DiskAndRecordCore> diskAndRecords = new ArrayList<DiskAndRecordCore>();

		while (results.next())
		{
			diskAndRecords.add(new DiskAndRecordCore(results));
		}

		results.close();
		statement.close();
		connection.close();

		Collections.sort(diskAndRecords,
			new Comparator<DiskAndRecordCore>()
			{
				public int compare(DiskAndRecordCore o1, DiskAndRecordCore o2)
				{
					try
					{
						Integer no1 = Integer.parseInt(o1.getTitle());
						Integer no2 = Integer.parseInt(o2.getTitle());
						return no1.compareTo(no2);
					}
					catch (NumberFormatException exception)
					{
						String no1 = o1.getTitle();
						String no2 = o2.getTitle();
						return no1.compareTo(no2);
					}
				}
			});

		return diskAndRecords;
	}
}
