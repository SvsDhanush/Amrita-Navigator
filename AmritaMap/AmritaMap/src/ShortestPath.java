import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import javax.swing.*;

import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit; 

@SuppressWarnings("serial")
public class ShortestPath extends JFrame {
	
// ***** classes *****
 JFrame frame=new JFrame();
 Edge edge=new Edge();
 Panel panel=new Panel(){  
     public void paintComponent(Graphics g) {  
         Image img = Toolkit.getDefaultToolkit().getImage(  
        		 ShortestPath.class.getResource("/images/amritaMap.png"));  
         g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
   }  
};
 
// ***** lists *****
 EdgeList<Edge> edgeList=new EdgeList();
 DotList<Dot> dotList=new DotList();

 // ***** mouse listeners *****
 ClickListner cl=new ClickListner();
 ClickListner4 cl4=new ClickListner4();
 
 // ***** buttons *****
 JRadioButton rbtnStart =new JRadioButton("Start");
 JRadioButton rbtnShortestPath=new JRadioButton("Shortest Path");
 
 //***JTextField***//
 JTextArea jWeight=new JTextArea(5,20);
 
 //*****jlabel*****
 JLabel label=new JLabel();
 
 //**** two nodes to get the shortest path ****
 Dot node1;
 Dot node2;
 
 //**** constructor ****
 public ShortestPath() {
	 super("Shotest Path GUI Group-15");
	 setFrame();
	 
 }
 public void setFrame() {
	 this.setSize(700, 700);
	 this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	 this.getContentPane().setLayout(null); 
	 this.setBackground(Color.WHITE);
	 this.addPanel(); 
	 this.setButtons();
	 this.setLabe();
	 this.setVisible(true); 
 }
 
 public void paint(Graphics g) {
	 super.paint(g);
	 //	**draws the vertex**
	 if(dotList.size()>0) {
		for(int i=0;i<dotList.size();i++) {
			dotList.get(i).draw(g);
		}
		}
	 
	 //	**draws the edges**
	 if(edgeList.size()>0) {
		 for(int i=0;i<edgeList.size();i++) {
			 edgeList.get(i).draw(g);
		 }
	 }
 }
 public void addPanel() {
	 panel.setBackground(Color.WHITE);
	 panel.setBounds(230, 10, 440, 600);
	 panel.setVisible(true);
	 this.add(panel);
 }
 
 //*************************************** Add buttons to the frame ****************************************************
 public void setButtons() {
	 
	 
	 //************************************ RADIO BUTTONS ************************************************     
	 //	**radio button to add vertex**
	 rbtnStart.setBounds(10, 100, 200, 50);
	 this.add(rbtnStart);
	  
	 rbtnShortestPath.setBounds(10,150,200,50);
	 this.add(rbtnShortestPath);
	  
	 //****************** Grouping radio buttons ******************
	 ButtonGroup bg=new ButtonGroup();
	 bg.add(rbtnStart);
	 bg.add(rbtnShortestPath);
	 
	 //**************** selecting radio to Start *********************
	 rbtnStart.addActionListener(new ActionListener() {
		 @Override	
		 public void actionPerformed(ActionEvent e) {
			setLabel(Color.RED,"click on the map to view paths.");
			panel.removeMouseListener(cl4);
			panel.addMouseListener(cl);
			}
		 });
	 
	 
	 //******* selection this buttion will give the shortest path *************
	 rbtnShortestPath.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			 edgeDefaultColor();
			 setLabel(Color.RED,"Click on a starting vertex and the click on the destination.");
			 
			 node1=new Dot();
			 node2=new Dot();
			 
			 panel.removeMouseListener(cl);
			 panel.addMouseListener(cl4);
 
		 }
	 });
    }
 
 	//*****seting up the JLabel*****
 	public void setLabe() {
 		label.setBounds(350,670,300,30);
 		label.setForeground(Color.RED);
 		panel.add(label);
 }
 	public void setLabel(Color c,String message)
 	{ 
 		label.setForeground(c);
 		label.setText(message);
 	}
 	//******************************* change back the color of the dots to red *****************************************
 	public void dotDefaultColor() {
		for(int i=0;i<dotList.size();i++) {
			dotList.get(i).setColor(Color.RED);
		}
 	}
 	
 	//******************************* change back the color of the edges to blue *****************************************
 	public void edgeDefaultColor() {
 		for(int i=0;i<edgeList.size();i++) {
 			edgeList.get(i).setColor(Color.BLUE);
 		}
 	}
 	
 	//******************************************* method to add all Edges ***********************************************
 	public void addAllEdges() {
 		edgeList=new EdgeList();
 		int size=dotList.size();
 		for(int i=0;i<size;i++) {
 			for(int j=i+1;j<size;j++) {
 				edgeList.add(new Edge(dotList.get(i),dotList.get(j)));
 				edgeList.get(i).calcWeight();
 				repaint();
 			}
 		}
 	}
 	
 	//************************************* this method will remove the mouse adapters from the panel ********************
 	public void removeMouseAdapters() {
 		 panel.removeMouseListener(cl);
		 panel.removeMouseListener(cl4);
 	}
 	//************************************************ main function ****************************************************
	public static void main(String[] args) {
        ShortestPath g = new ShortestPath();
	}

	
	//************************************************ mouse adapters classes for dot************************************
	protected class ClickListner extends MouseAdapter{
		
		ClickListner(){
			super();
		}
		
		public void mouseClicked(MouseEvent e) {

			dotList.deleteAllElements();
			edgeList.deleteAllElements();
			
			Dot staff=new Dot(374, 475);
			Dot deanOfc=new Dot(369, 339);
			Dot parking=new Dot(368, 222);
			Dot rightOfParking=new Dot(437, 202);
			Dot topLeft=new Dot(420, 52);
			Dot topRight=new Dot(494, 40);
			Dot rightRightOfParking=new Dot(522, 219);
			Dot chemTop=new Dot(626, 279);
			Dot chem=new Dot(630, 329);
			Dot canteen=new Dot(640, 473);
			Dot canteenNear =new Dot(570, 497);
			Dot groundRight=new Dot(649, 603);
			Dot groundLeft=new Dot(558, 605);
			Dot postOffice=new Dot(461, 611);

			dotList.add(staff);
			dotList.add(deanOfc);
			dotList.add(parking);
			dotList.add(rightOfParking);
			dotList.add(topLeft);
			dotList.add(topRight);
			dotList.add(rightRightOfParking);
			dotList.add(chemTop);
			dotList.add(chem);
			dotList.add(canteen);
			dotList.add(canteenNear);
			dotList.add(groundRight);
			dotList.add(groundLeft);
			dotList.add(postOffice);
			
			edgeList.add(new Edge(staff, deanOfc));
			edgeList.add(new Edge(deanOfc, parking));
			edgeList.add(new Edge(parking, rightOfParking));
			edgeList.add(new Edge(rightOfParking, topLeft));
			edgeList.add(new Edge(topLeft, topRight));
			edgeList.add(new Edge(topRight, rightRightOfParking));
			edgeList.add(new Edge(rightRightOfParking, chemTop));
			edgeList.add(new Edge(chemTop, chem));
			edgeList.add(new Edge(chem, canteen));
			edgeList.add(new Edge(canteen, groundRight));
			edgeList.add(new Edge(groundRight, groundLeft));
			edgeList.add(new Edge(groundLeft, postOffice));
			edgeList.add(new Edge(postOffice, staff));
			
			edgeList.add(new Edge(deanOfc, chem));
			edgeList.add(new Edge(canteen, canteenNear));
			edgeList.add(new Edge(canteenNear, groundLeft));
			
			Dot dot=new Dot();
			int x=e.getX()+225;
			int y=e.getY()+25;
			dot.setColor(Color.RED);
			repaint();
		}
	 }
	
	//*********************** mouse Adapter to Get the shortest path from one node to another ***************************
	protected class ClickListner4 extends MouseAdapter
	{
		public ClickListner4() {
			super();
		}
		
		public void mouseClicked(MouseEvent e) {
			Dijkstra dj=new Dijkstra();
			dj.execute(node1);
			edgeDefaultColor();
			int x=e.getX()+225;
			int y=e.getY()+25;
			int i;
			node2=new Dot();
			
			for(i=0;i<dotList.size();i++) {
				int z1=dotList.get(i).getX(),z2=dotList.get(i).getY();
				
				if(((x<=z1+10)&&(x>=z1-10))&&((y<=z2+10)&&(y>=z2-10))) {
					
					if(node1.isEmpty()) {
						node1=dotList.get(i);
						dotList.get(i).setColor(Color.GREEN);
						setLabel(Color.RED,"Click on destination vertex to find the shortest path.");
					}
					else if(node2.isEmpty()) 
						{node2=dotList.get(i);
						dotList.get(i).setColor(Color.GREEN);
						}
					repaint();
					break;
				}
				
			}
			
			//add the methods from dijkstras algorithm
			if(!node1.isEmpty()&&!node2.isEmpty()){
				
				dj.getPath(node1,node2);
			}
		}
	}
	
	
	//************************************* Implementation of Dijkstra's Algorithm **************************************

	protected class Dijkstra{
		private ArrayList<Dot> nodes;
		private EdgeList<Edge> edges;
		private Set<Dot> visitedNodes;
		private Set<Dot> unvisitedNodes;
		private Map<Dot, Integer> totalWeight;
		private Map<Dot,Dot> prevNodes;
		
		public Dijkstra() {
			nodes=new ArrayList<>();
			for(int i=0;i<dotList.size();i++) {
				Dot temp =new Dot(dotList.get(i).getX(),dotList.get(i).getY());
				this.nodes.add(temp);
			}
			this.edges=edgeList;
		}
		
		public void execute(Dot start) {
			visitedNodes=new HashSet<>();
			unvisitedNodes=new HashSet<>();
			totalWeight=new HashMap<>();
			prevNodes=new HashMap<>();
			this.totalWeight.put(start, 0);
			unvisitedNodes.add(start);
			while(unvisitedNodes.size()>0) {
				Dot node=getMinimum(unvisitedNodes);
				visitedNodes.add(node);
				unvisitedNodes.remove(node);
				findMinimalWeights(node);
			}
		}
		
		private Dot getMinimum(Set<Dot> dots) {
			Dot minimum=null;
			for(Dot dot:dots) {
				if(minimum==null) {
					minimum=dot;
				}else{
					if(this.getShortestDistance(dot)<this.getShortestDistance(minimum)) {
						minimum=dot;
					}
				}
			}
			return minimum;
		}

		public void findMinimalWeights(Dot node) {
			DotList<Dot> adjNodes=getNeighbours(node);
			for(int i=0;i<adjNodes.size();i++) {
				Dot target=adjNodes.get(i);
				if(getShortestDistance(target)>getShortestDistance(node)+getDistance(node,target)) {
					totalWeight.put(target, getShortestDistance(node)+getDistance(node,target));
					prevNodes.put(target, node);
					unvisitedNodes.add(target);
				}
			}
		}

		private int getDistance(Dot node, Dot target)  {
			int weight=0;
			Edge edge=new Edge(node,target);
			int index=this.getIndexOf(edge);
			if(index!=-1)         
			weight=edges.get(index).getWeight();
			else {            	           
				throw new RuntimeException("no such edge");
			}
			return weight;
		}

		private int getShortestDistance(Dot node) {
			Integer d=totalWeight.get(node);
			if(d==null) {
				return Integer.MAX_VALUE;
			}
			else
			return d;
		}

		private DotList<Dot> getNeighbours(Dot node) {
			DotList<Dot> temp=new DotList<>();
			for(int i=0;i<nodes.size();i++) {
				if(!visitedNodes.contains(nodes.get(i))) {
					if(this.checkNeighbour(new Edge(node,nodes.get(i)))==true){
					temp.add(nodes.get(i));
				}
				}
				
			}
	
			return temp;
		}
		
		public Map<Dot, Dot> getPrev(){
			return this.prevNodes;
		}
		
		public boolean checkNeighbour(Edge e) {
			int x1,x2,y1,y2,a,b,c,d;
			boolean check=false;
			x1=e.getPointA().getX();
			x2=e.getPointB().getX();
			y1=e.getPointA().getY();
			y2=e.getPointB().getY();
			for(int i=0;i<edges.size();i++) {
				a=edges.get(i).getPointA().getX();
				b=edges.get(i).getPointB().getX();
				c=edges.get(i).getPointA().getY();
				d=edges.get(i).getPointB().getY();
				if(a==x1&&b==x2&&c==y1&&d==y2) {
					check= true;
					break;
				}
				if(a==x2&&b==x1&&c==y2&&d==y1) {
					check= true;
					break;
				}
			}
			return check;
			}
		
		public int getIndexOf(Edge e) {
			int x1,x2,y1,y2,a,b,c,d,index=-1;
			x1=e.getPointA().getX();
			x2=e.getPointB().getX();
			y1=e.getPointA().getY();
			y2=e.getPointB().getY();
			for(int i=0;i<edges.size();i++) {
				a=edges.get(i).getPointA().getX();
				b=edges.get(i).getPointB().getX();
				c=edges.get(i).getPointA().getY();
				d=edges.get(i).getPointB().getY();
				if(a==x1&&b==x2&&c==y1&&d==y2) {
					index=i;
					break;
				}
				if(a==x2&&b==x1&&c==y2&&d==y1) {
					index= i;
					break;
				}
			}
			return index;
		}
		
		//get the shortest path from one node to another
		public void getPath( Dot a,Dot b) {
			dotDefaultColor();
			if(!b.isEmpty()) {
				int x,y;
				ArrayList<Dot> keys=new ArrayList<>(prevNodes.keySet());
				LinkedList<Dot> path=new LinkedList<>();
				Dot start=b;
				for(int i=0;i<keys.size();i++) {
					x=keys.get(i).getX();
					y=keys.get(i).getY();
					if(x==start.getX()&&y==start.getY()) {
						start=keys.get(i);
						break;
				}
				}
				if(prevNodes.get(start)==null) {
					return;
				}
				path.add(start);
			while(prevNodes.get(start)!=null) {
				path.add(prevNodes.get(start));
				start=prevNodes.get(start);
			}
			Collections.reverse(path);

			int i;
			for(i=0;i<path.size();i++) {
				try {
				int index=this.getIndexOf(new Edge(path.get(i),path.get(i+1)));
				edgeList.get(index).setColor(Color.RED);
				
				}
				catch(IndexOutOfBoundsException e){
					setLabel(Color.RED,"Error!!!"+e.getMessage());
				}
			}
				repaint();
				setLabel(Color.GREEN,"Shortest path found ");
			}
		}
	}
	
	//****************************************** DotList Class contains the list of Dot (the vertex) *************************************
	protected class DotList<Dot> {
		private ArrayList<Dot> dotList;
		
		public DotList() {
			dotList=new ArrayList<>();
		}
		public void add(Dot d) {
			dotList.add(d);
		}
		
		public void deleteAllElements() {
			dotList.clear();
		}
		public int size() {
			return dotList.size();
		}
		public Dot get(int i){
			return dotList.get(i);
		}
		public ArrayList<Dot> getList(){
			return dotList;
		}
		public Iterator<Dot> iterator() {
			ArrayList<Dot> list=new ArrayList<>();
			for(int i=0;i<dotList.size();i++) {
				list.add(dotList.get(i));
			}
			return list.iterator();
		}
		}

	//********************************************** this class contains the vertex *********************************************
	protected class Dot {
		private int x;
		private int y;
		private Color color;

		public Dot() {
			this.color=color.RED;
			x=y=0;
		}
		public Dot(int a, int b) {
			this.color=color.RED;
			x=a;
			y=b;
		}
		public void setX(int a) {
			x=a;
		}
		public void setY(int b) {
			y=b;
		}
		public void setColor(Color c) {
			this.color=c;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public boolean isEmpty() {
			if(this.x==0&&this.y==0)
				return true;
			else return false;
		}
		public String getColor() {
			return color.toString();
		}
		public void draw(Graphics g) {
			g.setColor(color);
			g.fillOval(x, y, 10, 10);
			
		}
	}
	
	//********************************************* this class contains the edge ******************************************
	protected class Edge {
		private int x1,y1,x2,y2;
		private int weight;
		Color color=Color.BLUE;
		Dot dot;

		public Edge() {
			x1=x2=y1=y2=0;
			weight=0;
			dot=new Dot();
		}
		public Edge(Dot a,Dot b) {
			x1=a.getX()+5;
			x2=b.getX()+5;
			y1=a.getY()+5;
			y2=b.getY()+5;
			this.calcWeight();
		}

		public void setA(Dot a) {
			x1=a.getX()+5;
			y1=a.getY()+5;
		}

		public void setB(Dot b) {
			x2=b.getX()+5;
			y2=b.getY()+5;
		}

		public void setColor(Color c) {
			this.color=c;
		}
		public int calcWeight() {	weight=(int)Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
			return weight;
		}
		public int getWeight() {
		return weight;	
		}

		public void setWeight(int weight) {
			this.weight=weight;
		}

		public void draw(Graphics g) {
			int midPointX=(int)((x2+x1)/2);
			int midPointY=(int)((y2+y1)/2);
			g.setColor(Color.BLACK);
			g.drawString(Double.toString(weight*0.897) + "m", midPointX, midPointY);
			g.setColor(color);
			g.drawLine(x1, y1, x2, y2);
		}

		public Dot getPointA() {
			Dot A=new Dot(x1,y1);
			return A;
		}

		public Dot getPointB() {
		Dot B=new Dot(x2,y2);
		return B;
		}

		public boolean hasA() {
			return (x1>0&&y1>0);	
		}

		public boolean hasB() {
			return (x2>0&&y2>0);	
		}

		//************ check if the user clicked on this edge or not *****************
		public boolean checkEdge(int x, int y) {
			
			int  i,a,b;
			if(this.equal(x,y)) return true;
			
			for(i=1;i<=20;i++) {
				a=x;b=y+i;
				if(this.equal(a,b)) return true;
				a=x;b=y-i;
				if(this.equal(a,b)) return true;
				a=x-i;b=y;
				if(this.equal(a,b)) return true;
				a=x+i;b=y;
				if(this.equal(a,b)) return true;
				a=x+i;b=y+i;
				if(this.equal(a,b)) return true;
				a=x-i;b=y-i;
				if(this.equal(a,b)) return true;
				a=x+i;b=y-i;
				if(this.equal(a,b)) return true;
				a=x-i;b=y+i;
				if(this.equal(a,b)) return true;
			}
			if(i>=10) {
				return false;
			}
			else return true;	
		}

		public boolean equal(int x,int y) {
			int c=((y)-y1)*(x2-x1);
			int d=(y2-y1)*((x)-x1);
			if(c>d-20&&c<d+20) return true;
			else return false;
		}
		}
	

	//*********************************************** this class contains the list of the Edge ******************************************
	public class EdgeList<Edge> {
		private ArrayList<Edge> edgeList;

		public EdgeList() {
			edgeList=new ArrayList<>();
		}

		public void add(Edge e) {
			edgeList.add(e);
		}

		public void deleteAllElements() {
			edgeList.clear();
		}
		
		public Edge get(int i) {
			return edgeList.get(i);
		}

		public ArrayList<Edge> getList(){
			return edgeList;
		}

		public int getIndex(Edge e) {
			return edgeList.indexOf(e);
		}


		public int size() {
			return edgeList.size();
		}
		}

	
	protected class Panel extends JPanel{
		public Panel() {
			this.setBounds(230, 10, 440, 600);
			
			this.setBackground(Color.GRAY);
			this.setVisible(true);

		 }
	}

}