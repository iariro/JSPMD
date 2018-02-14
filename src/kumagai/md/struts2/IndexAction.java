package kumagai.md.struts2;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import kumagai.md.*;

/**
 * トップページ表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/index.jsp")
public class IndexAction
{
	public ArrayList<PageIdAndDiskIdRange> pageAndDiskIdCollection;
	public int lastPageIndex;

	/**
	 * トップページ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("index")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		int maxid = DiskAndRecordCollection.getMaxDiskId(connection);

		connection.close();

		lastPageIndex = maxid / 100;

		pageAndDiskIdCollection = new ArrayList<PageIdAndDiskIdRange>();

		pageAndDiskIdCollection.add(new PageIdAndDiskIdRange("", "全部"));

		//  1-10 = 011-110
		// 11-20 = 111-210

		for (int i=0 ; i<maxid/100 + 1 ; i++)
		{
			pageAndDiskIdCollection.add(
				new PageIdAndDiskIdRange(
					i,
					String.format("%03d-%03d", i * 100 + 11, i * 100 + 110)));
		}

		return "success";
	}
}
