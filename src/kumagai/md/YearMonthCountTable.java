package kumagai.md;

import java.util.*;

/**
 * 月毎録音件数集計。
 * @author kumagai
 */
public class YearMonthCountTable
	extends ArrayList<YearMonthCountTableRow>
{
	/**
	 * 月毎録音件数集計を構築。
	 * @param yearMonthCountCollection 録音集計 ソート済み
	 */
	public YearMonthCountTable
		(ArrayList<YearMonthCount> yearMonthCountCollection)
	{
		int year = 0;

		YearMonthCountTableRow row = null;

		for (YearMonthCount yearMonthCount : yearMonthCountCollection)
		{
			if (year != yearMonthCount.getYear())
			{
				// 次の年。

				if (year > 0)
				{
					// 先頭の年以降。

					for (int i=year+1 ; i<yearMonthCount.getYear() ; i++)
					{
						add(new YearMonthCountTableRow(i));
					}
				}

				year = yearMonthCount.getYear();

				row = new YearMonthCountTableRow(year);

				row.addMonthCount(
					yearMonthCount.getMonth(),
					yearMonthCount.getCount());

				add(row);
			}
			else
			{
				// 同じ年。

				row.addMonthCount(
					yearMonthCount.getMonth(),
					yearMonthCount.getCount());
			}
		}
	}
}
