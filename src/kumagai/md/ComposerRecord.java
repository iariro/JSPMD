package kumagai.md;

import java.util.*;

/**
 * 作曲家レコード。
 * @author kumagai
 */
public class ComposerRecord
{
	public final String composer;
	public final int [] record;
	public final ArrayList<String> etc = new ArrayList<String>();

	/**
	 * 指定の値を割り当てオブジェクトを構築する。
	 * @param composer 作曲家名
	 * @param maxnumber 交響曲最大番号
	 */
	public ComposerRecord(String composer, int maxnumber)
	{
		this.composer = composer;
		this.record = new int [maxnumber];
	}

	/**
	 * 交響曲をコンプリートしているかを取得。
	 * @return true=コンプリートしている／false=していない
	 */
	public boolean isCompleted()
	{
		boolean complete = true;

		for (int i=0 ; i<record.length ; i++)
		{
			complete &= record[i] == 2;
		}

		return complete;
	}

	/**
	 * 番号ありの交響曲以外の曲目リスト。
	 * @return スラッシュ区切りの曲目リスト
	 */
	public String getEtcList()
	{
		String ret = new String();

		for (int i=0 ; i<etc.size() ; i++)
		{
			if (i > 0)
			{
				// ２個目以降。

				ret += "／";
			}

			ret += etc.get(i);
		}

		return ret;
	}

	/**
	 * タイトル追加。
	 * @param title タイトル
	 * @param memo メモ
	 */
	public void addTitle(String title, String memo)
	{
		try
		{
			int number = Integer.valueOf(title) - 1;

			if (record[number] < 2)
			{
				// 録音していないまたは途中。

				if (memo.indexOf("途中") >= 0)
				{
					// 途中からまたは途中まで。

					record[number] = 1;
				}
				else
				{
					// 完全。

					record[number] = 2;
				}
			}
		}
		catch (NumberFormatException exception)
		{
			// 番号あり以外。

			if (etc.indexOf(title) < 0)
			{
				// 既存ではないタイトル。

				etc.add(title);
			}
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			// 最後のナンバーを越えたナンバー。

			etc.add(title);
		}
	}
}
