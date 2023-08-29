package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.Player;
import entity.Status;

import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Database.HistoryDAO;
import Database.LichSuDau;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetupChar extends JPanel {
	private JFrame parentFrame;
	
	public Status status;
	public Status status2;
	private JTextField namePlayer_1,namePlayer;
	JLabel pointHP, pointMP, pointCrit, pointCritDM, pointHP_1, pointMP_1, pointCrit_1, pointCritDM_1;
	JLabel pointNum, pointNum_1;

	// fixed index
	public int statusPoint = 8;
	public int statusHP = 100;
	public int statusMP = 100;
	public int statusCrit = 30;
	public int statusCritDame = 60;

	public int chHP;
	public int chMP;
	public int chCrit;
	public int chCritDama;

	public int point;
	public int HP;
	public int MP;
	public int crit;
	public int critDame;

	public int point_1;
	public int HP_1;
	public int MP_1;
	public int crit_1;
	public int critDame_1;

	/**
	 * Create the panel.
	 */
	public void changeCharacterStats(int chHP, int chMP, int chCrit, int chCritDame) {
		this.chHP = chHP;
		this.chMP = chMP;
		this.chCrit = chCrit;
		this.chCritDama = chCritDame;
	}

	public void setStatus() {
		
		
		this.point = this.statusPoint;
		this.HP = this.statusHP;
		this.MP = this.statusMP;
		this.crit = this.statusCrit;
		this.critDame = this.statusCritDame;

		this.point_1 = this.statusPoint;
		this.HP_1 = this.statusHP;
		this.MP_1 = this.statusMP;
		this.crit_1 = this.statusCrit;
		this.critDame_1 = this.statusCritDame;

	}

	public void minusPoint() {
		point--;
	}

	public void plusPoint() {
		point++;
	}

	public void minusPoint_1() {
		point_1--;
	}

	public void plusPoint_1() {
		point_1++;
	}
	
	private void closeMainFrame() {
        if (parentFrame != null) {
            parentFrame.dispose(); // Đóng cửa sổ JFrame
        }
    }

	public SetupChar(SetupFrame setupFrame) {
		
		this.parentFrame = setupFrame;
		
		setBackground(new Color(0, 0, 0));
		setBorder(null);

		// set change status
		changeCharacterStats(100, 100, 10, 20);

		// set point
		setStatus();

		URL linkMinus = getClass().getResource("/icons/minus.png");
		URL linkPlus = getClass().getResource("/icons/plus.png"); // Đường dẫn tới ảnh
		ImageIcon imgMinus = new ImageIcon(linkMinus);
		ImageIcon imgPlus = new ImageIcon(linkPlus);

		namePlayer = new JTextField();
		namePlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		namePlayer.setColumns(10);

		JLabel lblNewLabel = new JLabel("Play name 1");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 23));
		lblNewLabel.setBackground(Color.WHITE);

		JLabel lblHp = new JLabel("HP");
		lblHp.setEnabled(false);
		lblHp.setFont(new Font("Dialog", Font.BOLD, 23));
		lblHp.setBackground(Color.WHITE);

		JButton plusHP = new JButton("");
		plusHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point - 1 >= 0 ) {
					HP += chHP;
					minusPoint();
					pointHP.setBackground(Color.GREEN);
					pointHP.setText("" + HP);
					pointNum.setText("" + point);
				}
			}
		});

		plusHP.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\plus.png"));

		JButton minusHP = new JButton("");
		minusHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point + 1 <= statusPoint && HP - chHP >= statusHP ) {
					HP -= chHP;
					plusPoint();
					pointHP.setText("" + HP);
					if(HP == statusHP)
						pointHP.setBackground(Color.WHITE);
					pointNum.setText("" + point);
				}
			}
		});
		minusHP.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\minus-sign.png"));

		pointHP = new JLabel("" + HP);
		pointHP.setHorizontalAlignment(SwingConstants.CENTER);
		pointHP.setFont(new Font("Dialog", Font.BOLD, 35));
		pointHP.setEnabled(false);
		pointHP.setBackground(Color.WHITE);

		JLabel lblCore = new JLabel("Point: ");
		lblCore.setFont(new Font("Dialog", Font.BOLD, 23));
		lblCore.setEnabled(false);
		lblCore.setBackground(Color.WHITE);

		pointNum = new JLabel(""+point);
		pointNum.setHorizontalAlignment(SwingConstants.CENTER);
		pointNum.setFont(new Font("Dialog", Font.BOLD, 35));
		pointNum.setEnabled(false);
		pointNum.setBackground(Color.WHITE);

		JLabel lbMP = new JLabel("MP");
		lbMP.setFont(new Font("Dialog", Font.BOLD, 23));
		lbMP.setEnabled(false);
		lbMP.setBackground(Color.WHITE);

		JButton minusMP = new JButton("");
		minusMP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point + 1 <= statusPoint && MP - chMP >= statusMP) {
					MP -= chMP;
					plusPoint();
					pointMP.setText("" + MP);
					
					if(MP == statusMP)
						pointHP.setBackground(Color.WHITE);
					pointNum.setText("" + point);
				}
			}
		});
		minusMP.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\minus-sign.png"));

		pointMP = new JLabel("" + MP);
		pointMP.setHorizontalAlignment(SwingConstants.CENTER);
		pointMP.setFont(new Font("Dialog", Font.BOLD, 35));
		pointMP.setEnabled(false);
		pointMP.setBackground(Color.WHITE);

		JButton plusMP = new JButton("");
		plusMP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point - 1 >= 0) {
					MP += chMP;
					minusPoint();
					pointMP.setText("" + MP);
					pointMP.setBackground(Color.GREEN);
					pointNum.setText("" + point);
				}
			}
		});
		plusMP.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\plus.png"));

		JLabel lblCrit = new JLabel("CRIT");
		lblCrit.setFont(new Font("Dialog", Font.BOLD, 23));
		lblCrit.setEnabled(false);
		lblCrit.setBackground(Color.WHITE);

		JButton minusCrit = new JButton("");
		minusCrit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point + 1 <= statusPoint && crit - chCrit >= statusCrit) {
					crit -= chCrit;
					plusPoint();
					pointCrit.setText("" + crit);
					if(crit == statusCrit) {
						pointCrit.setBackground(Color.WHITE);
					}
					pointNum.setText("" + point);
				}
			}
		});
		minusCrit.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\minus-sign.png"));

		pointCrit = new JLabel("" + crit);
		pointCrit.setHorizontalAlignment(SwingConstants.CENTER);
		pointCrit.setFont(new Font("Dialog", Font.BOLD, 35));
		pointCrit.setEnabled(false);
		pointCrit.setBackground(Color.WHITE);

		JButton plusCrit = new JButton("");
		plusCrit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point - 1 >= 0  && crit + chCrit <= 100) {
					crit += chCrit;
					minusPoint();
				    pointCrit.setBackground(Color.GREEN);
					pointCrit.setText("" + crit);
					pointNum.setText("" + point);
				}
				if(crit == 100) {
					JOptionPane.showMessageDialog(null, "Crit the limit", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		plusCrit.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\plus.png"));

		JLabel lbcritdm = new JLabel("CRIT DAME");
		lbcritdm.setFont(new Font("Dialog", Font.BOLD, 23));
		lbcritdm.setEnabled(false);
		lbcritdm.setBackground(Color.WHITE);

		JButton minusCritDM = new JButton("");
		minusCritDM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point + 1 <= statusPoint && critDame - chCritDama >= statusCritDame) {
					critDame -= chCritDama;
					plusPoint();
					if(critDame == statusCritDame) {
						pointCritDM.setBackground(Color.WHITE);
					}
					pointCritDM.setText("" + critDame);
					pointNum.setText("" + point);
				}
			}
		});
		minusCritDM.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\minus-sign.png"));

		pointCritDM = new JLabel("" + critDame);
		pointCritDM.setHorizontalAlignment(SwingConstants.CENTER);
		pointCritDM.setFont(new Font("Dialog", Font.BOLD, 35));
		pointCritDM.setEnabled(false);
		pointCritDM.setBackground(Color.WHITE);

		JButton plusCritDM = new JButton("");
		plusCritDM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point - 1 >= 0) {
					critDame += chCritDama;
					minusPoint();
					pointCritDM.setBackground(Color.GREEN);
					pointCritDM.setText("" + critDame);
					pointNum.setText("" + point);
				}
			}
		});
		plusCritDM.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\plus.png"));

		JLabel lblNewLabel_1 = new JLabel("Play name 2");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 23));
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBackground(Color.WHITE);

		namePlayer_1 = new JTextField();
		namePlayer_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		namePlayer_1.setColumns(10);

		JLabel lblHp_1 = new JLabel("HP");
		lblHp_1.setFont(new Font("Dialog", Font.BOLD, 23));
		lblHp_1.setEnabled(false);
		lblHp_1.setBackground(Color.WHITE);

		JButton minusHP_1 = new JButton("");
		minusHP_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_1 + 1 <= statusPoint && HP_1 - chHP >= statusHP) {
					HP_1 -= chHP;
					plusPoint_1();
					if(HP_1 == statusHP) {
						pointHP_1.setBackground(Color.WHITE);
					}
					pointHP_1.setText("" + HP_1);
					pointNum_1.setText("" + point_1);
				}
			}
		});
		minusHP_1.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\minus-sign.png"));

		pointHP_1 = new JLabel("" + HP_1);
		pointHP_1.setHorizontalAlignment(SwingConstants.CENTER);
		pointHP_1.setFont(new Font("Dialog", Font.BOLD, 35));
		pointHP_1.setEnabled(false);
		pointHP_1.setBackground(Color.WHITE);

		JButton plusHP_1 = new JButton("");
		plusHP_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_1 - 1 >= 0) {
					HP_1 += chHP;
					minusPoint_1();
					pointHP_1.setBackground(Color.GREEN);
					pointHP_1.setText("" + HP_1);
					pointNum_1.setText("" + point_1);
				}
			}
		});
		plusHP_1.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\plus.png"));

		JLabel lblCore_1 = new JLabel("Point: ");
		lblCore_1.setFont(new Font("Dialog", Font.BOLD, 23));
		lblCore_1.setEnabled(false);
		lblCore_1.setBackground(Color.WHITE);

		pointNum_1 = new JLabel(""+point_1);
		pointNum_1.setHorizontalAlignment(SwingConstants.CENTER);
		pointNum_1.setFont(new Font("Dialog", Font.BOLD, 35));
		pointNum_1.setEnabled(false);
		pointNum_1.setBackground(Color.WHITE);

		JLabel lbMP_1 = new JLabel("MP");
		lbMP_1.setFont(new Font("Dialog", Font.BOLD, 23));
		lbMP_1.setEnabled(false);
		lbMP_1.setBackground(Color.WHITE);

		JButton minusMP_1 = new JButton("");
		minusMP_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_1 + 1 <= statusPoint && MP_1 - chMP >= statusMP) {
					MP_1 -= chMP;
					plusPoint_1();
					if(MP_1 == statusMP)
						pointMP_1.setBackground(Color.WHITE);
					pointMP_1.setText("" + MP_1);
					pointNum_1.setText("" + point_1);
				}
			}
		});
		minusMP_1.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\minus-sign.png"));

		pointMP_1 = new JLabel("" + MP_1);
		pointMP_1.setHorizontalAlignment(SwingConstants.CENTER);
		pointMP_1.setFont(new Font("Dialog", Font.BOLD, 35));
		pointMP_1.setEnabled(false);
		pointMP_1.setBackground(Color.WHITE);

		JButton plusMP_1 = new JButton("");
		plusMP_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_1 - 1 >= 0) {
					MP_1 += chMP;
					minusPoint_1();
					pointMP_1.setBackground(Color.GREEN);
					pointMP_1.setText("" + MP_1);
					pointNum_1.setText("" + point_1);
				}
			}
		});
		plusMP_1.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\plus.png"));

		JLabel lblCrit_1 = new JLabel("CRIT");
		lblCrit_1.setFont(new Font("Dialog", Font.BOLD, 23));
		lblCrit_1.setEnabled(false);
		lblCrit_1.setBackground(Color.WHITE);

		JButton minusCrit_1 = new JButton("");
		minusCrit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_1 + 1 <= statusPoint && crit_1 - chCrit >= statusCrit) {
					crit_1 -= chCrit;
					plusPoint_1();
					if(crit_1 == statusCrit)
						pointCrit_1.setBackground(Color.WHITE);
					pointCrit_1.setText("" + crit_1);
					pointNum_1.setText("" + point_1);
				}
			}
		});
		minusCrit_1.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\minus-sign.png"));

		pointCrit_1 = new JLabel("" + crit_1);
		pointCrit_1.setHorizontalAlignment(SwingConstants.CENTER);
		pointCrit_1.setFont(new Font("Dialog", Font.BOLD, 35));
		pointCrit_1.setEnabled(false);
		pointCrit_1.setBackground(Color.WHITE);

		JButton plusCrit_1 = new JButton("");
		plusCrit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_1 - 1 >= 0 && crit_1 + chCrit <= 100) {
					crit_1 += chCrit;
					minusPoint_1();
					pointCrit_1.setBackground(Color.GREEN);
					pointCrit_1.setText("" + crit_1);
					pointNum_1.setText("" + point_1);
				}
				if(crit_1 == 100) {
					JOptionPane.showMessageDialog(null, "Crit the limit", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		plusCrit_1.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\plus.png"));

		JLabel lbcritdm_1 = new JLabel("CRIT DAME");
		lbcritdm_1.setFont(new Font("Dialog", Font.BOLD, 23));
		lbcritdm_1.setEnabled(false);
		lbcritdm_1.setBackground(Color.WHITE);

		JButton minusCritDM_1 = new JButton("");
		minusCritDM_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_1 + 1 <= statusPoint && critDame_1 - chCritDama >= statusCritDame) {
					critDame_1 -= chCritDama;
					plusPoint_1();
					if(critDame_1 == statusCritDame)
						pointCritDM_1.setBackground(Color.WHITE);
					pointCritDM_1.setText("" + critDame_1);
					pointNum_1.setText("" + point_1);
				}
			}
		});
		minusCritDM_1.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\minus-sign.png"));

		pointCritDM_1 = new JLabel("" + critDame_1);
		pointCritDM_1.setHorizontalAlignment(SwingConstants.CENTER);
		pointCritDM_1.setFont(new Font("Dialog", Font.BOLD, 35));
		pointCritDM_1.setEnabled(false);
		pointCritDM_1.setBackground(Color.WHITE);

		JButton plusCritDM_1 = new JButton("");
		plusCritDM_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (point_1 - 1 >= 0) {
					critDame_1 += chCritDama;
					minusPoint_1();
					pointCritDM_1.setBackground(Color.GREEN);
					pointCritDM_1.setText("" + critDame_1);
					pointNum_1.setText("" + point_1);
				}
			}
		});
		plusCritDM_1.setIcon(new ImageIcon("D:\\_doan\\RyiSnowJava\\learn game\\icon\\plus.png"));

		JButton BtnPlayer = new JButton("Play");
		BtnPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "";
				String name1 = "";
				name = namePlayer.getText();
				name1 = namePlayer_1.getText();
				
				if(name.isEmpty() && name1.isEmpty() && name.equalsIgnoreCase(name1)) {
					JOptionPane.showMessageDialog(null, "Duplicate name or null name!!!");
				}else {
					if(point == 0 && point_1 == 0) {
						status = new Status(name, HP, MP, crit, critDame); 
						status2 = new Status(name1, HP_1, MP_1, crit_1, critDame_1); 

						new WindowFrame(status, status2);
						
						closeMainFrame();
					}else {
						JOptionPane.showMessageDialog(null, "Point has not reached 0 !!!");
					}
				}
			}
		});
		
		BtnPlayer.setFont(new Font("Dialog", Font.BOLD, 29));
		
		JLabel lblNewLabel_2 = new JLabel("HP: lượng máu giới hạn của nhân vật");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_2_2 = new JLabel("Crit: tỉ lệ xuất hiện sát thương thêm khi nhân vật tấn công");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblNewLabel_2_3 = new JLabel("MP: lượng mana cần thiết để gọi Hỏa cầu");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Crit Dame : độ lớn của sát thương thêm");
		lblNewLabel_2_3_1.setForeground(Color.WHITE);
		lblNewLabel_2_3_1.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JButton BtnLSDau = new JButton("HISTORY ");
		BtnLSDau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String liString = "";
				
				HistoryDAO historyDAO = new HistoryDAO();

				List<LichSuDau> lSuDaus = new ArrayList<>();

				lSuDaus.addAll(historyDAO.toStringLSDau());
				
				for (var item : lSuDaus) {
					liString += item.toString();
					liString += "\n";
				}
			}
		});
		BtnLSDau.setFont(new Font("Dialog", Font.BOLD, 29));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCore, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(126)
									.addComponent(pointNum, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(namePlayer, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lbcritdm, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
													.addGap(38)
													.addComponent(minusCritDM, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
													.addComponent(pointCritDM, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblCrit, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
													.addGap(38)
													.addComponent(minusCrit, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
													.addComponent(pointCrit, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lbMP, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
													.addGap(38)
													.addComponent(minusMP, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
													.addComponent(pointMP, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblHp, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
													.addGap(38)
													.addComponent(minusHP, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(pointHP, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
											.addGap(47)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(plusMP, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addComponent(plusCrit, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addComponent(plusCritDM, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addComponent(plusHP))))
							.addPreferredGap(ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(namePlayer_1, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCore_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(126)
									.addComponent(pointNum_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblHp_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(38)
									.addComponent(minusHP_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(pointHP_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(plusHP_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbMP_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(38)
									.addComponent(minusMP_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(pointMP_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(plusMP_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCrit_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(38)
									.addComponent(minusCrit_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(pointCrit_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(plusCrit_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbcritdm_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(38)
									.addComponent(minusCritDM_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(pointCritDM_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(plusCritDM_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(BtnLSDau, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(BtnPlayer, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
							.addGap(192))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_3)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_3_1, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))
					.addGap(415))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(23)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(namePlayer_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(5)
									.addComponent(lblCore_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(pointNum_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lblHp_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(minusHP_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(7)
									.addComponent(pointHP_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(plusHP_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lbMP_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(minusMP_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(7)
									.addComponent(pointMP_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(plusMP_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lblCrit_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(minusCrit_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(7)
									.addComponent(pointCrit_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(plusCrit_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(7)
									.addComponent(pointCritDM_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(plusCritDM_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lbcritdm_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addComponent(minusCritDM_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(namePlayer, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(pointNum, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCore, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(34)
											.addComponent(lblHp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(23)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(plusHP, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
												.addComponent(minusHP, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pointHP, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(22)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lbMP, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(minusMP, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(plusMP, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(pointMP, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(11)
											.addComponent(lblCrit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addComponent(minusCrit, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(plusCrit, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(21)
									.addComponent(pointCrit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(11)
											.addComponent(lbcritdm, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
										.addComponent(minusCritDM, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(plusCritDM, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(23)
									.addComponent(pointCritDM, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2_3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(BtnPlayer, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2_3_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(BtnLSDau, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);

		setLayout(groupLayout);

	}
}
