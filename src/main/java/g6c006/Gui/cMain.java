package g6c006.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("all")
public class cMain extends JFrame {

	private JPanel ContentMain;
	private JPanel Content;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cMain frame = new cMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setContent(JPanel sContent) {
		sContent.setSize(547, 428);
		sContent.setLocation(0, 0);
		Content.removeAll();
		Content.add(sContent, BorderLayout.CENTER);
		Content.revalidate();
		Content.repaint();
	}

	public cMain() {
		ImageIcon iconMain = new ImageIcon(getClass().getResource("/images/Logo.png"));
		setTitle("Trabajo Integrador [Grupo 6 - Comision 006]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 489);
		setIconImage(iconMain.getImage());
		setResizable(false);
		ContentMain = new JPanel();
		ContentMain.setBackground(Color.DARK_GRAY);
		ContentMain.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ContentMain);
		ContentMain.setLayout(null);

		JButton setInicio = new JButton("Inicio");
		setInicio.setFont(new Font("Dialog", Font.BOLD, 13));
		setInicio.setForeground(Color.BLACK);
		setInicio.setBackground(Color.WHITE);
		setInicio.setBounds(10, 114, 135, 31);
		ContentMain.add(setInicio);

		JButton setEmpresa = new JButton("Empresa");
		setEmpresa.setFont(new Font("Dialog", Font.BOLD, 13));
		setEmpresa.setForeground(Color.BLACK);
		setEmpresa.setBackground(Color.WHITE);
		setEmpresa.setBounds(10, 176, 135, 31);
		ContentMain.add(setEmpresa);

		JButton setRRHH = new JButton("Area RRHH");
		setRRHH.setFont(new Font("Dialog", Font.BOLD, 13));
		setRRHH.setForeground(Color.BLACK);
		setRRHH.setBackground(Color.WHITE);
		setRRHH.setBounds(10, 218, 135, 31);
		ContentMain.add(setRRHH);

		JButton setComercial = new JButton("Area Comercial");
		setComercial.setFont(new Font("Dialog", Font.BOLD, 13));
		setComercial.setForeground(Color.BLACK);
		setComercial.setBackground(Color.WHITE);
		setComercial.setBounds(10, 260, 135, 31);
		ContentMain.add(setComercial);

		JButton setAyuda = new JButton("Mesa de Ayuda");
		setAyuda.setFont(new Font("Dialog", Font.BOLD, 13));
		setAyuda.setForeground(Color.BLACK);
		setAyuda.setBackground(Color.WHITE);
		setAyuda.setBounds(10, 324, 135, 31);
		ContentMain.add(setAyuda);

		JButton setTecnicos = new JButton("Tecnicos");
		setTecnicos.setFont(new Font("Dialog", Font.BOLD, 13));
		setTecnicos.setForeground(Color.BLACK);
		setTecnicos.setBackground(Color.WHITE);
		setTecnicos.setBounds(10, 366, 135, 31);
		ContentMain.add(setTecnicos);

		JButton setClientes = new JButton("Clientes");
		setClientes.setFont(new Font("Dialog", Font.BOLD, 13));
		setClientes.setForeground(Color.BLACK);
		setClientes.setBackground(Color.WHITE);
		setClientes.setBounds(10, 408, 135, 31);
		ContentMain.add(setClientes);

		Content = new JPanel();
		Content.setBackground(Color.GRAY);
		Content.setBounds(155, 11, 547, 428);
		ContentMain.add(Content);
		Content.setLayout(null);

		cInicio cInicio = new cInicio();
		cInicio.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContent(cInicio);

		JLabel Logo = new JLabel("");
		Logo.setIcon(new ImageIcon(getClass().getResource("/images/Icono.png")));
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setBounds(10, 11, 135, 92);
		ContentMain.add(Logo);

		JLabel Marco1 = new JLabel("");
		Marco1.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco1.setBounds(6, 109, 144, 42);
		ContentMain.add(Marco1);

		JLabel Marco2 = new JLabel("");
		Marco2.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco2.setBounds(6, 171, 144, 125);
		ContentMain.add(Marco2);

		JLabel Marco3 = new JLabel("");
		Marco3.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco3.setBounds(6, 319, 144, 125);
		ContentMain.add(Marco3);

		setInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cInicio cInicio = new cInicio();
				setContent(cInicio);
			}
		});

		setEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cEmpresa cEmpresa = new cEmpresa();
				setContent(cEmpresa);
			}
		});

		setRRHH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cRRHH cRRHH = new cRRHH();
				setContent(cRRHH);
			}
		});

		setComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cComercial cComercial = new cComercial();
				setContent(cComercial);
			}
		});

		setAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cAyuda cAyuda = new cAyuda();
				setContent(cAyuda);
			}
		});

		setTecnicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cTecnicos cTecnicos = new cTecnicos();
				setContent(cTecnicos);
			}
		});

		setClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cClientes cClientes = new cClientes();
				setContent(cClientes);
			}
		});
	}
}
