import java.applet.Applet;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Zaven
 *
 */
public class ImageMap extends Applet implements MouseListener, MouseMotionListener{
	Image background; 
	String target;
	Set<HotZone> hotZones = new HashSet<HotZone>();
	public void init(){
		background = getImage(this.getDocumentBase(),this.getParameter("BACKGROUND"));
		target = this.getParameter("TARGET");
		String baseURL = this.getDocumentBase().toString();
		baseURL = baseURL.substring(0,baseURL.lastIndexOf("/")+1);
		int i=1;
		for(String url = this.getParameter("URL"+i);url!=null;url=this.getParameter("URL"+i)){
			String coords[] = this.getParameter("COORD"+i).split(",");
			try {
				hotZones.add(new HotZone(Integer.parseInt(coords[0]),Integer.parseInt(coords[1]),
							Integer.parseInt(coords[2]),Integer.parseInt(coords[3]),
							new URL(baseURL+url)));
			} catch(MalformedURLException e){throw new RuntimeException(e);}
			i++;
		}
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(background,0,0,this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		for(HotZone hz : hotZones){
			if(hz.contains(arg0.getPoint())){
				this.getAppletContext().showDocument(hz.getUrl(),target);
				return;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		for(HotZone hz : hotZones){
			if(hz.contains(arg0.getPoint())){
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				showStatus(hz.getUrl().toString());
				return;
			}
		}
		setCursor(Cursor.getDefaultCursor());
		showStatus("");
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) { }

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) { }

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) { }

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) { }

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) { }
}
