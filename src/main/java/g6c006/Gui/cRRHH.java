package g6c006.Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import g6c006.Classes.Especialidades;
import g6c006.Classes.Incidentes;
import g6c006.Classes.Tecnicos;
import g6c006.Classes.companySpecialties;
import g6c006.Controllers.EspecialidadesJPA;
import g6c006.Controllers.IncidentesJPA;
import g6c006.Controllers.TecnicosJPA;
import g6c006.Controllers.companySpecialtiesJPA;
import g6c006.Misc.NonEditableTableModel;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class cRRHH extends JPanel {

	private TecnicosJPA Controller = new TecnicosJPA();
	private IncidentesJPA ControllerIncidents = new IncidentesJPA();
	private companySpecialtiesJPA ControllerSpecialties = new companySpecialtiesJPA();
	private EspecialidadesJPA ControllerSpecialtiesTechnic = new EspecialidadesJPA();

	private JTextField NyA;
	private JTextField DNI;
	private JTextField Email;
	private JTextField Phone;
	private JTable Technics;
	private JComboBox Speciality;
	private JLabel lbInfo;
	private JLabel Marco1;
	private JLabel Marco2;

	private NonEditableTableModel technicsModel;

	private EntityManagerFactory eMF = Persistence.createEntityManagerFactory("tIntegradorPU");
	private EntityManager eM = eMF.createEntityManager();

	public cRRHH() {
		setBackground(Color.BLACK);
		setLayout(null);

		JPanel ContentRRHH = new JPanel();
		ContentRRHH.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContentRRHH.setBackground(Color.GRAY);
		ContentRRHH.setBounds(0, 0, 547, 428);
		add(ContentRRHH);
		ContentRRHH.setLayout(null);

		NyA = new JTextField();
		NyA.setHorizontalAlignment(SwingConstants.CENTER);
		NyA.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		NyA.setText("Nombre");
		NyA.setBounds(10, 50, 121, 20);
		ContentRRHH.add(NyA);
		NyA.setColumns(10);

		DNI = new JTextField();
		DNI.setHorizontalAlignment(SwingConstants.CENTER);
		DNI.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		DNI.setText("DNI");
		DNI.setColumns(10);
		DNI.setBounds(141, 50, 128, 20);
		ContentRRHH.add(DNI);

		Email = new JTextField();
		Email.setHorizontalAlignment(SwingConstants.CENTER);
		Email.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Email.setText("Email");
		Email.setColumns(10);
		Email.setBounds(279, 50, 127, 20);
		ContentRRHH.add(Email);

		Phone = new JTextField();
		Phone.setHorizontalAlignment(SwingConstants.CENTER);
		Phone.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Phone.setText("Celular");
		Phone.setColumns(10);
		Phone.setBounds(416, 50, 121, 20);
		ContentRRHH.add(Phone);

		JButton addTechnic = new JButton("Agregar Tecnico");
		addTechnic.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		addTechnic.setBackground(new Color(145, 255, 145));
		addTechnic.setBounds(10, 81, 527, 23);
		ContentRRHH.add(addTechnic);

		Speciality = new JComboBox();
		Speciality.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Speciality.setBounds(312, 361, 225, 22);
		ContentRRHH.add(Speciality);

		Technics = new JTable();
		Technics.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Technics.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		technicsModel = new NonEditableTableModel(new Object[] { "ID", "Tecnico", "Email", "Celular" }, 0);
		Technics.setModel(technicsModel);
		technicsModel.setColumnEditable(0, false);
		technicsModel.setColumnEditable(1, false);
		technicsModel.setColumnEditable(2, false);
		technicsModel.setColumnEditable(3, false);
		Technics.getColumnModel().getColumn(0).setResizable(false);
		Technics.getColumnModel().getColumn(0).setPreferredWidth(45);
		Technics.getColumnModel().getColumn(1).setResizable(false);
		Technics.getColumnModel().getColumn(2).setResizable(false);
		Technics.getColumnModel().getColumn(3).setResizable(false);
		Technics.setBounds(315, 11, 222, 144);
		ContentRRHH.add(Technics);
		JScrollPane tableTechnics = new JScrollPane(Technics);
		tableTechnics.setBounds(10, 115, 527, 235);
		ContentRRHH.add(tableTechnics);
		RefreshTable();

		JButton delTechnic = new JButton("Eliminar Tecnico");
		delTechnic.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		delTechnic.setBackground(new Color(255, 145, 145));
		delTechnic.setBounds(10, 361, 292, 56);
		ContentRRHH.add(delTechnic);

		JButton setSpeciality = new JButton("Otorgar Especialidad");
		setSpeciality.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setSpeciality.setBackground(new Color(255, 255, 128));
		setSpeciality.setBounds(312, 394, 225, 23);
		ContentRRHH.add(setSpeciality);

		lbInfo = new JLabel("");
		lbInfo.setFont(new Font("Impact", Font.PLAIN, 10));
		lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfo.setBounds(10, 11, 527, 28);
		ContentRRHH.add(lbInfo);

		Marco1 = new JLabel("");
		Marco1.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco1.setBounds(7, 47, 533, 60);
		ContentRRHH.add(Marco1);

		Marco2 = new JLabel("");
		Marco2.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco2.setBounds(7, 113, 533, 308);
		ContentRRHH.add(Marco2);

		List<companySpecialties> cSpec = ControllerSpecialties.findcompanySpecialtiesEntities();
		for (companySpecialties cSp : cSpec) {
			Speciality.addItem(cSp.getEspecialidad());
		}

		addTechnic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tecnicos Tecnico = new Tecnicos(Integer.parseInt(DNI.getText()), NyA.getText(), "0h 0m",Email.getText(), Integer.parseInt(Phone.getText()));
				try {
					lbInfo.setText("Tecnico (" + Tecnico.getNyA() + " [ID: " + Tecnico.getIdentificacion()+ "]) registrado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(145, 255, 145));
					Controller.create(Tecnico);
					RefreshTable();
				} catch (PreexistingEntityException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		setSpeciality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Consult = "SELECT COUNT(e) FROM Especialidades e WHERE e.especialidad = :especialidad AND e.tecnico = :tecnico";
				Query Query = eM.createQuery(Consult);
				Query.setParameter("especialidad", Speciality.getSelectedItem().toString());
				Query.setParameter("tecnico",Integer.parseInt(technicsModel.getValueAt(Technics.getSelectedRow(), 0).toString()));
				List<Especialidades> rQuery = Query.getResultList();
				if (Integer.parseInt(Query.getSingleResult().toString()) < 1) {
					Especialidades assignSpecialtie = new Especialidades(UUID.randomUUID().toString(),Integer.parseInt(technicsModel.getValueAt(Technics.getSelectedRow(), 0).toString()),Speciality.getSelectedItem().toString());
					try {
						lbInfo.setText("Especialidad (" + Speciality.getSelectedItem()+ ") asignada exitosamente al tecnico (ID: "+ technicsModel.getValueAt(Technics.getSelectedRow(), 0) + ")");
						InfoRestore();
						lbInfo.setForeground(new Color(255, 255, 128));
						ControllerSpecialtiesTechnic.create(assignSpecialtie);
					} catch (PreexistingEntityException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					lbInfo.setText("El tecnico ya tiene esa especialidad");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 145, 145));
				}
			}
		});

		delTechnic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lbInfo.setText("Tecnico (ID: " + technicsModel.getValueAt(Technics.getSelectedRow(), 0)+ ") eliminado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 167, 79));
					Controller.destroy(Integer.parseInt(technicsModel.getValueAt(Technics.getSelectedRow(), 0).toString()));
					String delIncident = "SELECT e FROM Incidentes e WHERE e.tecnico = :tecnico";
					Query iQuery = eM.createQuery(delIncident);
					iQuery.setParameter("tecnico",Integer.parseInt(technicsModel.getValueAt(Technics.getSelectedRow(), 0).toString()));
					List<Incidentes> incidentResult = iQuery.getResultList();
					for (Incidentes iResult : incidentResult) {
						ControllerIncidents.destroy(iResult.getId());
					}
					String delSpecialtie = "SELECT e FROM Especialidades e WHERE e.tecnico = :tecnico";
					Query eQuery = eM.createQuery(delSpecialtie);
					eQuery.setParameter("tecnico",Integer.parseInt(technicsModel.getValueAt(Technics.getSelectedRow(), 0).toString()));
					List<Especialidades> specialtieResult = eQuery.getResultList();
					for (Especialidades eResult : specialtieResult) {
						ControllerSpecialtiesTechnic.destroy(eResult.getId());
					}
					RefreshTable();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public void RefreshTable() {
		technicsModel.setRowCount(0);
		Technics.revalidate();
		Technics.repaint();
		List<Tecnicos> tecnics = Controller.findTecnicosEntities();
		for (Tecnicos tecnic : tecnics) {
			Object[] customersData = { tecnic.getIdentificacion(), tecnic.getNyA(), tecnic.getMail(),tecnic.getTelefono() };
			technicsModel.addRow(customersData);
		}
	}

	public void InfoRestore() {
		ActionListener timerAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lbInfo.setText("");
			}
		};
		Timer timer = new Timer(5000, timerAction);
		timer.setRepeats(false);
		timer.start();
	}
}
