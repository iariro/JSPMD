package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * 全タイトルリスト表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/alllist.jsp")
public class AllListAction
{
	public String allListNo;
	public ArrayList<DiskAndRecord> diskAndMusicCollection;

	/**
	 * 全タイトルリスト表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("alllist")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		String startDisk = null;
		String endDisk = null;

		if (allListNo.length() > 0)
		{
			// １個でもある。

			startDisk =
				String.format("%d", Integer.valueOf(allListNo) * 100 + 11);
			endDisk =
				String.format("%d", Integer.valueOf(allListNo) * 100 + 110);
		}

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		diskAndMusicCollection =
			new DiskAndRecordCollection(connection, startDisk, endDisk);

		connection.close();

		return "success";
	}
}
