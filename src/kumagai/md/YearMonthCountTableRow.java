package kumagai.md;

/**
 * 月毎録音件数集計行データ。
 * @author kumagai
 */
public class YearMonthCountTableRow
{
	private int year;
	private int [] monthCount;

	/**
	 * 年の割り当てと配列の初期化。
	 * @param year 年
	 */
	public YearMonthCountTableRow(int year)
	{
		this.year = year;
		this.monthCount = new int [12];
	}

	/**
	 * 年を取得。
	 * @return 年
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * 年を割り当て。
	 * @param year
	 */
	public void setYear(int year)
	{
		this.year = year;
	}

	/**
	 * カウントを取得。
	 * @return カウント
	 */
	public int[] getMonthCount()
	{
		return monthCount;
	}

	/**
	 * カウントを割り当て。
	 * @param month 月
	 * @param monthCount 月のカウント
	 */
	public void addMonthCount(int month, int monthCount)
	{
		this.monthCount[month - 1] += monthCount;
	}
}
