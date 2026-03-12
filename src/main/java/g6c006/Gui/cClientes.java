package g6c006.Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import g6c006.Classes.Incidentes;
import g6c006.Classes.Servicios;
import g6c006.Controllers.ClientesJPA;
import g6c006.Controllers.IncidentesJPA;
import g6c006.Controllers.ServiciosJPA;
import g6c006.Misc.NonEditableTableModel;
import g6c006.Misc.NonexistentEntityException;

@SuppressWarnings("all")
public class cClientes extends JPanel {

	private ClientesJPA Controller = new ClientesJPA();
	private ServiciosJPA ControllerServices = new ServiciosJPA();
	private IncidentesJPA ControllerIncidents = new IncidentesJPA();

	private JTable Incidentes;
	private JTable Services;
	private JTextField idLogin;
	private JScrollPane tableIncidentes;
	private JButton delIncident;
	private JLabel lbInfo;
	private JButton Login;
	private Clientes User;
	private JButton delService;

	private NonEditableTableModel IncidentesModel;
	private NonEditableTableModel ServicesModel;

	private EntityManagerFactory eMF = Persistence.createEntityManagerFactory("tIntegradorPU");
	private EntityManager eM = eMF.createEntityManager();

	public cClientes() {
		setBackground(Color.BLACK);
		setLayout(null);

		JPanel ContentClientes = new JPanel();
		ContentClientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContentClientes.setBackground(Color.GRAY);
		ContentClientes.setBounds(0, 0, 547, 428);
		add(ContentClientes);
		ContentClientes.setLayout(null);

		Incidentes = new JTable();
		Incidentes.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Incidentes.setBounds(10, 44, 527, 333);
		ContentClientes.add(Incidentes);
		Incidentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		IncidentesModel = new NonEditableTableModel(new Object[] { "Incidente", "Servicio", "Error" }, 0);
		Incidentes.setModel(IncidentesModel);
		IncidentesModel.setColumnEditable(0, false);
		IncidentesModel.setColumnEditable(1, false);
		IncidentesModel.setColumnEditable(2, false);
		Incidentes.getColumnModel().getColumn(0).setResizable(false);
		Incidentes.getColumnModel().getColumn(0).setPreferredWidth(45);
		Incidentes.getColumnModel().getColumn(1).setResizable(false);
		Incidentes.getColumnModel().getColumn(2).setResizable(false);
		tableIncidentes = new JScrollPane(Incidentes);
		tableIncidentes.setEnabled(false);
		tableIncidentes.setBounds(10, 236, 527, 145);
		ContentClientes.add(tableIncidentes);

		delIncident = new JButton("Eliminar Incidente");
		delIncident.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		delIncident.setEnabled(false);
		delIncident.setBackground(new Color(255, 145, 145));
		delIncident.setBounds(11, 392, 526, 25);
		ContentClientes.add(delIncident);

		idLogin = new JTextField();
		idLogin.setHorizontalAlignment(SwingConstants.CENTER);
		idLogin.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		idLogin.setText("DNI");
		idLogin.setBounds(11, 11, 191, 22);
		ContentClientes.add(idLogin);
		idLogin.setColumns(10);

		Login = new JButton("Ingresar");
		Login.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		Login.setBackground(new Color(255, 167, 79));
		Login.setBounds(211, 11, 116, 23);
		ContentClientes.add(Login);

		lbInfo = new JLabel("");
		lbInfo.setFont(new Font("Impact", Font.PLAIN, 10));
		lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfo.setBounds(337, 11, 200, 22);
		ContentClientes.add(lbInfo);

		Services = new JTable();
		Services.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Services.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ServicesModel = new NonEditableTableModel(new Object[] { "ID", "Servicio" }, 0);
		Services.setModel(ServicesModel);
		ServicesModel.setColumnEditable(0, false);
		ServicesModel.setColumnEditable(1, false);
		Services.getColumnModel().getColumn(0).setResizable(false);
		Services.getColumnModel().getColumn(1).setResizable(false);
		Services.setBounds(11, 44, 527, 145);
		JScrollPane tableServices = new JScrollPane(Services);
		tableServices.setEnabled(false);
		tableServices.setBounds(11, 44, 527, 145);
		ContentClientes.add(tableServices);

		delService = new JButton("Eliminar Servicio");
		delService.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		delService.setEnabled(false);
		delService.setBackground(new Color(255, 145, 145));
		delService.setBounds(11, 200, 526, 25);
		ContentClientes.add(delService);

		JLabel Marco1 = new JLabel("");
		Marco1.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco1.setBounds(8, 41, 533, 189);
		ContentClientes.add(Marco1);

		JLabel Marco2 = new JLabel("");
		Marco2.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco2.setBounds(8, 233, 533, 189);
		ContentClientes.add(Marco2);

		JLabel Marco3 = new JLabel("");
		Marco3.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco3.setBounds(8, 8, 323, 29);
		ContentClientes.add(Marco3);

		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User = Controller.findClientes(Integer.parseInt(idLogin.getText()));
				if (User == null) {
					lbInfo.setText("DNI Erroneo");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 145, 145));
				} else {
					tableIncidentes.setEnabled(true);
					delIncident.setEnabled(true);
					delService.setEnabled(true);
					Login.setEnabled(false);
					idLogin.setEnabled(false);
					lbInfo.setText("Ingresaste como (" + User.getNyA() + ")");
					InfoRestore();
					lbInfo.setForeground(new Color(145, 255, 145));
					RefreshTable();
					RefreshServicesT();
				}
			}
		});

		delIncident.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lbInfo.setText("Incidente eliminado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 167, 79));
					ControllerIncidents.destroy(IncidentesModel.getValueAt(Incidentes.getSelectedRow(), 0).toString());
					RefreshTable();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				}
			}
		});

		delService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lbInfo.setText("Servicio eliminado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 167, 79));
					ControllerServices.destroy(ServicesModel.getValueAt(Services.getSelectedRow(), 0).toString());
					String consultServices = "SELECT e FROM Incidentes e WHERE e.servicio = :servicio";
					Query sQuery = eM.createQuery(consultServices);
					sQuery.setParameter("servicio", ServicesModel.getValueAt(Services.getSelectedRow(), 0).toString());
					List<Incidentes> Query = sQuery.getResultList();
					for (Incidentes resultS : Query) {
						ControllerIncidents.destroy(resultS.getId());
					}
					RefreshServicesT();
					RefreshTable();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public void RefreshTable() {
		IncidentesModel.setRowCount(0);
		Incidentes.revalidate();
		Incidentes.repaint();
		String consultIncidents = "SELECT e FROM Incidentes e WHERE e.cliente = :cliente AND e.resuelto = :resuelto";
		Query iQuery = eM.createQuery(consultIncidents);
		iQuery.setParameter("cliente", User.getIdentificacion());
		iQuery.setParameter("resuelto", false);
		List<Incidentes> Query = iQuery.getResultList();
		for (Incidentes result : Query) {
			String Estado = "No";
			if (result.getResuelto() == true) {
				Estado = "Si";
			}
			Object[] queryResult = { result.getId(),ControllerServices.findServicios(result.getServicio()).getServicio() + " [" + result.getServicio()+ "]",result.getType() };
			IncidentesModel.addRow(queryResult);
		}
	}

	public void RefreshServicesT() {
		ServicesModel.setRowCount(0);
		Services.revalidate();
		Services.repaint();
		String consultService = "SELECT e FROM Servicios e WHERE e.cliente = :cliente";
		Query sQuery = eM.createQuery(consultService);
		sQuery.setParameter("cliente", User.getIdentificacion());
		List<Servicios> sFinalQuery = sQuery.getResultList();
		if (sFinalQuery.isEmpty() == false) {
			for (Servicios fQuery : sFinalQuery) {
				Object[] serviceData = { fQuery.getId(), fQuery.getServicio() };
				ServicesModel.addRow(serviceData);
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
