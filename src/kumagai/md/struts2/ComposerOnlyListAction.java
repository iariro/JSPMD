package kumagai.md.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import kumagai.md.RecordFlagAndColor;

/**
 * １作曲家分の交響曲録音状況一覧表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Results
({
	@Result(name="success1", location="/md/composeronlylist1.jsp"),
	@Result(name="success2", location="/md/composeronlylist2.jsp")
})
public class ComposerOnlyListAction
{
	public String composerName;
	public String composerId;
	public String maxNumber;
	public int count;
	public String perDisk;
	public String listtype;
	public ArrayList<ArrayList<RecordFlagAndColor>> array1;
	public ArrayList<RecordFlagAndColor> array2;

	/**
	 * １作曲家分の交響曲録音状況一覧表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("composeronlylist")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		int composer = Integer.valueOf(this.composerId);
		int max = Integer.valueOf(this.maxNumber);
		int perDisk = Integer.valueOf(this.perDisk);

		String sql =
			"select recordid, diskid, name, title, date, symphony, memo from composer join record on composer.composerid=record.composerid where record.composerid=? order by title";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, composer);

		boolean [] flagArray = new boolean [max];

		ResultSet results = statement.executeQuery();

		while (results.next())
		{
			String title = results.getString(4);

			try
			{
				flagArray[Integer.valueOf(title) - 1] = true;
				count++;
			}
			catch (NumberFormatException exception)
			{
				// 番号なしタイトルの場合。
			}
		}

		results.close();
		statement.close();
		connection.close();

		this.array1 = new ArrayList<ArrayList<RecordFlagAndColor>>();
		ArrayList<RecordFlagAndColor> array12 = null;
		this.array2 = new ArrayList<RecordFlagAndColor>();

		for (int i=0 ; i<max ; i++)
		{
			String color;

			switch ((i % (perDisk * 5)) / perDisk)
			{
				case 0:
					color = "#ffcccc";
					break;
				case 1:
					color = "#ffffcc";
					break;
				case 2:
					color = "#ccffcc";
					break;
				case 3:
					color = "#ccccff";
					break;
				case 4:
					color = "#00ccff";
					break;
				default:
					throw new Exception();
			}

			if (i % 10 == 0)
			{
				array12 = new ArrayList<RecordFlagAndColor>();
				this.array1.add(array12);
			}

			RecordFlagAndColor recordFlagAndColor = new RecordFlagAndColor(i + 1, flagArray[i], color);
			array12.add(recordFlagAndColor);
			this.array2.add(recordFlagAndColor);
		}

		return "success" + listtype;
	}
}
