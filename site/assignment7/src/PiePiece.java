import java.awt.Color;

/**
 * 
 */

/**
 * @author Zaven Mnatzakanian
 *	
 */
public class PiePiece {
	private int percent;
	private Color color;
	private String desc;
	
	public PiePiece(int percent, Color color, String desc) {
		super();
		this.percent = percent;
		this.color = color;
		this.desc = desc;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isValid(){
		return percent>0 && color!=null && desc!=null;
	}
}
