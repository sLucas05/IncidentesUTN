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

import g6c006.Classes.Clientes;
import g6c006.Classes.Incidentes;
import g6c006.Classes.Servicios;
import g6c006.Classes.companyServices;
import g6c006.Controllers.ClientesJPA;
import g6c006.Controllers.IncidentesJPA;
import g6c006.Controllers.ServiciosJPA;
import g6c006.Controllers.companyServicesJPA;
import g6c006.Misc.NonEditableTableModel;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class cComercial extends JPanel {

	private ClientesJPA Controller = new ClientesJPA();
	private IncidentesJPA ControllerIncidents = new IncidentesJPA();
	private companyServicesJPA ControllerServices = new companyServicesJPA();
	private ServiciosJPA ControllerServicesClient = new ServiciosJPA();

	private JTextField NyA;
	private JTextField DNI;
	private JTextField rSocial;
	private JTextField Email;
	private JTextField Phone;
	private JTable Customers;
	private JComboBox Services;
	private JLabel lbInfo;

	private NonEditableTableModel customersModel;

	private EntityManagerFactory eMF = Persistence.createEntityManagerFactory("tIntegradorPU");
	private EntityManager eM = eMF.createEntityManager();

	public cComercial() {
		setBackground(Color.BLACK);
		setLayout(null);

		JPanel ContentComercial = new JPanel();
		ContentComercial.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContentComercial.setBackground(Color.GRAY);
		ContentComercial.setBounds(0, 0, 547, 428);
		add(ContentComercial);
		ContentComercial.setLayout(null);

		NyA = new JTextField();
		NyA.setHorizontalAlignment(SwingConstants.CENTER);
		NyA.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		NyA.setText("Nombre");
		NyA.setBounds(10, 50, 102, 20);
		ContentComercial.add(NyA);
		NyA.setColumns(10);

		DNI = new JTextField();
		DNI.setHorizontalAlignment(SwingConstants.CENTER);
		DNI.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		DNI.setText("DNI");
		DNI.setColumns(10);
		DNI.setBounds(122, 50, 95, 20);
		ContentComercial.add(DNI);

		rSocial = new JTextField();
		rSocial.setHorizontalAlignment(SwingConstants.CENTER);
		rSocial.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		rSocial.setText("Razon Social");
		rSocial.setColumns(10);
		rSocial.setBounds(227, 50, 93, 20);
		ContentComercial.add(rSocial);

		Email = new JTextField();
		Email.setHorizontalAlignment(SwingConstants.CENTER);
		Email.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Email.setText("Email");
		Email.setColumns(10);
		Email.setBounds(330, 50, 95, 20);
		ContentComercial.add(Email);

		Phone = new JTextField();
		Phone.setHorizontalAlignment(SwingConstants.CENTER);
		Phone.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Phone.setText("Celular");
		Phone.setColumns(10);
		Phone.setBounds(435, 50, 102, 20);
		ContentComercial.add(Phone);

		JButton addCustomer = new JButton("Agregar Cliente");
		addCustomer.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		addCustomer.setBackground(new Color(145, 255, 145));
		addCustomer.setBounds(10, 81, 527, 23);
		ContentComercial.add(addCustomer);

		Services = new JComboBox();
		Services.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Services.setBounds(312, 365, 225, 22);
		ContentComercial.add(Services);

		Customers = new JTable();
		Customers.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Customers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customersModel = new NonEditableTableModel(new Object[] { "ID", "Cliente", "Razon Social", "Email", "Celular" },
				0);
		Customers.setModel(customersModel);
		customersModel.setColumnEditable(0, false);
		customersModel.setColumnEditable(1, false);
		Customers.getColumnModel().getColumn(0).setResizable(false);
		Customers.getColumnModel().getColumn(0).setPreferredWidth(45);
		Customers.getColumnModel().getColumn(1).setResizable(false);
		Customers.setBounds(341, 10, 196, 145);
		ContentComercial.add(Customers);
		JScrollPane tableCustomers = new JScrollPane(Customers);
		tableCustomers.setBounds(10, 115, 527, 239);
		ContentComercial.add(tableCustomers);
		RefreshTable();

		JButton delCustomer = new JButton("Eliminar Cliente");
		delCustomer.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		delCustomer.setBackground(new Color(255, 145, 145));
		delCustomer.setBounds(10, 365, 292, 52);
		ContentComercial.add(delCustomer);

		JButton setService = new JButton("Otorgar Servicio");
		setService.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicios Service = new Servicios(UUID.randomUUID().toString(),
						Integer.parseInt(customersModel.getValueAt(Customers.getSelectedRow(), 0).toString()),
						Services.getSelectedItem().toString());
				try {
					lbInfo.setText("Servicio (" + Services.getSelectedItem()+ ") generado y asignado exitosamente al cliente (ID: "+ customersModel.getValueAt(Customers.getSelectedRow(), 0) + ")");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 255, 128));
					ControllerServicesClient.create(Service);
				} catch (PreexistingEntityException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		setService.setBackground(new Color(255, 255, 128));
		setService.setBounds(312, 394, 225, 23);
		ContentComercial.add(setService);

		JLabel Marco1 = new JLabel("");
		Marco1.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco1.setBounds(7, 47, 533, 60);
		ContentComercial.add(Marco1);

		JLabel Marco2 = new JLabel("");
		Marco2.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco2.setBounds(7, 113, 533, 308);
		ContentComercial.add(Marco2);

		lbInfo = new JLabel("");
		lbInfo.setFont(new Font("Impact", Font.PLAIN, 10));
		lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfo.setBounds(10, 11, 527, 28);
		ContentComercial.add(lbInfo);

		List<companyServices> cServ = ControllerServices.findcompanyServicesEntities();
		for (companyServices cS : cServ) {
			Services.addItem(cS.getServicio());
		}

		addCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes Customer = new Clientes(Integer.parseInt(DNI.getText()), NyA.getText(), rSocial.getText(),Email.getText(), Integer.parseInt(Phone.getText()));
				try {
					lbInfo.setText("Cliente (" + Customer.getNyA() + " [ID: " + Customer.getIdentificacion()+ "]) registrado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(145, 255, 145));
					Controller.create(Customer);
					RefreshTable();
				} catch (PreexistingEntityException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		delCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lbInfo.setText("Cliente (ID: " + customersModel.getValueAt(Customers.getSelectedRow(), 0)+ ") eliminado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 167, 79));
					Controller.destroy(Integer.parseInt(customersModel.getValueAt(Customers.getSelectedRow(), 0).toString()));
					String delIncident = "SELECT e FROM Incidentes e WHERE e.cliente = :cliente";
					Query iQuery = eM.createQuery(delIncident);
					iQuery.setParameter("cliente",Integer.parseInt(customersModel.getValueAt(Customers.getSelectedRow(), 0).toString()));
					List<Incidentes> incidentResult = iQuery.getResultList();
					for (Incidentes iResult : incidentResult) {
						ControllerIncidents.destroy(iResult.getId());
					}
					String delService = "SELECT e FROM Servicios e WHERE e.cliente = :cliente";
					Query sQuery = eM.createQuery(delService);
					sQuery.setParameter("cliente",Integer.parseInt(customersModel.getValueAt(Customers.getSelectedRow(), 0).toString()));
					List<Servicios> serviceResult = sQuery.getResultList();
					for (Servicios sResult : serviceResult) {
						ControllerServicesClient.destroy(sResult.getId());
					}
					RefreshTable();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void RefreshTable() {
		customersModel.setRowCount(0);
		Customers.revalidate();
		Customers.repaint();
		List<Clientes> clients = Controller.findClientesEntities();
		for (Clientes client : clients) {
			Object[] customersData = { client.getIdentificacion(), client.getNyA(), client.getRsocial(),client.getMail(), client.getTelefono() };
			customersModel.addRow(customersData);
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
