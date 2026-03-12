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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import g6c006.Classes.Empresa;
import g6c006.Classes.Especialidades;
import g6c006.Classes.Servicios;
import g6c006.Classes.companyServices;
import g6c006.Classes.companySpecialties;
import g6c006.Controllers.EmpresaJPA;
import g6c006.Controllers.EspecialidadesJPA;
import g6c006.Controllers.ServiciosJPA;
import g6c006.Controllers.companyServicesJPA;
import g6c006.Controllers.companySpecialtiesJPA;
import g6c006.Misc.NonEditableTableModel;
import g6c006.Misc.NonexistentEntityException;
import g6c006.Misc.PreexistingEntityException;

@SuppressWarnings("all")
public class cEmpresa extends JPanel {

	private EmpresaJPA Controller = new EmpresaJPA();
	private ServiciosJPA ControllerCustomerS = new ServiciosJPA();
	private EspecialidadesJPA ControllerTechnicE = new EspecialidadesJPA();
	private companyServicesJPA ControllerServices = new companyServicesJPA();
	private companySpecialtiesJPA ControllerSpecialties = new companySpecialtiesJPA();

	private JTextField Service;
	private JTextField Speciality;
	private JLabel lbInfo;
	private JTable Company;
	private JTable Services;
	private JTable Specialties;

	private NonEditableTableModel companyModel;
	private NonEditableTableModel servicesModel;
	private NonEditableTableModel specialtiesModel;

	private EntityManagerFactory eMF = Persistence.createEntityManagerFactory("tIntegradorPU");
	private EntityManager eM = eMF.createEntityManager();

	public cEmpresa() {
		setBackground(Color.BLACK);
		setLayout(null);

		JPanel ContentEmpresa = new JPanel();
		ContentEmpresa.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContentEmpresa.setBackground(Color.GRAY);
		ContentEmpresa.setBounds(0, 0, 547, 428);
		add(ContentEmpresa);
		ContentEmpresa.setLayout(null);

		Service = new JTextField();
		Service.setHorizontalAlignment(SwingConstants.CENTER);
		Service.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Service.setText("Servicio");
		Service.setBounds(10, 36, 255, 20);
		ContentEmpresa.add(Service);
		Service.setColumns(10);

		Speciality = new JTextField();
		Speciality.setHorizontalAlignment(SwingConstants.CENTER);
		Speciality.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Speciality.setText("Especialidad");
		Speciality.setColumns(10);
		Speciality.setBounds(282, 36, 255, 20);
		ContentEmpresa.add(Speciality);

		lbInfo = new JLabel("");
		lbInfo.setBackground(new Color(240, 240, 240));
		lbInfo.setFont(new Font("Impact", Font.PLAIN, 10));
		lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbInfo.setBounds(10, 11, 527, 14);
		ContentEmpresa.add(lbInfo);

		Company = new JTable();
		Company.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Company.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		companyModel = new NonEditableTableModel(new Object[] { "Servicio", "Especialidad" }, 0);
		Company.setModel(companyModel);
		companyModel.setColumnEditable(0, false);
		Company.setBounds(10, 305, 527, 78);
		ContentEmpresa.add(Company);
		JScrollPane tableCompany = new JScrollPane(Company);
		tableCompany.setBounds(10, 288, 527, 95);
		ContentEmpresa.add(tableCompany);
		RefreshLinks();

		JButton Unassign = new JButton("Desvincular");
		Unassign.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		Unassign.setBackground(new Color(255, 167, 79));
		Unassign.setBounds(10, 394, 527, 23);
		ContentEmpresa.add(Unassign);

		JButton addSpecialtie = new JButton("Crear Especialidad");
		addSpecialtie.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		addSpecialtie.setBackground(new Color(145, 255, 145));
		addSpecialtie.setBounds(282, 67, 255, 23);
		ContentEmpresa.add(addSpecialtie);

		JButton addService = new JButton("Crear Servicio");
		addService.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		addService.setBackground(new Color(145, 255, 145));
		addService.setBounds(10, 67, 255, 23);
		ContentEmpresa.add(addService);

		Services = new JTable();
		Services.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Services.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servicesModel = new NonEditableTableModel(new Object[] { "Servicios" }, 0);
		Services.setModel(servicesModel);
		servicesModel.setColumnEditable(0, false);
		Services.setBounds(10, 76, 242, 125);
		ContentEmpresa.add(Services);
		JScrollPane tableServices = new JScrollPane(Services);
		tableServices.setBounds(10, 101, 255, 108);
		ContentEmpresa.add(tableServices);
		RefreshServices();

		Specialties = new JTable();
		Specialties.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		Specialties.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		specialtiesModel = new NonEditableTableModel(new Object[] { "Especialidades" }, 0);
		Specialties.setModel(specialtiesModel);
		specialtiesModel.setColumnEditable(0, false);
		Specialties.setBounds(295, 76, 242, 125);
		ContentEmpresa.add(Specialties);
		JScrollPane tableSpecialties = new JScrollPane(Specialties);
		tableSpecialties.setBounds(282, 101, 255, 108);
		ContentEmpresa.add(tableSpecialties);
		RefreshSpecialties();

		JButton Assign = new JButton("Vincular");
		Assign.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		Assign.setBackground(new Color(255, 255, 128));
		Assign.setBounds(10, 254, 527, 23);
		ContentEmpresa.add(Assign);

		JButton delService = new JButton("Eliminar Servicio");
		delService.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		delService.setBackground(new Color(255, 145, 145));
		delService.setBounds(10, 220, 255, 23);
		ContentEmpresa.add(delService);

		JButton delSpecialtie = new JButton("Eliminar Especialidad");
		delSpecialtie.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		delSpecialtie.setBackground(new Color(255, 145, 145));
		delSpecialtie.setBounds(282, 220, 255, 23);
		ContentEmpresa.add(delSpecialtie);

		JLabel Marco1 = new JLabel("");
		Marco1.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco1.setBounds(7, 33, 261, 60);
		ContentEmpresa.add(Marco1);

		JLabel Marco2 = new JLabel("");
		Marco2.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco2.setBounds(279, 33, 261, 60);
		ContentEmpresa.add(Marco2);

		JLabel Marco3 = new JLabel("");
		Marco3.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco3.setBounds(7, 99, 533, 181);
		ContentEmpresa.add(Marco3);

		JLabel Marco4 = new JLabel("");
		Marco4.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco4.setBounds(7, 286, 533, 133);
		ContentEmpresa.add(Marco4);

		addService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyServices cService = new companyServices(Service.getText());
				try {
					lbInfo.setText("Servicio (" + Service.getText() + ") creado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(145, 255, 145));
					ControllerServices.create(cService);
					RefreshServices();
				} catch (PreexistingEntityException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		addSpecialtie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companySpecialties cSpecialties = new companySpecialties(Speciality.getText());
				try {
					lbInfo.setText("Especialidad (" + Speciality.getText() + ") creada exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(145, 255, 145));
					ControllerSpecialties.create(cSpecialties);
					RefreshSpecialties();
				} catch (PreexistingEntityException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		delService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lbInfo.setText("Servicio (" + servicesModel.getValueAt(Services.getSelectedRow(), 0)+ ") eliminado exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 167, 79));
					ControllerServices.destroy(servicesModel.getValueAt(Services.getSelectedRow(), 0).toString());
					String delCompany = "SELECT e FROM Empresa e WHERE e.servicio = :servicio";
					Query Query = eM.createQuery(delCompany);
					Query.setParameter("servicio", servicesModel.getValueAt(Services.getSelectedRow(), 0).toString());
					List<Empresa> companyResult = Query.getResultList();
					for (Empresa cResult : companyResult) {
						Controller.destroy(cResult.getId());
					}
					String delService = "SELECT e FROM Servicios e WHERE e.servicio = :servicio";
					Query sQuery = eM.createQuery(delService);
					sQuery.setParameter("servicio", servicesModel.getValueAt(Services.getSelectedRow(), 0).toString());
					List<Servicios> serviceResult = sQuery.getResultList();
					for (Servicios sResult : serviceResult) {
						ControllerCustomerS.destroy(sResult.getId());
					}
					RefreshServices();
					RefreshLinks();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				}
			}
		});

		delSpecialtie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lbInfo.setText("Especialidad (" + specialtiesModel.getValueAt(Specialties.getSelectedRow(), 0)+ ") eliminada exitosamente");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 167, 79));
					ControllerSpecialties.destroy(specialtiesModel.getValueAt(Specialties.getSelectedRow(), 0).toString());
					String delCompany = "SELECT e FROM Empresa e WHERE e.especialidad = :especialidad";
					Query Query = eM.createQuery(delCompany);
					Query.setParameter("especialidad",specialtiesModel.getValueAt(Specialties.getSelectedRow(), 0).toString());
					List<Empresa> companyResult = Query.getResultList();
					for (Empresa cResult : companyResult) {
						Controller.destroy(cResult.getId());
					}
					String delSpecialtie = "SELECT e FROM Especialidades e WHERE e.especialidad = :especialidad";
					Query eQuery = eM.createQuery(delSpecialtie);
					eQuery.setParameter("especialidad",specialtiesModel.getValueAt(Specialties.getSelectedRow(), 0).toString());
					List<Especialidades> specialtieResult = eQuery.getResultList();
					for (Especialidades eResult : specialtieResult) {
						ControllerTechnicE.destroy(eResult.getId());
					}
					RefreshSpecialties();
					RefreshLinks();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				}
			}
		});

		Assign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Consult = "SELECT COUNT(e) FROM Empresa e WHERE e.servicio = :servicio AND e.especialidad = :especialidad";
				Query Query = eM.createQuery(Consult);
				Query.setParameter("servicio", servicesModel.getValueAt(Services.getSelectedRow(), 0).toString());
				Query.setParameter("especialidad",specialtiesModel.getValueAt(Specialties.getSelectedRow(), 0).toString());
				List<Empresa> rQuery = Query.getResultList();
				if (Integer.parseInt(Query.getSingleResult().toString()) < 1) {
					Empresa Company = new Empresa(UUID.randomUUID().toString(),servicesModel.getValueAt(Services.getSelectedRow(), 0).toString(),specialtiesModel.getValueAt(Specialties.getSelectedRow(), 0).toString());
					try {
						lbInfo.setText("Servicio (" + servicesModel.getValueAt(Services.getSelectedRow(), 0)+ ") vinculado exitosamente a la especialidad ("+ specialtiesModel.getValueAt(Specialties.getSelectedRow(), 0) + ")");
						InfoRestore();
						lbInfo.setForeground(new Color(145, 255, 145));
						Controller.create(Company);
						RefreshLinks();
					} catch (PreexistingEntityException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					lbInfo.setText("El servicio ya esta vinculado a esa especialidad");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 145, 145));
				}
			}
		});

		Unassign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Consult = "SELECT e FROM Empresa e WHERE e.servicio = :servicio AND e.especialidad = :especialidad";
				Query Query = eM.createQuery(Consult);
				Query.setParameter("servicio", companyModel.getValueAt(Company.getSelectedRow(), 0).toString());
				Query.setParameter("especialidad", companyModel.getValueAt(Company.getSelectedRow(), 1).toString());
				List<Empresa> rQuery = Query.getResultList();
				try {
					lbInfo.setText("Servicio (" + companyModel.getValueAt(Company.getSelectedRow(), 0)+ ") desvinculado exitosamente de la especialidad ("+ companyModel.getValueAt(Company.getSelectedRow(), 1) + ")");
					InfoRestore();
					lbInfo.setForeground(new Color(255, 167, 79));
					Controller.destroy(rQuery.get(0).getId().toString());
					RefreshLinks();
				} catch (NonexistentEntityException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void RefreshServices() {
		servicesModel.setRowCount(0);
		Services.revalidate();
		Services.repaint();
		List<companyServices> servis = ControllerServices.findcompanyServicesEntities();
		for (companyServices serv : servis) {
			Object[] serviceData = { serv.getServicio() };
			servicesModel.addRow(serviceData);
		}
	}

	public void RefreshSpecialties() {
		specialtiesModel.setRowCount(0);
		Specialties.revalidate();
		Specialties.repaint();
		List<companySpecialties> specialts = ControllerSpecialties.findcompanySpecialtiesEntities();
		for (companySpecialties special : specialts) {
			Object[] specialtiesData = { special.getEspecialidad() };
			specialtiesModel.addRow(specialtiesData);
		}
	}

	public void RefreshLinks() {
		companyModel.setRowCount(0);
		Company.revalidate();
		Company.repaint();
		List<Empresa> Empress = Controller.findEmpresaEntities();
		for (Empresa Company : Empress) {
			Object[] empresaData = { Company.getServicio(), Company.getEspecialidad() };
			companyModel.addRow(empresaData);
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
