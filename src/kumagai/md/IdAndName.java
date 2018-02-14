package kumagai.md;

/**
 * IDと名前の対。
 * @author kumagai
 */
public class IdAndName
{
	private int id;
	private String name;

	/**
	 * メンバーに値を割り当て。
	 * @param id ID
	 * @param name 名前
	 */
	public IdAndName(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	/**
	 * IDを取得。
	 * @return ID
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * IDを割り当て。
	 * @param id ID
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * 名前を取得。
	 * @return 名前
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 名前を割り当て。
	 * @param name 名前
	 */
	public void setName(String name)
	{
		this.name = name;
	}
}
