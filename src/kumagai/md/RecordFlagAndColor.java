package kumagai.md;

/**
 * 交響曲のナンバーと録音の有無・表示色情報。
 * @author kumagai
 */
public class RecordFlagAndColor
{
	private int number;
	private boolean record;
	private String color;

	/**
	 * ナンバーを取得。
	 * @return ナンバー
	 */
	public int getNumber()
	{
		return number;
	}

	/**
	 * ナンバーを割り当て。
	 * @param number ナンバー
	 */
	public void setNumber(int number)
	{
		this.number = number;
	}

	/**
	 * 録音の有無を取得。
	 * @return 録音の有無
	 */
	public String getRecord()
	{
		return record ? "○" : new String();
	}

	/**
	 * 録音の有無を割り当て。
	 * @param record 録音の有無
	 */
	public void setRecord(boolean record)
	{
		this.record = record;
	}

	/**
	 * 表示色を取得。
	 * @return 表示色
	 */
	public String getColor()
	{
		return color;
	}

	/**
	 * 表示色を割り当て。
	 * @param color 表示色
	 */
	public void setColor(String color)
	{
		this.color = color;
	}

	/**
	 * メンバーに値を割り当てる。
	 * @param number ナンバー
	 * @param record 録音の有無
	 * @param color 表示色
	 */
	public RecordFlagAndColor(int number, boolean record, String color)
	{
		this.setNumber(number);
		this.record = record;
		this.color = color;
	}
}
