package g6c006.Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import g6c006.Classes.Clientes;
import g6c006.Classes.Empresa;
import g6c006.Classes.Especialidades;
import g6c006.Classes.Incidentes;
import g6c006.Classes.Servicios;
import g6c006.Controllers.ClientesJPA;
import g6c006.Controllers.EmpresaJPA;
import g6c006.Controllers.EspecialidadesJPA;
import g6c006.Controllers.IncidentesJPA;
import g6c006.Controllers.ServiciosJPA;
import g6c006.Controllers.TecnicosJPA;
import g6c006.Misc.NonEditableTableModel;
import g6c006.Misc.PreexistingEntityException;
import g6c006.Notifications.Email;

@SuppressWarnings("all")
public class cAyuda extends JPanel {

	private IncidentesJPA Controller = new IncidentesJPA();
	private ClientesJPA ControllerCustomers = new ClientesJPA();
	private ServiciosJPA ControllerServices = new ServiciosJPA();
	private EspecialidadesJPA ControllerSpecialties = new EspecialidadesJPA();
	private EmpresaJPA ControllerCompany = new EmpresaJPA();
	private TecnicosJPA ControllerTechnics = new TecnicosJPA();

	private Clientes User;

	private JTextField incidentType;
	private JTable technicsTraineds;
	private JLabel lbInfo;
	private JTextField customerID;
	private JEditorPane incidentDescription;
	private JButton newIncident;
	private JButton customerLogin;
	private JTable serviceIncident;
	private JPanel ContentAyuda;
	private JScrollPane tableServiceI;
	private JScrollPane tableTechnicsT;
	private JLabel Marco3_1;

	private EntityManagerFactory eMF = Persistence.createEntityManagerFactory("tIntegradorPU");
	private EntityManager eM = eMF.createEntityManager();

	public cAyuda() {
		setBackground(Color.BLACK);
		setLayout(null);

		ContentAyuda = new JPanel();
		ContentAyuda.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContentAyuda.setBackground(Color.GRAY);
		ContentAyuda.setBounds(0, 0, 547, 428);
		add(ContentAyuda);
		ContentAyuda.setLayout(null);

		incidentType = new JTextField();
		incidentType.setHorizontalAlignment(SwingConstants.CENTER);
		incidentType.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		incidentType.setEnabled(false);
		incidentType.setText("Error");
		incidentType.setBounds(337, 46, 200, 20);
		ContentAyuda.add(incidentType);
		incidentType.setColumns(10);

		incidentDescription = new JEditorPane();
		incidentDescription.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		incidentDescription.setEnabled(false);
		incidentDescription.setText("Descripcion");
		incidentDescription.setBounds(337, 76, 200, 111);
		ContentAyuda.add(incidentDescription);

		technicsTraineds = new JTable();
		technicsTraineds.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		technicsTraineds.setEnabled(false);
		technicsTraineds.setBounds(311, 278, 226, 105);
		ContentAyuda.add(technicsTraineds);
		technicsTraineds.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final NonEditableTableModel ttModel = new NonEditableTableModel(new Object[] { "ID", "Tecnico", "Tiempo Estimado" }, 0);
		technicsTraineds.setModel(ttModel);
		ttModel.setColumnEditable(0, false);
		ttModel.setColumnEditable(1, false);
		ttModel.setColumnEditable(2, false);
		technicsTraineds.getColumnModel().getColumn(0).setResizable(false);
		technicsTraineds.getColumnModel().getColumn(0).setPreferredWidth(45);
		technicsTraineds.getColumnModel().getColumn(1).setResizable(false);
		technicsTraineds.getColumnModel().getColumn(2).setResizable(false);
		tableTechnicsT = new JScrollPane(technicsTraineds);
		tableTechnicsT.setBounds(10, 198, 527, 185);
		ContentAyuda.add(tableTechnicsT);
		tableTechnicsT.setEnabled(false);

		serviceIncident = new JTable();
		serviceIncident.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		serviceIncident.setEnabled(false);
		serviceIncident.setBounds(10, 181, 291, 86);
		ContentAyuda.add(serviceIncident);
		serviceIncident.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final NonEditableTableModel siModel = new NonEditableTableModel(new Object[] { "ID", "Servicio" }, 0);
		serviceIncident.setModel(siModel);
		siModel.setColumnEditable(0, false);
		siModel.setColumnEditable(1, false);
		serviceIncident.getColumnModel().getColumn(0).setResizable(false);
		serviceIncident.getColumnModel().getColumn(0).setPreferredWidth(45);
		serviceIncident.getColumnModel().getColumn(1).setResizable(false);
		tableServiceI = new JScrollPane(serviceIncident);
		tableServiceI.setBounds(10, 45, 317, 142);
		ContentAyuda.add(tableServiceI);
		tableServiceI.setEnabled(false);

		newIncident = new JButton("Reportar Incidente");
		newIncident.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		newIncident.setBackground(new Color(255, 255, 128));
		newIncident.setEnabled(false);
		newIncident.setBounds(10, 394, 527, 23);
		ContentAyuda.add(newIncident);

		customerID = new JTextField();
		customerID.setHorizontalAlignment(SwingConstants.CENTER);
		customerID.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		customerID.setText("DNI");
		customerID.setBounds(11, 11, 191, 23);
		ContentAyuda.add(customerID);
		customerID.setColumns(10);

		customerLogin = new JButton("Ingresar");
		customerLogin.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		customerLogin.setBackground(new Color(255, 167, 79));

		customerLogin.setBounds(211, 11, 116, 23);
		ContentAyuda.add(customerLogin);

		lbInfo = new JLabel("");
		lbInfo.setFont(new Font("Impact", Font.PLAIN, 10));
		lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfo.setBounds(337, 11, 200, 23);
		ContentAyuda.add(lbInfo);

		JLabel Marco3 = new JLabel("");
		Marco3.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco3.setBounds(8, 8, 323, 29);
		ContentAyuda.add(Marco3);

		Marco3_1 = new JLabel("");
		Marco3_1.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco3_1.setBounds(8, 43, 532, 377);
		ContentAyuda.add(Marco3_1);

		customerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User = ControllerCustomers.findClientes(Integer.parseInt(customerID.getText()));
				if (User == null) {
					lbInfo.setText("DNI Erroneo");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 145, 145));
				} else {
					newIncident.setEnabled(true);
					technicsTraineds.setEnabled(true);
					incidentDescription.setEnabled(true);
					incidentType.setEnabled(true);
					serviceIncident.setEnabled(true);
					tableTechnicsT.setEnabled(true);
					tableServiceI.setEnabled(true);
					customerLogin.setEnabled(false);
					customerID.setEnabled(false);
					lbInfo.setText("Ingresaste como (" + User.getNyA() + ")");
					InfoRestore();
					lbInfo.setForeground(new Color(145, 255, 145));
					String Consult = "SELECT e FROM Servicios e WHERE e.cliente = :cliente";
					Query Query = eM.createQuery(Consult);
					Query.setParameter("cliente", Integer.parseInt(customerID.getText()));
					List<Servicios> rQuery = Query.getResultList();
					for (Servicios query : rQuery) {
						Object[] serviceData = { query.getId(), query.getServicio() };
						siModel.addRow(serviceData);
					}
				}
			}
		});

		newIncident.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UUID Id = UUID.randomUUID();
				Incidentes Incident = new Incidentes(Id.toString(), User.getIdentificacion(),
						Integer.parseInt(ttModel.getValueAt(technicsTraineds.getSelectedRow(), 0).toString()),
						siModel.getValueAt(serviceIncident.getSelectedRow(), 0).toString(), 
						incidentType.getText(),
						incidentDescription.getText(), 
						null, 
						new Date(), 
						LocalTime.now(), 
						false, 
						null, 
						null, 
						null);
				Email Mail = new Email();
				Mail.Send(ControllerTechnics.findTecnicos(Integer.parseInt(ttModel.getValueAt(technicsTraineds.getSelectedRow(), 0).toString())).getMail(),
								"Nuevo Incidente [" + Id.toString() + "]",
								"El cliente " + User.getNyA() + " (ID: " + User.getIdentificacion()+ ") reporta un incidente en su servicio "+ siModel.getValueAt(serviceIncident.getSelectedRow(), 1).toString() + " (ID: "+ siModel.getValueAt(serviceIncident.getSelectedRow(), 0).toString() + ").\n\nError: "+ incidentType.getText() + "\nDescripcion: " + incidentDescription.getText()+ "\n\nIncidente registrado el " + LocalDate.now() + " a las "+ LocalTime.now().getHour() + ":" + LocalTime.now().getMinute());
				try {
					lbInfo.setText("Incidente reportado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 255, 128));
					Controller.create(Incident);
				} catch (PreexistingEntityException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		serviceIncident.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ttModel.setRowCount(0);
				technicsTraineds.revalidate();
				technicsTraineds.repaint();
				String consultCompany = "SELECT e FROM Empresa e WHERE e.servicio = :servicio";
				Query icQuery = eM.createQuery(consultCompany);
				icQuery.setParameter("servicio", siModel.getValueAt(serviceIncident.getSelectedRow(), 1).toString());
				List<Empresa> preQuery = icQuery.getResultList();
				List<String> technicsList = new ArrayList<>();
				for (Empresa pQuery : preQuery) {
					technicsList.add(pQuery.getEspecialidad());
				}
				String consultSpecialties = "SELECT e FROM Especialidades e WHERE e.especialidad IN :especialidad";
				Query isQuery = eM.createQuery(consultSpecialties);
				isQuery.setParameter("especialidad", technicsList);
				HashSet<Object> tableValues = new HashSet<>();
				List<Especialidades> finalQuery = isQuery.getResultList();
				for (Especialidades fQuery : finalQuery) {
					if (!tableValues.contains(ControllerTechnics.findTecnicos(fQuery.getTecnico()).getNyA())) {
						tableValues.add(ControllerTechnics.findTecnicos(fQuery.getTecnico()).getNyA());
						Object[] technicData = {
								ControllerTechnics.findTecnicos(fQuery.getTecnico()).getIdentificacion(),
								ControllerTechnics.findTecnicos(fQuery.getTecnico()).getNyA(),
								ControllerTechnics.findTecnicos(fQuery.getTecnico()).getEstimatedT() };
						ttModel.addRow(technicData);
					}
				}
			}
		});
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
