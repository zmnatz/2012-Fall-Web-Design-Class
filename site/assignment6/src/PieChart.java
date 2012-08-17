import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class PieChart {
	private int radius;
	private List<PiePiece> pieces = new ArrayList<PiePiece>();
	
	public PieChart(int radius) {
		super();
		this.radius = radius;
	}

	/**
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * @return the pieces
	 */
	public List<PiePiece> getPieces() {
		return pieces;
	}

	/**
	 * @param pieces the pieces to set
	 */
	public void setPieces(List<PiePiece> pieces) {
		this.pieces = pieces;
	}
	
	public boolean isValid(){
		double percentTotal = 0;
		for(PiePiece p : pieces){
			if(!p.isValid()) return false;
			percentTotal += p.getPercent();
		}
		return percentTotal == 100;
	}

	public void draw(Graphics g, int x, int y) {
		int currentAngle = 0;
		for(PiePiece p : pieces){
			g.setColor(p.getColor());
			int degrees = p.getPercent() * 360 / 100;
			g.fillArc(x-radius, y-radius, radius*2, radius*2, currentAngle, degrees);
			currentAngle+=degrees;
		}
		
	}

	public void drawLegend(Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 130,pieces.size()*15);
		for(PiePiece p : pieces){
			g.setColor(p.getColor());
			g.fillRect(x+3, y+3, 9,9);
			g.setColor(Color.BLACK);
			g.drawString(p.getDesc(),x+15,y+12);
			y+=15;
		}
		
	}
	
}
