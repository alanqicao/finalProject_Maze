package finalProject_Maze;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import javax.swing.JTextField;
/**
 * GUI class control the program 
 * @author Qi Cao and Danny Dwyer
 *
 */
public class MazeUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img_go = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/go.png")).getImage().getScaledInstance(90, 30, Image.SCALE_SMOOTH);
	private Image img_quit = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/quit.png")).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
	private Image img_logo = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/logo.png")).getImage().getScaledInstance(290, 120, Image.SCALE_SMOOTH);
	private Image img_restart = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/restart.png")).getImage().getScaledInstance(90, 30, Image.SCALE_SMOOTH);
	private Image img_pause = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/pause.png")).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
	private Image img_size = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/size3.png")).getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH);
	private Image img_slow = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/slow.png")).getImage().getScaledInstance(60, 30, Image.SCALE_SMOOTH);
	private Image img_fast = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/fast.png")).getImage().getScaledInstance(60, 30, Image.SCALE_SMOOTH);
	private Image img_human = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/human2.png")).getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH);
	private Image img_computer = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/computer.png")).getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH);
	private Image img_time = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/time.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_steps = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/steps.png")).getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH);
	private Image img_reset = new ImageIcon(MazeUI.class.getResource("/finalProject_Maze/source/reSet.png")).getImage().getScaledInstance(90, 30, Image.SCALE_SMOOTH);

	private JPanel contentPane;
    private int size;
    private boolean startTiming = false;
    private static JTextField timeText = new Timers();
    private boolean radionButton = false;
    private JRadioButton dFSRadioButton;
    private JRadioButton bFSRadioButton;
    private Thread thread = null;
	private mazeRunnable mazeRun;
	private Thread mazeThread;
	private boolean paseToggle = false;
	private boolean done=false;
	private int stepNumber =0;
	private static JTextField stepText = new StepNumber();
	private boolean firtRun = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeUI frame = new MazeUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MazeUI() {
		size = 20;//defalu
		setBackground(new Color(47, 79, 79));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 897);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getTimeText().setForeground(Color.BLUE);
		getTimeText().setFont(new Font("Dialog", Font.PLAIN, 14));
		getTimeText().setHorizontalAlignment(JTextField.CENTER);
		
		JPanel panelMenu = panelMenu();
		
		JLabel lblcone = new JLabel("");
		lblcone.setIcon(new ImageIcon(img_logo));
		lblcone.setBounds(25, 65, 287, 123);
		
		panelMenu.add(lblcone);			
		JPanel panel = panel(panelMenu);		
		lbNewLabel_1(panel);	
		spinner(panel);			
		lblNewLabel_2(panel);		
		lblNewLabel_4_2_1_2(panel);		
		JPanel panel_1 = panel_1(panelMenu);		
		lblNewLabel(panel_1);
		//panel 2
		JPanel panel_2 = panel_2(panelMenu);	
		lblNewLabel_3(panel_2);		
		radioButton(panel_2);		
		lblNewLabel_4_2_1_2_1(panel_2);	
		lblNewLabel_4_2_1_2_1_1(panel_2);
		//panel 4
		JPanel panel_4 = panel_4(panelMenu);				
		lblNewLabel_4(panel_4,mazeRun);	
		JPanel panel_4_1 = panel_4_1(panelMenu);		
		lblNewLabel_4_1(panel_4_1);
		//panel3
		JPanel panel_3 = panel_3();		
		JPanel panel_4_2 = panel_4_2(panel_3);	
		//panel4_2
		lblNewLabel_4_2(panel_4_2);	
		lblNewLabel_4_3(panel_4_2);		
		JPanel panel_4_2_1 = panel_4_2_1(panel_3);	
		lblNewLabel_4_2_1(panel_4_2_1);	
		lblNewLabel_4_3(panel_4_2_1);
		//panel 4_2_1_1
		JPanel panel_4_2_1_1 = panel_4_2_1_1(panel_3);		
		lblNewLabel_4_2_1_1(panel_4_2_1_1);	
		lblNewLabel_4_3_1_1(panel_4_2_1_1);		
		JPanel panel_4_2_1_1_1 = panel_4_2_1_1_1(panel_3);		
		lblNewLabel_4_2_1_1_1(panel_4_2_1_1_1);		
		lblNewLabel_4_3_1_1(panel_4_2_1_1_1);
		//panel 4_2_2_2
		JPanel panel_4_2_2 = panel_4_2_2(panel_3);		
		lblNewLabel_4_2_2(panel_4_2_2);		
		lblNewLabel_6_1(panel_4_2_2);
		
		
		timeText.setBounds(107, 13, 86, 20);
		panel_4_2_2.add(timeText);
		timeText.setColumns(10);
		
		JPanel panel_4_2_2_1 = panel_4_2_2_1(panel_3);		
		lblNewLabel_4_2_2_1(panel_4_2_2_1);		
		lblNewLabel_6(panel_4_2_2_1);		
		panel_5();
		

	}

	private void radioButton(JPanel panel_2) {
		dFSRadioButton = new JRadioButton("Depth First Search");
		dFSRadioButton.setBackground(new Color(0, 139, 139));
		dFSRadioButton.setForeground(new Color(255, 255, 255));
		dFSRadioButton.setFont(new Font("Dialog", Font.BOLD, 15));
		dFSRadioButton.setBounds(122, 51, 164, 36);
		panel_2.add(dFSRadioButton);
		dFSRadioButton.setSelected(true);

		bFSRadioButton = new JRadioButton("Breadth First Search");
		bFSRadioButton.setBackground(new Color(0, 139, 139));
		bFSRadioButton.setForeground(new Color(255, 255, 255));
		bFSRadioButton.setFont(new Font("Dialog", Font.BOLD, 15));
		bFSRadioButton.setBounds(120, 101, 173, 23);
		panel_2.add(bFSRadioButton);
		bFSRadioButton.setSelected(false);
		ButtonGroup group = new ButtonGroup();
		group.add(bFSRadioButton);
		group.add(dFSRadioButton);
		
		dFSRadioButton.addActionListener(new ActionListener() {	 

			@Override
		    public void actionPerformed(ActionEvent event) {	 
	    	    radionButton = false;		    
		    	System.out.println(radionButton);
		    }
	});
	
			bFSRadioButton.addActionListener(new ActionListener() {				 
			    @Override
			    public void actionPerformed(ActionEvent event) {		 
		    	radionButton = true;
			    System.out.println(radionButton);
			    }
		});
	}

	private void panel_5() {
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBackground(new Color(0, 128, 128));
		panel_5.setBounds(335, 170, 878, 727);
		contentPane.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
	}

	private void lblNewLabel_6(JPanel panel_4_2_2_1) {
		JLabel lblNewLabel_6 = new JLabel("Steps:");
		lblNewLabel_6.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(49, 11, 46, 14);
		panel_4_2_2_1.add(lblNewLabel_6);
	}

	private void lblNewLabel_4_2_2_1(JPanel panel_4_2_2_1) {
		JLabel lblNewLabel_4_2_2_1 = new JLabel("");
		lblNewLabel_4_2_2_1.setIcon(new ImageIcon(img_steps));
		lblNewLabel_4_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_2_1.setBounds(10, 0, 41, 42);
		panel_4_2_2_1.add(lblNewLabel_4_2_2_1);
	}

	private JPanel panel_4_2_2_1(JPanel panel_3) {
		JPanel panel_4_2_2_1 = new JPanel();
		panel_4_2_2_1.setLayout(null);
		panel_4_2_2_1.setBackground(new Color(0, 139, 139));
		panel_4_2_2_1.setBounds(476, 117, 239, 42);
		panel_3.add(panel_4_2_2_1);
		
		
		stepText.setBounds(99, 10, 86, 20);
		panel_4_2_2_1.add(stepText);
		stepText.setColumns(10);
		return panel_4_2_2_1;
	}

	private void lblNewLabel_6_1(JPanel panel_4_2_2) {
		JLabel lblNewLabel_6_1 = new JLabel("Time:");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_6_1.setBounds(58, 14, 46, 14);
		panel_4_2_2.add(lblNewLabel_6_1);
	}

	private void lblNewLabel_4_2_2(JPanel panel_4_2_2) {
		JLabel lblNewLabel_4_2_2 = new JLabel("");
		lblNewLabel_4_2_2.setIcon(new ImageIcon(img_time));
		lblNewLabel_4_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_2.setBounds(10, 0, 41, 42);
		panel_4_2_2.add(lblNewLabel_4_2_2);
	}

	private JPanel panel_4_2_2(JPanel panel_3) {
		JPanel panel_4_2_2 = new JPanel();
		panel_4_2_2.setLayout(null);
		panel_4_2_2.setBackground(new Color(0, 139, 139));
		panel_4_2_2.setBounds(21, 117, 239, 42);
		panel_3.add(panel_4_2_2);
		return panel_4_2_2;
	}

	private void lblNewLabel_4_2_1_1_1(JPanel panel_4_2_1_1_1) {
		JLabel lblNewLabel_4_2_1_1_1 = new JLabel("");
		lblNewLabel_4_2_1_1_1.setIcon(new ImageIcon(img_computer));
		lblNewLabel_4_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_1_1.setBounds(0, 11, 84, 58);
		panel_4_2_1_1_1.add(lblNewLabel_4_2_1_1_1);
	}

	private JPanel panel_4_2_1_1_1(JPanel panel_3) {
		JPanel panel_4_2_1_1_1 = new JPanel();
		panel_4_2_1_1_1.setLayout(null);
		panel_4_2_1_1_1.setBackground(new Color(0, 139, 139));
		panel_4_2_1_1_1.setBounds(541, 26, 262, 80);
		panel_3.add(panel_4_2_1_1_1);
		return panel_4_2_1_1_1;
	}

	private void lblNewLabel_4_3_1_1(JPanel panel_4_2_1_1) {
		JLabel lblNewLabel_4_3_1_1 = new JLabel("");
		lblNewLabel_4_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3_1_1.setBounds(10, 11, 74, 58);
		panel_4_2_1_1.add(lblNewLabel_4_3_1_1);
	}

	private void lblNewLabel_4_2_1_1(JPanel panel_4_2_1_1) {
		JLabel lblNewLabel_4_2_1_1 = new JLabel("");
		lblNewLabel_4_2_1_1.setIcon(new ImageIcon(img_human));
		lblNewLabel_4_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_1.setBounds(10, 11, 84, 58);
		panel_4_2_1_1.add(lblNewLabel_4_2_1_1);
	}

	private JPanel panel_4_2_1_1(JPanel panel_3) {
		JPanel panel_4_2_1_1 = new JPanel();
		panel_4_2_1_1.setLayout(null);
		panel_4_2_1_1.setBackground(new Color(0, 139, 139));
		panel_4_2_1_1.setBounds(319, 26, 215, 80);
		panel_3.add(panel_4_2_1_1);
		{
			JLabel lblNewLabel_4_2 = new JLabel("");
			lblNewLabel_4_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					resetTimer();
					mazeRun.setRadionButton(radionButton);
					mazeRun.setSize(size);
					mazeRun.setMenu(3);
					mazeThread = new Thread(mazeRun);
					mazeThread.start();
					setStartTiming(!isStartTiming());
					((Timers) getTimeText()).start();
					//Reset button
				}
			});
			lblNewLabel_4_2.setIcon(new ImageIcon(img_reset));
			lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_4_2.setBounds(82, 11, 123, 58);
			panel_4_2_1_1.add(lblNewLabel_4_2);
		}
		return panel_4_2_1_1;
	}

	private void lblNewLabel_4_2_1(JPanel panel_4_2_1) {
		JLabel lblNewLabel_4_2_1 = new JLabel("");
		
		lblNewLabel_4_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				paseToggle = !paseToggle;
				if(paseToggle) {
					mazeThread.suspend();;
					((Timers) getTimeText()).stop();
				}else {
					mazeThread.resume();
					((Timers) getTimeText()).proceed();
				}				
			}
		});
		lblNewLabel_4_2_1.setIcon(new ImageIcon(img_pause));
		lblNewLabel_4_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1.setBounds(10, 11, 119, 58);
		panel_4_2_1.add(lblNewLabel_4_2_1);
	}

	private JPanel panel_4_2_1(JPanel panel_3) {
		JPanel panel_4_2_1 = new JPanel();
		panel_4_2_1.setLayout(null);
		panel_4_2_1.setBackground(new Color(0, 139, 139));
		panel_4_2_1.setBounds(170, 26, 139, 80);
		panel_3.add(panel_4_2_1);
		return panel_4_2_1;
	}

	private void lblNewLabel_4_3(JPanel panel_4_2) {
		JLabel lblNewLabel_4_3 = new JLabel("");
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3.setBounds(10, 11, 119, 58);
		panel_4_2.add(lblNewLabel_4_3);
	}

	private void lblNewLabel_4_2(JPanel panel_4_2) {
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetTimer();
				mazeRun.setRadionButton(radionButton);
				mazeRun.setMenu(2);
				mazeThread = new Thread(mazeRun);
				mazeThread.start();
				setStartTiming(!isStartTiming());
				((Timers) getTimeText()).start();
				// restart button
			}
		});
		lblNewLabel_4_2.setIcon(new ImageIcon(img_restart));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setBounds(10, 11, 119, 58);
		panel_4_2.add(lblNewLabel_4_2);
	}

	private JPanel panel_4_2(JPanel panel_3) {
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setLayout(null);
		panel_4_2.setBackground(new Color(0, 139, 139));
		panel_4_2.setBounds(21, 26, 139, 80);
		panel_3.add(panel_4_2);
		return panel_4_2;
	}

	private JPanel panel_3() {
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 128));
		panel_3.setBounds(335, 0, 878, 170);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		return panel_3;
	}

	private void lblNewLabel_4_1(JPanel panel_4_1) {
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				System.exit(0);		
				// TODO QUIT!!! BUTTON
			}
		});
		lblNewLabel_4_1.setIcon(new ImageIcon(img_quit));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setBounds(10, 11, 119, 58);
		panel_4_1.add(lblNewLabel_4_1);
	}

	private JPanel panel_4_1(JPanel panelMenu) {
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(new Color(0, 139, 139));
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(187, 550, 139, 80);
		panelMenu.add(panel_4_1);
		return panel_4_1;
	}

	private void lblNewLabel_4(JPanel panel_4, Runnable runnable) {
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(firtRun) {
				mazeRun = new mazeRunnable(size,radionButton);
				mazeRun.setMenu(1);;
				mazeThread = new Thread(mazeRun);
				
				mazeThread.start();
				setStartTiming(!isStartTiming());
				((Timers) getTimeText()).start();
				firtRun = false;
				}else {
					resetTimer();
					mazeRun.setRadionButton(radionButton);
					mazeRun.setSize(size);
					mazeRun.setMenu(3);
					mazeThread = new Thread(mazeRun);
					mazeThread.start();
					setStartTiming(!isStartTiming());
					((Timers) getTimeText()).start();
				}
				//Go button!!!!
			}
		});
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(img_go));
		lblNewLabel_4.setBounds(10, 11, 119, 58);
		panel_4.add(lblNewLabel_4);
	}

	private JPanel panel_4(JPanel panelMenu) {
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 139, 139));
		panel_4.setBounds(25, 550, 139, 80);
		panelMenu.add(panel_4);
		panel_4.setLayout(null);
		return panel_4;
	}

	private void lblNewLabel_4_2_1_2_1_1(JPanel panel_2) {
		JLabel lblNewLabel_4_2_1_2_1_1 = new JLabel("");
		lblNewLabel_4_2_1_2_1_1.setIcon(new ImageIcon(img_fast));
		lblNewLabel_4_2_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_2_1_1.setBounds(8, 98, 106, 44);
		panel_2.add(lblNewLabel_4_2_1_2_1_1);
	}

	private void lblNewLabel_4_2_1_2_1(JPanel panel_2) {
		JLabel lblNewLabel_4_2_1_2_1 = new JLabel("");
		lblNewLabel_4_2_1_2_1.setIcon(new ImageIcon(img_slow));
		lblNewLabel_4_2_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_2_1.setBounds(10, 43, 106, 44);
		panel_2.add(lblNewLabel_4_2_1_2_1);
	}



	private void lblNewLabel_3(JPanel panel_2) {
		JLabel lblNewLabel_3 = new JLabel("Select the  Algorithm to solve the Maze");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_3.setBounds(0, 11, 293, 21);
		panel_2.add(lblNewLabel_3);
		
	}

	private JPanel panel_2(JPanel panelMenu) {
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 139, 139));
		panel_2.setBounds(25, 333, 301, 147);
		panelMenu.add(panel_2);
		panel_2.setLayout(null);
		return panel_2;
	}

	private void lblNewLabel(JPanel panel_1) {
		JLabel lblNewLabel = new JLabel("Welcome to Amazing Maze");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 128, 128));
		panel_1.add(lblNewLabel);
	}

	private JPanel panel_1(JPanel panelMenu) {
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 11, 300, 43);
		panelMenu.add(panel_1);
		return panel_1;
	}

	private void lblNewLabel_4_2_1_2(JPanel panel) {
		JLabel lblNewLabel_4_2_1_2 = new JLabel("");
		lblNewLabel_4_2_1_2.setIcon(new ImageIcon(img_size));
		lblNewLabel_4_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_2.setBounds(10, 43, 103, 55);
		panel.add(lblNewLabel_4_2_1_2);
	}

	private void lblNewLabel_2(JPanel panel) {
		JLabel lblNewLabel_2 = new JLabel("Size:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_2.setBounds(130, 57, 51, 14);
		panel.add(lblNewLabel_2);
	}
	
	public static JTextField getTimeText() {
		return timeText;
	}
	public boolean isStartTiming() {
		return startTiming;
	}
	
	public void setStartTiming(boolean startTiming) {
		this.startTiming = startTiming;
	}
	
	public void resetTimer() {
		setStartTiming(false);
		getTimeText().setText("00:00:00");
		((Timers) timeText).restart();
	}
	
	public static JTextField getstepNumber() {
		return stepText;
	}
	
	//game stop
	public void GameOver() {
		((Timers) getTimeText()).stop();
	}

	private void spinner(JPanel panel) {
		JSpinner spinner = new JSpinner();
		spinner.setBounds(203, 51, 74, 33);
		panel.add(spinner);
		spinner.setValue(20);
		
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				size = (int) spinner.getValue();
				
			}
		});
	}

	private void lbNewLabel_1(JPanel panel) {
		JLabel lblNewLabel_1 = new JLabel("Please set the size of the Maze");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1.setBounds(38, 11, 206, 14);
		panel.add(lblNewLabel_1);
	}

	private JPanel panel(JPanel panelMenu) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(25, 199, 301, 123);
		panelMenu.add(panel);
		panel.setLayout(null);
		return panel;
	}

	private JPanel panelMenu() {
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(0, 128, 128));
		panelMenu.setBounds(0, 0, 336, 897);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		return panelMenu;
	}
	
	/**
	 * @return the thread
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * @param thread the thread to set
	 */
	private void setThread(Thread thread) {
		this.thread = thread;
	}

	public void setThreadStop() {
		if (getThread() != null) {

			thread.interrupt();
			setThread(null);
		}
	}

	public int getStepNumber() {
		return stepNumber;
	}
}
