package kumagai.md.struts2;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.xml.transform.*;
import com.microsoft.sqlserver.jdbc.*;
import org.apache.struts2.*;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.convention.annotation.Result;
import ktool.xml.*;
import kumagai.md.*;

/**
 * MDリストグラフ表示アクション。
 * @author kumagai
 */
@Namespace("/md")
@Result(name="success", location="/md/disklist.jsp")
public class DiskListAction
{
	private KDocument document;

	/**
	 * グラフSVGドキュメントを文字列として取得。
	 * @return 文字列によるグラフSVGドキュメント
	 */
	public String getXml()
		throws TransformerFactoryConfigurationError, TransformerException
	{
		// XML書き出し準備。
		Transformer transformer =
			TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

		StringWriter writer = new StringWriter();

		// XML書き出し。
		document.write(transformer, writer);

		return writer.toString();
	}

	/**
	 * MDリストグラフ表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("disklist")
	public String execute()
		throws Exception
	{
		ServletContext context = ServletActionContext.getServletContext();

		DriverManager.registerDriver(new SQLServerDriver());

		Connection connection =
			DriverManager.getConnection
				(context.getInitParameter("MDSqlserverUrl"));

		Statement statement = connection.createStatement();

		ResultSet results = statement.executeQuery("select diskid from record");

		int xcount = 100;
		int ycount = 10;

		int [] count = new int [xcount * ycount];

		while (results.next())
		{
			int id = results.getInt(1) - 1;

			count[id]++;
		}

		results.close();
		statement.close();
		connection.close();

		document = new MdListSvgDocument(count, xcount, ycount);

		return "success";
	}
}
