import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * @author Zaven Mnatzakanian
 */
public class OrderForm extends Applet implements ActionListener,ItemListener {
	private static Dialog d;
	List items;
	Choice itemIds;
	TextField quantity;
	TextArea instructions;
	CheckboxGroup creditCards;
	ArrayList<Item> inventory = new ArrayList<Item>();
	
	public void init(){
		setLayout(null);
		
		//Initialize values
		inventory.add(new Item("Hammer",10,1.99));
		inventory.add(new Item("Nails",15,2.79));
		inventory.add(new Item("Foo Bar",12,6.59));
		
		//Initialize form components
		itemIds = new Choice();
		items = new List(4);
		items.add("");
		itemIds.add("");
		for(Item i : inventory){
			items.add(i.getDesc());
			itemIds.add(i.getItemId());
		}
		items.addItemListener(this);
		itemIds.addItemListener(this);
		quantity = new TextField("",4);
		instructions = new TextArea("",4,40);
		creditCards = new CheckboxGroup();
		creditCards = new CheckboxGroup();
		ArrayList<Checkbox> ccTypes = new ArrayList<Checkbox>();
		ccTypes.add(new Checkbox("Visa",creditCards,false));
		ccTypes.add(new Checkbox("Master Card",creditCards,false));
		ccTypes.add(new Checkbox("Discover",creditCards,false));
		
		//Add components to applet
		addField("Item Number",itemIds,5,5,490,20);
		addField("Item Description",items,5,30,490,80);
		addField("Quantity",quantity,5,115,490,20);
		addField("Special Instructions",instructions,5,140,490,200);
		Panel ccPanel = new Panel(new GridLayout(1,3));
		for(Checkbox c : ccTypes)
			ccPanel.add(c);
		addField("Credit Card Type",ccPanel,5,345,490,25);
		
		Button submit = new Button();
		submit.setLabel("Submit Order");
		submit.setBounds(150,380,100,25);
		submit.addActionListener(this);
		add(submit);
	}

	private void addField(String labelText,Component c,int x,int y,int w,int h){
		final int LABEL_WIDTH = 130;
		Label label = new Label(labelText+":",Label.RIGHT);
		label.setBounds(x,y,LABEL_WIDTH,h);
		c.setBounds(x+LABEL_WIDTH,y,w-LABEL_WIDTH,h);
		add(label);
		add(c);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(this.validateForm() && this.submit()){
			//Clear form if valid and submit successful
			clearForm();
		}
	}
	
	//TODO: Next assignment
	private boolean submit(){
		return true;
	}
	
	private void clearForm(){
		items.select(0);
		itemIds.select(0);
		quantity.setText("");
		instructions.setText("");
		creditCards.setSelectedCheckbox(null);
	}
	
	//Validates order form
	private boolean validateForm(){
		if(items.getSelectedIndex()==0 || itemIds.getSelectedIndex()==0){
			alertError("You must select an item to place an order");
			return false;
		}
		try{
			Integer q = Integer.parseInt(quantity.getText());
			if(q<1){
				alertError("You must enter a positive quantity.");
				return false;
			}
		}catch(NumberFormatException e){
			alertError("The quantity entered must be in numerical format");
			return false;
		}
		if(creditCards.getSelectedCheckbox()==null){
			alertError("You must select a credit card.");
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getSource().equals(itemIds))
			items.select(itemIds.getSelectedIndex());
		else if(arg0.getSource().equals(items))
			itemIds.select(items.getSelectedIndex());
	}
	
	public void alertError(String msg){
		d = new Dialog(new Frame(),"Order Validation Error",true);
		d.setLocationRelativeTo(this);
		d.setLayout(new GridLayout(2,1));
		Button ok = new Button ("OK");
		ok.addActionListener ( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				OrderForm.d.setVisible(false);
			}
		});
		d.add( new Label (msg,Label.CENTER));
		d.add( ok );
		
		d.pack();
		d.setVisible(true);
	}
}
