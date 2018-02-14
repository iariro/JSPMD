package kumagai.md;

/**
 * 年月・カウント情報。
 * @author kumagai
 */
public class YearMonthCount
{
	private int year;
	private int month;
	private int count;

	/**
	 * 指定の値をメンバーに割り当て。
	 * @param year 年
	 * @param month 月
	 * @param count カウント
	 */
	public YearMonthCount(int year, int month, int count)
	{
		this.year = year;
		this.month = month;
		this.count = count;
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
	 * @param year 年
	 */
	public void setYear(int year)
	{
		this.year = year;
	}

	/**
	 * 月を取得。
	 * @return 月
	 */
	public int getMonth()
	{
		return month;
	}

	/**
	 * 月を割り当て。
	 * @param month 月
	 */
	public void setMonth(int month)
	{
		this.month = month;
	}

	/**
	 * カウントを取得。
	 * @return カウント
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * カウントを割り当て。
	 * @param count カウント
	 */
	public void setCount(int count)
	{
		this.count = count;
	}
}
