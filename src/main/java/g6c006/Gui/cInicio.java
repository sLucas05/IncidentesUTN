package g6c006.Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;

import g6c006.Classes.Especialidades;
import g6c006.Classes.Incidentes;
import g6c006.Classes.Tecnicos;
import g6c006.Classes.companySpecialties;
import g6c006.Controllers.EspecialidadesJPA;
import g6c006.Controllers.TecnicosJPA;
import g6c006.Controllers.companySpecialtiesJPA;
import g6c006.Misc.NonEditableTableModel;
import g6c006.Misc.NonexistentEntityException;

@SuppressWarnings("all")
public class cInicio extends JPanel {

	private TecnicosJPA ControllerTechnics = new TecnicosJPA();
	private EspecialidadesJPA ControllerSpecialties = new EspecialidadesJPA();
	private companySpecialtiesJPA ControllerCompanySpecialties = new companySpecialtiesJPA();

	private Integer DaysSearch;
	private String SpecialtieSearch;

	private JTable incidentsSolveds;
	private JTable promedioSolveds;
	private JTextField textField;
	private JComboBox comboBox;

	private LocalDate date;

	private NonEditableTableModel solvedsModel;
	private NonEditableTableModel promedioModel;

	private EntityManagerFactory eMF = Persistence.createEntityManagerFactory("tIntegradorPU");
	private EntityManager eM = eMF.createEntityManager();

	public cInicio() {
		DaysSearch = 3650;
		SpecialtieSearch = "General";
		setBackground(Color.BLACK);
		setLayout(null);

		JPanel ContentInicio = new JPanel();
		ContentInicio.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContentInicio.setBackground(Color.GRAY);
		ContentInicio.setBounds(0, 0, 547, 428);
		add(ContentInicio);
		ContentInicio.setLayout(null);

		incidentsSolveds = new JTable();
		incidentsSolveds.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		incidentsSolveds.setBounds(10, 44, 527, 181);
		ContentInicio.add(incidentsSolveds);
		incidentsSolveds.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		solvedsModel = new NonEditableTableModel(new Object[] { "Tecnico", "Incidentes Solucionados" }, 0);
		incidentsSolveds.setModel(solvedsModel);
		solvedsModel.setColumnEditable(0, false);
		solvedsModel.setColumnEditable(1, false);
		incidentsSolveds.getColumnModel().getColumn(0).setResizable(false);
		incidentsSolveds.getColumnModel().getColumn(0).setPreferredWidth(125);
		incidentsSolveds.getColumnModel().getColumn(1).setResizable(false);
		JScrollPane tablesolveds = new JScrollPane(incidentsSolveds);
		tablesolveds.setBounds(10, 44, 527, 181);
		ContentInicio.add(tablesolveds);
		AddSolvedsTop();

		promedioSolveds = new JTable();
		promedioSolveds.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		promedioSolveds.setBounds(10, 236, 527, 181);
		ContentInicio.add(promedioSolveds);
		promedioSolveds.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		promedioModel = new NonEditableTableModel(new Object[] { "Tecnico", "Tiempo Promedio" }, 0);
		promedioSolveds.setModel(promedioModel);
		promedioModel.setColumnEditable(0, false);
		promedioModel.setColumnEditable(1, false);
		promedioSolveds.getColumnModel().getColumn(0).setResizable(false);
		promedioSolveds.getColumnModel().getColumn(0).setPreferredWidth(125);
		promedioSolveds.getColumnModel().getColumn(1).setResizable(false);
		JScrollPane tablepromedio = new JScrollPane(promedioSolveds);
		tablepromedio.setBounds(10, 236, 527, 181);
		ContentInicio.add(tablepromedio);
		AddPromedioTop();

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		textField.setText("3650");
		textField.setBounds(234, 11, 43, 22);
		ContentInicio.add(textField);
		textField.setColumns(10);

		JLabel lbResueltos = new JLabel("Registro de incidentes resueltos en los ultimos ");
		lbResueltos.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		lbResueltos.setBounds(13, 11, 223, 22);
		ContentInicio.add(lbResueltos);

		JLabel lblNewLabel = new JLabel("dias, por la especialidad");
		lblNewLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		lblNewLabel.setBounds(284, 11, 119, 22);
		ContentInicio.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "General" }));
		comboBox.setBounds(404, 11, 133, 22);
		ContentInicio.add(comboBox);

		JLabel Marco1 = new JLabel("");
		Marco1.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco1.setBounds(8, 8, 532, 220);
		ContentInicio.add(Marco1);

		JLabel Marco2 = new JLabel("");
		Marco2.setIcon(new ImageIcon(getClass().getResource("/images/Marco.png")));
		Marco2.setBounds(8, 234, 532, 186);
		ContentInicio.add(Marco2);

		List<companySpecialties> cSpec = ControllerCompanySpecialties.findcompanySpecialtiesEntities();
		for (companySpecialties cSp : cSpec) {
			comboBox.addItem(cSp.getEspecialidad());
		}

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String key = textField.getText();
				if (!key.isEmpty()) {
					try {
						DaysSearch = Integer.parseInt(textField.getText());
						AddSolvedsTop();
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpecialtieSearch = comboBox.getSelectedItem().toString();
				AddSolvedsTop();
			}
		});
	}

	public void AddSolvedsTop() {
		solvedsModel.setRowCount(0);
		incidentsSolveds.revalidate();
		incidentsSolveds.repaint();
		String Consult = "SELECT e FROM Incidentes e WHERE e.reportDate BETWEEN :fechaLimite AND :fechaActual";
		String Count = "SELECT COUNT(e) FROM Incidentes e WHERE e.tecnico = :tecnico AND e.resuelto = :resuelto AND e.reportDate BETWEEN :fechaLimite AND :fechaActual";
		String consultSpecialtie = "SELECT e FROM Especialidades e WHERE e.especialidad IN :especialidad";
		LocalDate nowDate = LocalDate.now();
		LocalDate limitDate = nowDate.minusDays(DaysSearch);
		Date sqlLimitDate = java.sql.Date.valueOf(limitDate.atStartOfDay().toLocalDate());
		Date sqlNowDate = java.sql.Date.valueOf(nowDate.atStartOfDay().toLocalDate());
		Query Query = eM.createQuery(Consult);
		Query.setParameter("fechaLimite", sqlLimitDate);
		Query.setParameter("fechaActual", sqlNowDate);
		HashSet<Object> tableValues = new HashSet<>();
		List<Incidentes> resultados = Query.getResultList();
		if (SpecialtieSearch.equals("General")) {
			for (Incidentes resultado : resultados) {
				Query cQuery = eM.createQuery(Count);
				cQuery.setParameter("tecnico", resultado.getTecnico());
				cQuery.setParameter("fechaLimite", sqlLimitDate);
				cQuery.setParameter("fechaActual", sqlNowDate);
				cQuery.setParameter("resuelto", true);
				List<Incidentes> countQuery = cQuery.getResultList();
				if (!tableValues.contains(ControllerTechnics.findTecnicos(resultado.getTecnico()).getNyA())) {
					tableValues.add(ControllerTechnics.findTecnicos(resultado.getTecnico()).getNyA());
					Object[] resultQuery = {ControllerTechnics.findTecnicos(resultado.getTecnico()).getNyA() + " [ID: "+ ControllerTechnics.findTecnicos(resultado.getTecnico()).getIdentificacion() + "]",countQuery.get(0) };
					solvedsModel.addRow(resultQuery);
				}
			}
		} else {
			List<String> specialtieList = new ArrayList<>();
			List<String> technicList = new ArrayList<>();
			HashSet<Object> tableValuesTwo = new HashSet<>();
			specialtieList.add(SpecialtieSearch);
			Query sQuery = eM.createQuery(consultSpecialtie);
			sQuery.setParameter("especialidad", specialtieList);
			List<Especialidades> specialtieQuery = sQuery.getResultList();
			for (Especialidades sFinalQuery : specialtieQuery) {
				technicList.add(sFinalQuery.getTecnico().toString());
			}
			for (String technic : technicList) {
				for (Incidentes resultado : resultados) {
					Query cQueryTwo = eM.createQuery(Count);
					cQueryTwo.setParameter("tecnico", Integer.parseInt(technic));
					cQueryTwo.setParameter("fechaLimite", sqlLimitDate);
					cQueryTwo.setParameter("fechaActual", sqlNowDate);
					cQueryTwo.setParameter("resuelto", true);
					List<Incidentes> countQueryTwo = cQueryTwo.getResultList();
					if (!tableValuesTwo.contains(ControllerTechnics.findTecnicos(Integer.parseInt(technic)).getNyA())) {
						tableValuesTwo.add(ControllerTechnics.findTecnicos(Integer.parseInt(technic)).getNyA());
						if (cQueryTwo.getSingleResult().toString().equals("0")) {
						} else {
							Object[] resultQueryTwo = {
									ControllerTechnics.findTecnicos(Integer.parseInt(technic)).getNyA() + " [ID: "
											+ ControllerTechnics.findTecnicos(Integer.parseInt(technic))
													.getIdentificacion()
											+ "]",
									countQueryTwo.get(0) };
							solvedsModel.addRow(resultQueryTwo);
						}
					}
				}
			}
		}
		TableRowSorter<NonEditableTableModel> orderTableSolveds = new TableRowSorter<NonEditableTableModel>(solvedsModel);
		incidentsSolveds.setRowSorter(orderTableSolveds);
		incidentsSolveds.getRowSorter().toggleSortOrder(1);
		incidentsSolveds.getRowSorter().toggleSortOrder(1);
	}

	public void AddPromedioTop() {
		String averageConsult = "SELECT e FROM Incidentes e";
		Query aQuery = eM.createQuery(averageConsult);
		List<Incidentes> averageQuery = aQuery.getResultList();
		List<Integer> averageTechnics = new ArrayList<>();
		for (Incidentes avg : averageQuery) {
			if (avg.getResuelto() == true) {
				if (!averageTechnics.contains(avg.getTecnico())) {
					averageTechnics.add(avg.getTecnico());
				}
			}
		}
		for (Integer average : averageTechnics) {
			int PromedioH = 0;
			int PromedioM = 0;
			String averageTechnic = "SELECT e FROM Incidentes e WHERE e.tecnico = :tecnico";
			String averageCount = "SELECT COUNT(e) FROM Incidentes e WHERE e.tecnico = :tecnico";
			Query averageFinalQ = eM.createQuery(averageTechnic);
			averageFinalQ.setParameter("tecnico", average);
			Query averageFinalQtwo = eM.createQuery(averageCount);
			averageFinalQtwo.setParameter("tecnico", average);
			List<Incidentes> avgQuery = averageFinalQ.getResultList();
			for (Incidentes averageFinal : avgQuery) {
				if (averageFinal.getResuelto() == true) {
					DateTimeFormatter Format = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz uuuu",Locale.ENGLISH);
					LocalDateTime reportDate = LocalDateTime.parse(averageFinal.getReportDate().toString(), Format);
					LocalDateTime solvedDate = LocalDateTime.parse(averageFinal.getSolvDate().toString(), Format);
					LocalDateTime fechaHoraStart = LocalDateTime.of(reportDate.getYear(), reportDate.getMonthValue(),reportDate.getDayOfMonth(), averageFinal.getReportTime().getHour(),averageFinal.getReportTime().getMinute());
					LocalDateTime fechaHoraEnd = LocalDateTime.of(solvedDate.getYear(), solvedDate.getMonthValue(),solvedDate.getDayOfMonth(), averageFinal.getSolvTime().getHour(),averageFinal.getSolvTime().getMinute());
					Duration duration = Duration.between(fechaHoraStart, fechaHoraEnd);
					double hTotales = duration.toMinutes();
					double avgHT = Math.floor(hTotales / 60);
					double avgMT = hTotales / 60;
					BigDecimal BD = new BigDecimal(avgMT);
					BigDecimal decimalPart = BD.remainder(BigDecimal.ONE);
					BigDecimal Decimal = decimalPart.multiply(new BigDecimal("100"));
					int HT = (int) avgHT;
					int MT = Decimal.intValue();
					if (MT < 0) {
						MT = MT + 60;
						HT = HT - 1;
					}
					if (MT > 60) {
						MT = MT - 60;
						HT = HT + 1;
					}
					if (MT == 60) {
						MT = 0;
						HT = HT + 1;
					}
					PromedioH = PromedioH + HT / Integer.parseInt(averageFinalQtwo.getSingleResult().toString());
					PromedioM = PromedioM + MT / Integer.parseInt(averageFinalQtwo.getSingleResult().toString());
				}
			}
			Tecnicos Tec = ControllerTechnics.findTecnicos(average);
			Object[] averageResult = { Tec.getNyA() + " [ID: " + average + "]", PromedioH + "h " + PromedioM + "m" };
			promedioModel.addRow(averageResult);
			Tec.setEstimatedT(PromedioH + "h " + PromedioM + "m");
			try {
				ControllerTechnics.edit(Tec);
			} catch (NonexistentEntityException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		TableRowSorter<NonEditableTableModel> orderTablePromedio = new TableRowSorter<NonEditableTableModel>(promedioModel);
		promedioSolveds.setRowSorter(orderTablePromedio);
		promedioSolveds.getRowSorter().toggleSortOrder(1);
		promedioSolveds.getRowSorter().toggleSortOrder(1);
	}
}
