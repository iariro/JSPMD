package kumagai.md.struts2;

/**
 * ページIDとディスクIDの範囲文字列の対。
 * @author kumagai
 */
public class PageIdAndDiskIdRange
{
	public String pageId;
	public String diskIdRange;

	/**
	 * 指定の値をメンバーに割り当て。
	 * @param pageId ページID
	 * @param diskIdRange ディスクID範囲
	 */
	public PageIdAndDiskIdRange(String pageId, String diskIdRange)
	{
		this.pageId = pageId;
		this.diskIdRange = diskIdRange;
	}

	/**
	 * 指定の値をメンバーに割り当て。
	 * @param pageId ページID
	 * @param diskIdRange ディスクID範囲
	 */
	public PageIdAndDiskIdRange(int pageId, String diskIdRange)
	{
		this.pageId = Integer.toString(pageId);
		this.diskIdRange = diskIdRange;
	}
}
