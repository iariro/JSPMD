package kumagai.md.struts2;

import kumagai.md.*;

/**
 * 交響曲一覧表示用行データ。
 * @author kumagai
 */
public class ComposerRecord2
{
	public final String composer;
	public final boolean completed;
	public int [] numbers;
	public String etc;

	/**
	 * メンバーに値を割り当てる。
	 * @param record レコード
	 * @param max 表示欄最大数
	 */
	public ComposerRecord2(ComposerRecord record, int max)
	{
		this.composer = record.composer;
		this.completed = record.isCompleted();
		this.numbers = new int [max];
	}

	/**
	 * 各ナンバーの録音状況を割り当て。
	 * @param index ０始まりのインデックス
	 * @param value 0=途中まで録音 1=全部録音 2=未録音 3=存在しない
	 */
	public void setNumbers(int index, int value)
	{
		numbers[index] = value;
	}
}
