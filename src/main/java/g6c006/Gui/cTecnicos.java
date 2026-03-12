package g6c006.Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import g6c006.Classes.Clientes;
import g6c006.Classes.Especialidades;
import g6c006.Classes.Incidentes;
import g6c006.Classes.Tecnicos;
import g6c006.Controllers.ClientesJPA;
import g6c006.Controllers.EspecialidadesJPA;
import g6c006.Controllers.IncidentesJPA;
import g6c006.Controllers.TecnicosJPA;
import g6c006.Misc.NonEditableTableModel;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Notifications.Email;

@SuppressWarnings("all")
public class cTecnicos extends JPanel {

	private TecnicosJPA Controller = new TecnicosJPA();
	private ClientesJPA ControllerCustomer = new ClientesJPA();
	private IncidentesJPA ControllerIncidents = new IncidentesJPA();
	private EspecialidadesJPA ControllerSpecialties = new EspecialidadesJPA();

	private Tecnicos User;

	private JTable Incidentes;
	private JTable eSpecialties;
	private JTextField technicID;
	private JButton refreshIncident;
	private JTextPane Resolucion;
	private JComboBox Resuelto;
	private JComboBox Complejidad;
	private JScrollPane tableSpecialties;
	private JButton technicLogin;
	private JLabel lbInfo;
	private JLabel lbComplejidad;
	private JLabel lbResuelto;
	private JButton delSpecialtie;

	private NonEditableTableModel incidentsModel;
	private NonEditableTableModel eSpecialtiesModel;

	private EntityManagerFactory eMF = Persistence.createEntityManagerFactory("tIntegradorPU");
	private EntityManager eM = eMF.createEntityManager();

	public cTecnicos() {
		setBackground(Color.BLACK);
		setLayout(null);

		JPanel ContentTecnicos = new JPanel();
		ContentTecnicos.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContentTecnicos.setBackground(Color.GRAY);
		ContentTecnicos.setBounds(0, 0, 547, 428);
		add(ContentTecnicos);
		ContentTecnicos.setLayout(null);

		Incidentes = new JTable();
		Incidentes.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Incidentes.setEnabled(false);
		Incidentes.setBounds(10, 259, 181, 158);
		ContentTecnicos.add(Incidentes);
		incidentsModel = new NonEditableTableModel(new Object[] { "ID", "Cliente", "Error" }, 0);
		Incidentes.setModel(incidentsModel);
		incidentsModel.setColumnEditable(0, false);
		incidentsModel.setColumnEditable(1, false);
		incidentsModel.setColumnEditable(2, false);
		Incidentes.getColumnModel().getColumn(0).setResizable(false);
		Incidentes.getColumnModel().getColumn(0).setPreferredWidth(45);
		Incidentes.getColumnModel().getColumn(1).setResizable(false);
		Incidentes.getColumnModel().getColumn(2).setResizable(false);
		JScrollPane tableIncidents = new JScrollPane(Incidentes);
		tableIncidents.setBounds(10, 180, 280, 203);
		ContentTecnicos.add(tableIncidents);

		Complejidad = new JComboBox();
		Complejidad.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Complejidad.setEnabled(false);
		Complejidad.setModel(new DefaultComboBoxModel(new String[] { "Alta", "Media", "Baja" }));
		Complejidad.setBounds(300, 197, 237, 22);
		ContentTecnicos.add(Complejidad);

		Resuelto = new JComboBox();
		Resuelto.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Resuelto.setEnabled(false);
		Resuelto.setModel(new DefaultComboBoxModel(new String[] { "Si", "No" }));
		Resuelto.setBounds(300, 241, 237, 22);
		ContentTecnicos.add(Resuelto);

		Resolucion = new JTextPane();
		Resolucion.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Resolucion.setEnabled(false);
		Resolucion.setText("Resolucion");
		Resolucion.setBounds(300, 274, 237, 109);
		ContentTecnicos.add(Resolucion);

		refreshIncident = new JButton("Solucionar Incidente");
		refreshIncident.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		refreshIncident.setBackground(new Color(145, 255, 145));
		refreshIncident.setEnabled(false);
		refreshIncident.setBounds(10, 394, 527, 23);
		ContentTecnicos.add(refreshIncident);

		technicID = new JTextField();
		technicID.setHorizontalAlignment(SwingConstants.CENTER);
		technicID.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		technicID.setText("DNI");
		technicID.setBounds(10, 11, 191, 22);
		ContentTecnicos.add(technicID);
		technicID.setColumns(10);

		technicLogin = new JButton("Ingresar");
		technicLogin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		technicLogin.setBackground(new Color(255, 167, 79));
		technicLogin.setBounds(211, 10, 116, 23);
		ContentTecnicos.add(technicLogin);

		lbInfo = new JLabel("");
		lbInfo.setFont(new Font("Impact", Font.PLAIN, 10));
		lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfo.setBounds(337, 11, 200, 22);
		ContentTecnicos.add(lbInfo);

		lbComplejidad = new JLabel("Complejidad");
		lbComplejidad.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lbComplejidad.setHorizontalAlignment(SwingConstants.CENTER);
		lbComplejidad.setBounds(300, 181, 237, 14);
		ContentTecnicos.add(lbComplejidad);

		lbResuelto = new JLabel("Resuelto");
		lbResuelto.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lbResuelto.setHorizontalAlignment(SwingConstants.CENTER);
		lbResuelto.setBounds(300, 225, 237, 14);
		ContentTecnicos.add(lbResuelto);

		delSpecialtie = new JButton("Eliminar Especialidad");
		delSpecialtie.setEnabled(false);
		delSpecialtie.setBackground(new Color(255, 145, 145));
		delSpecialtie.setBounds(10, 148, 527, 23);
		ContentTecnicos.add(delSpecialtie);

		eSpecialties = new JTable();
		eSpecialties.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		eSpecialties.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		eSpecialtiesModel = new NonEditableTableModel(new Object[] { "ID", "Especialidad" }, 0);
		eSpecialties.setModel(eSpecialtiesModel);
		eSpecialtiesModel.setColumnEditable(0, false);
		eSpecialtiesModel.setColumnEditable(1, false);
		eSpecialties.getColumnModel().getColumn(0).setResizable(false);
		eSpecialties.getColumnModel().getColumn(1).setResizable(false);
		eSpecialties.setBounds(10, 44, 527, 93);
		JScrollPane tableeSpecialties = new JScrollPane(eSpecialties);
		tableeSpecialties.setEnabled(false);
		tableeSpecialties.setBounds(10, 44, 527, 93);
		ContentTecnicos.add(tableeSpecialties);

		JLabel Marco3 = new JLabel("");
		Marco3.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco3.setBounds(8, 178, 532, 241);
		ContentTecnicos.add(Marco3);

		JLabel Marco2 = new JLabel("");
		Marco2.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco2.setBounds(8, 42, 532, 132);
		ContentTecnicos.add(Marco2);

		JLabel Marco1 = new JLabel("");
		Marco1.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco1.setBounds(7, 8, 323, 28);
		ContentTecnicos.add(Marco1);

		technicLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User = Controller.findTecnicos(Integer.parseInt(technicID.getText()));
				if (User == null) {
					lbInfo.setText("DNI Erroneo");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 145, 145));
				} else {
					refreshIncident.setEnabled(true);
					Resolucion.setEnabled(true);
					Resuelto.setEnabled(true);
					Complejidad.setEnabled(true);
					Incidentes.setEnabled(true);
					delSpecialtie.setEnabled(true);
					technicLogin.setEnabled(false);
					technicID.setEnabled(false);
					lbInfo.setText("Ingresaste como (" + User.getNyA() + ")");
					InfoRestore();
					lbInfo.setForeground(new Color(145, 255, 145));
					RefreshTable();
					RefresheSpecialties();
				}
			}
		});

		Resuelto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Resuelto.getSelectedItem().equals("Si")) {
					refreshIncident.setBackground(new Color(145, 255, 145));
					refreshIncident.setText("Solucionar Incidente");
				} else {
					refreshIncident.setBackground(new Color(255, 255, 128));
					refreshIncident.setText("Actualizar Incidente");
				}
			}
		});

		refreshIncident.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Value = "";
				Incidentes Incident = ControllerIncidents.findIncidentes(incidentsModel.getValueAt(Incidentes.getSelectedRow(), 0).toString());
				if (Resuelto.getSelectedItem().equals("Si")) {
					Incident.setResuelto(true);
					Incident.setComplejidad(Complejidad.getSelectedItem().toString());
					Incident.setResolucion(Resolucion.getText());
					Incident.setSolvDate(new Date());
					Incident.setSolvTime(LocalTime.now());
					Tecnicos Tecnico = Controller.findTecnicos(Incident.getTecnico());
					Clientes Cliente = ControllerCustomer.findClientes(Incident.getCliente());
					Email Mail = new Email();
					Mail.Send(Cliente.getMail(), 
									"Solucion de Incidente [ID: " + Incident.getId() + "]",
									"El tecnico " + Tecnico.getNyA() + " (ID: " + Tecnico.getIdentificacion()+ ") dio solucion al incidente (ID: " + Incident.getId() + ")\n\nResolucion: "+ Incident.getResolucion() + "\nComplejidad: " + Incident.getComplejidad()+ "\nResuelto: Si\n\nIncidente solucionado el " + LocalDate.now() + " a las "+ LocalTime.now().getHour() + ":" + LocalTime.now().getMinute());
					Value = "solucionado";
				} else {
					Tecnicos Tecnico = Controller.findTecnicos(Incident.getTecnico());
					Clientes Cliente = ControllerCustomer.findClientes(Incident.getCliente());
					Incident.setResuelto(false);
					Incident.setComplejidad(Complejidad.getSelectedItem().toString());
					Incident.setResolucion(Resolucion.getText());
					Email Mail = new Email();
					Value = "actualizado";
					Mail.Send(Cliente.getMail(), 
							"Actualizacion de Incidente [ID: " + Incident.getId() + "]",
							"El tecnico " + Tecnico.getNyA() + " (ID: " + Tecnico.getIdentificacion()+ ") actualizo el estado del incidente (ID: " + Incident.getId()+ ")\n\nResolucion: " + Incident.getResolucion() + "\nComplejidad: "+ Incident.getComplejidad() + "\nResuelto: No\n\nIncidente actualizado el "+ LocalDate.now() + " a las " + LocalTime.now().getHour() + ":"+ LocalTime.now().getMinute());
				}
				try {
					lbInfo.setText("Incidente " + Value + " exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(refreshIncident.getBackground().getRed(),refreshIncident.getBackground().getGreen(), refreshIncident.getBackground().getBlue()));
					ControllerIncidents.edit(Incident);
					RefreshTable();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		delSpecialtie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lbInfo.setText("Especialidad eliminada exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 167, 79));
					ControllerSpecialties.destroy(eSpecialtiesModel.getValueAt(eSpecialties.getSelectedRow(), 0).toString());
					RefresheSpecialties();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void RefreshTable() {
		incidentsModel.setRowCount(0);
		Incidentes.revalidate();
		Incidentes.repaint();
		List<Incidentes> incidents = ControllerIncidents.findIncidentesEntities();
		for (Incidentes incident : incidents) {
			if (incident.getTecnico().equals(User.getIdentificacion()) && (incident.getResuelto() == false)) {
				Object[] incidentsData = { incident.getId(), incident.getCliente(), incident.getType() };
				incidentsModel.addRow(incidentsData);
			}
		}
	}

	public void RefresheSpecialties() {
		eSpecialtiesModel.setRowCount(0);
		eSpecialties.revalidate();
		eSpecialties.repaint();
		String consulteSpecialties = "SELECT e FROM Especialidades e WHERE e.tecnico = :tecnico";
		Query eQuery = eM.createQuery(consulteSpecialties);
		eQuery.setParameter("tecnico", User.getIdentificacion());
		List<Especialidades> eFinalQuery = eQuery.getResultList();
		if (eFinalQuery.isEmpty() == false) {
			for (Especialidades fQuery : eFinalQuery) {
				Object[] eSpecialtiesData = { fQuery.getId(), fQuery.getEspecialidad() };
				eSpecialtiesModel.addRow(eSpecialtiesData);
			}
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
