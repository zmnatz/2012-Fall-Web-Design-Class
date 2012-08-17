import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Zaven Mnatzakanian
 */
public class PieChartApplet extends Applet {
	public void paint(Graphics g) {
		Map<String,Color> colors = new HashMap<String,Color>();
		colors.put("red", Color.RED);
		colors.put("blue", Color.BLUE);
		colors.put("black",Color.BLACK);
		colors.put("yellow", Color.YELLOW);
		colors.put("green", Color.GREEN);
		colors.put("orange", Color.ORANGE);
		colors.put("white", Color.WHITE);
		try{
			PieChart pieChart = new PieChart(Integer.parseInt(this.getParameter("RADIUS")));
			final int pieceCount = Integer.parseInt(this.getParameter("PIECES"));
			for(int i=0;i<pieceCount;i++){
				pieChart.getPieces().add(
					new PiePiece(Integer.parseInt(this.getParameter("PERCENT"+i)),
													colors.get(this.getParameter("COLOR"+i)),
													this.getParameter("DESC"+i)));
			}
			if(!pieChart.isValid() || pieChart.getPieces().size()!=pieceCount)
				throw new RuntimeException("Invalid pie chart");
			
			//Draw pie chart
			pieChart.draw(g,this.getWidth()/2,this.getHeight()/2);
			pieChart.drawLegend(g,5,5);
		} catch(Exception e){
			g.drawString("Incorrect arguments", 25,25);
		}
	}
}
