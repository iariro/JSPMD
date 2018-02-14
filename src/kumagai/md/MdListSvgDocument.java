package kumagai.md;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.w3c.dom.*;
import ktool.xml.*;

/**
 * MDリストを表示するSVGドキュメント。
 * @author kumagai
 */
public class MdListSvgDocument
	extends KDocument
{
	static public final int ox = 50;
	static public final int oy = 50;
	static public final int xsize = 10;
	static public final int ysize = 20;

	/**
	 * SVGドキュメント出力テスト。
	 * @param args 未使用
	 * @throws Exception
	 */
	public static void main(String [] args)
		throws Exception
	{
		int [] count = new int [1000];

		MdListSvgDocument document = new MdListSvgDocument(count, 100, 10);

		document.write(new OutputStreamWriter(System.out));
	}

	/**
	 * ドキュメントを構築する。
	 * @param count ディスク１枚につき１つの点の座標のコレクション
	 * @param xcount 横方向個数
	 * @param ycount 縦方向個数
	 * @throws ParserConfigurationException
	 * @throws TransformerConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 */
	public MdListSvgDocument(int [] count, int xcount, int ycount)
		throws ParserConfigurationException,
			TransformerConfigurationException,
			TransformerFactoryConfigurationError
	{
		// トップ要素。
		Element top = createElement("svg");
		top.setAttribute("xmlns", "http://www.w3.org/2000/svg");
		appendChild(top);

		Element element1 = createElement("title");
		element1.appendChild(createTextNode("MDリスト"));
		top.appendChild(element1);

		element1 = createElement("rect");
		top.appendChild(element1);
		element1.setAttribute("x", Integer.toString(ox));
		element1.setAttribute("y", Integer.toString(oy));
		element1.setAttribute("width", Integer.toString(xsize * xcount));
		element1.setAttribute("height", Integer.toString(ysize * ycount));
		element1.setAttribute("fill", "none");
		element1.setAttribute("stroke", "black");

		// 横軸縦線。
		for (int i=5 ; i<=95 ; i += 5)
		{
			element1 = createElement("line");
			top.appendChild(element1);

			element1.setAttribute("x1", Integer.toString(ox + xsize * i));
			element1.setAttribute("y1", Integer.toString(oy));
			element1.setAttribute("x2", Integer.toString(ox + xsize * i));
			element1.setAttribute("y2", Integer.toString(oy + ysize * 10));
			element1.setAttribute("style", "stroke:gray");
			element1.setAttribute("stroke-width", "0.5");
		}

		// 縦軸数字。
		for (int i=0 ; i<ycount ; i++)
		{
			element1 = createElement("text");
			top.appendChild(element1);

			element1.setAttribute
				("x", Integer.toString(ox - 10));
			element1.setAttribute
				("y", Integer.toString(oy + ysize * (i + 1) - 5));
			element1.setAttribute
				("text-anchor", "end");
			element1.appendChild
				(createTextNode(Integer.toString(i * xcount + 1)));
		}

		// ディスクを表す四角形。
		for (int x=0 ; x<xcount ; x++)
		{
			for (int y=0 ; y<ycount ; y++)
			{
				if (count[x + xcount * y] > 0)
				{
					// 録音あり。

					String color;

					if (count[x + xcount * y] <= 1)
					{
						// １曲。

						color = "#aaaaff";
					}
					else if (count[x + xcount * y] <= 2)
					{
						// ２曲。

						color = "#4444ff";
					}
					else
					{
						// それ以上。

						color = "blue";
					}

					element1 = createElement("rect");
					top.appendChild(element1);

					element1.setAttribute
						("x", Integer.toString(ox + x * xsize));
					element1.setAttribute
						("y", Integer.toString(oy + y * ysize + 1));
					element1.setAttribute
						("width", Integer.toString(xsize - 3));
					element1.setAttribute
						("height", Integer.toString(ysize - 3));
					element1.setAttribute
						("fill", color);
					element1.setAttribute
						("stroke", color);
				}
			}
		}
	}
}
