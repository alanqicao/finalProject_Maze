package finalProject_Maze;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
/**
 * 
 * @author Danny and Qi
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
	private JPanel contentPane;
    int size;
    
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
		size = 15;//defalu
		setBackground(new Color(47, 79, 79));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 897);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMenu = panelMenu();
		
		JLabel lblcone = new JLabel("");
		lblcone.setIcon(new ImageIcon(img_logo));
		lblcone.setBounds(25, 65, 287, 123);
		
		panelMenu.add(lblcone);	
		
		JPanel panel = panel(panelMenu);
		
		lbNewLabel_1(panel);
		
		spinner(panel);
			
		JLabel lblNewLabel_2 = new JLabel("Size:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_2.setBounds(130, 57, 51, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4_2_1_2 = new JLabel("");
		lblNewLabel_4_2_1_2.setIcon(new ImageIcon(img_size));
		lblNewLabel_4_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_2.setBounds(10, 43, 103, 55);
		panel.add(lblNewLabel_4_2_1_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 11, 300, 43);
		panelMenu.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Welcome to Amazing Maze");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 128, 128));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 139, 139));
		panel_2.setBounds(25, 333, 301, 147);
		panelMenu.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Select the  Algorithm to solve the Maze");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_3.setBounds(0, 11, 293, 21);
		panel_2.add(lblNewLabel_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Depth First Search");
		rdbtnNewRadioButton.setBackground(new Color(0, 139, 139));
		rdbtnNewRadioButton.setForeground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setFont(new Font("Dialog", Font.BOLD, 15));
		rdbtnNewRadioButton.setBounds(122, 51, 164, 23);
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Breadth First Search");
		rdbtnNewRadioButton_1.setBackground(new Color(0, 139, 139));
		rdbtnNewRadioButton_1.setForeground(new Color(255, 255, 255));
		rdbtnNewRadioButton_1.setFont(new Font("Dialog", Font.BOLD, 15));
		rdbtnNewRadioButton_1.setBounds(120, 101, 173, 23);
		panel_2.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_4_2_1_2_1 = new JLabel("");
		lblNewLabel_4_2_1_2_1.setIcon(new ImageIcon(img_slow));
		lblNewLabel_4_2_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_2_1.setBounds(10, 43, 106, 44);
		panel_2.add(lblNewLabel_4_2_1_2_1);
		
		JLabel lblNewLabel_4_2_1_2_1_1 = new JLabel("");
		lblNewLabel_4_2_1_2_1_1.setIcon(new ImageIcon(img_fast));
		lblNewLabel_4_2_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_2_1_1.setBounds(8, 98, 106, 44);
		panel_2.add(lblNewLabel_4_2_1_2_1_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 139, 139));
		panel_4.setBounds(25, 550, 139, 80);
		panelMenu.add(panel_4);
		panel_4.setLayout(null);
		
		//create thread
		Runnable runnable=()->{
			MazeModified newMaze=new MazeModified(size);
			newMaze.start();
			newMaze.solveBFS();

		};
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Thread thread = new Thread(runnable);
				thread.start();
				//Go button!!!!
			}
		});
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(img_go));
		lblNewLabel_4.setBounds(10, 11, 119, 58);
		panel_4.add(lblNewLabel_4);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(new Color(0, 139, 139));
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(187, 550, 139, 80);
		panelMenu.add(panel_4_1);
		
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 128));
		panel_3.setBounds(335, 0, 878, 170);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setLayout(null);
		panel_4_2.setBackground(new Color(0, 139, 139));
		panel_4_2.setBounds(21, 26, 139, 80);
		panel_3.add(panel_4_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//TODO Restart!!!!!!Button
			}
		});
		lblNewLabel_4_2.setIcon(new ImageIcon(img_restart));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setBounds(10, 11, 119, 58);
		panel_4_2.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("");
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3.setBounds(10, 11, 119, 58);
		panel_4_2.add(lblNewLabel_4_3);
		
		JPanel panel_4_2_1 = new JPanel();
		panel_4_2_1.setLayout(null);
		panel_4_2_1.setBackground(new Color(0, 139, 139));
		panel_4_2_1.setBounds(170, 26, 139, 80);
		panel_3.add(panel_4_2_1);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("");
		lblNewLabel_4_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO Pause!!!!Button
			}
		});
		lblNewLabel_4_2_1.setIcon(new ImageIcon(img_pause));
		lblNewLabel_4_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1.setBounds(10, 11, 119, 58);
		panel_4_2_1.add(lblNewLabel_4_2_1);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("");
		lblNewLabel_4_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3_1.setBounds(10, 11, 119, 58);
		panel_4_2_1.add(lblNewLabel_4_3_1);
		
		JPanel panel_4_2_1_1 = new JPanel();
		panel_4_2_1_1.setLayout(null);
		panel_4_2_1_1.setBackground(new Color(0, 139, 139));
		panel_4_2_1_1.setBounds(319, 26, 215, 80);
		panel_3.add(panel_4_2_1_1);
		
		JLabel lblNewLabel_4_2_1_1 = new JLabel("");
		lblNewLabel_4_2_1_1.setIcon(new ImageIcon(img_human));
		lblNewLabel_4_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_1.setBounds(10, 11, 84, 58);
		panel_4_2_1_1.add(lblNewLabel_4_2_1_1);
		
		JLabel lblNewLabel_4_3_1_1 = new JLabel("");
		lblNewLabel_4_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3_1_1.setBounds(10, 11, 74, 58);
		panel_4_2_1_1.add(lblNewLabel_4_3_1_1);
		
		JPanel panel_4_2_1_1_1 = new JPanel();
		panel_4_2_1_1_1.setLayout(null);
		panel_4_2_1_1_1.setBackground(new Color(0, 139, 139));
		panel_4_2_1_1_1.setBounds(541, 26, 262, 80);
		panel_3.add(panel_4_2_1_1_1);
		
		JLabel lblNewLabel_4_2_1_1_1 = new JLabel("");
		lblNewLabel_4_2_1_1_1.setIcon(new ImageIcon(img_computer));
		lblNewLabel_4_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1_1_1.setBounds(0, 11, 84, 58);
		panel_4_2_1_1_1.add(lblNewLabel_4_2_1_1_1);
		
		JLabel lblNewLabel_4_3_1_1_1 = new JLabel("");
		lblNewLabel_4_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3_1_1_1.setBounds(10, 11, 74, 58);
		panel_4_2_1_1_1.add(lblNewLabel_4_3_1_1_1);
		
		JPanel panel_4_2_2 = new JPanel();
		panel_4_2_2.setLayout(null);
		panel_4_2_2.setBackground(new Color(0, 139, 139));
		panel_4_2_2.setBounds(21, 117, 239, 42);
		panel_3.add(panel_4_2_2);
		
		JLabel lblNewLabel_4_2_2 = new JLabel("");
		lblNewLabel_4_2_2.setIcon(new ImageIcon(img_time));
		lblNewLabel_4_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_2.setBounds(10, 0, 41, 42);
		panel_4_2_2.add(lblNewLabel_4_2_2);
		
		JLabel lblNewLabel_6_1 = new JLabel("Time:");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_6_1.setBounds(58, 14, 46, 14);
		panel_4_2_2.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_7 = new JLabel("00:00:00");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setForeground(new Color(0, 0, 255));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		lblNewLabel_7.setBounds(114, 11, 96, 19);
		panel_4_2_2.add(lblNewLabel_7);
		
		JPanel panel_4_2_2_1 = new JPanel();
		panel_4_2_2_1.setLayout(null);
		panel_4_2_2_1.setBackground(new Color(0, 139, 139));
		panel_4_2_2_1.setBounds(476, 117, 239, 42);
		panel_3.add(panel_4_2_2_1);
		
		JLabel lblNewLabel_4_2_2_1 = new JLabel("");
		lblNewLabel_4_2_2_1.setIcon(new ImageIcon(img_steps));
		lblNewLabel_4_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_2_1.setBounds(10, 0, 41, 42);
		panel_4_2_2_1.add(lblNewLabel_4_2_2_1);
		
		JLabel lblNewLabel_6 = new JLabel("Steps:");
		lblNewLabel_6.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(49, 11, 46, 14);
		panel_4_2_2_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7_1 = new JLabel("0");
		lblNewLabel_7_1.setOpaque(true);
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_7_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_7_1.setBackground(Color.WHITE);
		lblNewLabel_7_1.setBounds(116, 9, 96, 19);
		panel_4_2_2_1.add(lblNewLabel_7_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBackground(new Color(0, 128, 128));
		panel_5.setBounds(335, 170, 878, 727);
		contentPane.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		

	}

	private void spinner(JPanel panel) {
		JSpinner spinner = new JSpinner();
		spinner.setBounds(203, 51, 74, 33);
		panel.add(spinner);
		spinner.setValue(15);
		
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

	
}
