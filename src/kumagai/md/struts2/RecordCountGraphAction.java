package kumagai.md.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import kumagai.md.RecordCollection;
import kumagai.md.YearMonthCount;

/**
 * 録音数時系列グラフ表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Results
({
	@Result(name="success", location="/md/mdcountgraph.jsp")
})
public class RecordCountGraphAction
{
	public ArrayList<YearMonthCount> perMonthCount;
	public String message;

	/**
	 * hiChartsグラフ用座標文字列を取得
	 * @return hiChartsグラフ用座標文字列
	 */
	public String getRecordCountPoints()
	{
		StringBuffer highChartsString = new StringBuffer();
		highChartsString.append("{name: '件数',data: [");
		int count = 0;

		for (int i=0 ; i<perMonthCount.size() ; i++)
		{
			YearMonthCount yearMonthCount = perMonthCount.get(i);

			if (i > 0)
			{
				// ２個目以降

				highChartsString.append(",");
			}

			count += yearMonthCount.getCount();
			highChartsString.append(
				String.format(
					"[Date.parse('%d/%d/1'), %d]",
					yearMonthCount.getYear(),
					yearMonthCount.getMonth(),
					count));
		}
		highChartsString.append("]}");

		return highChartsString.toString();
	}

	/**
	 * 録音数時系列グラフ表示アクション。
	 * @return 処理結果
	 */
	@Action("recordcountgraph")
	public String execute()
		throws Exception
	{
		try
		{
			ServletContext context = ServletActionContext.getServletContext();

			String dbUrl = context.getInitParameter("MDSqlserverUrl");
			if (dbUrl != null)
			{
				// パラメータあり

				DriverManager.registerDriver(new SQLServerDriver());

				Connection connection = DriverManager.getConnection(dbUrl);
				perMonthCount = RecordCollection.getPerMonthCount(connection);
				connection.close();

				return "success";
			}
			else
			{
				// パラメータなし

				message = "MDSqlserverUrl定義なし";
			}
		}
		catch (Exception exception)
		{
			// 何もしない

			message = exception.toString();
		}

		return "error";
	}
}
