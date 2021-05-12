package org.springframework.samples.petclinic.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.basicUnit.BasicUnitRepository;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointment;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {
	private final BasicUnitRepository basicUnitRepository;
	private final MedicalAppointmentRepository medicalAppointmentRepository;

	public ReportController(BasicUnitRepository basicUnitRepository,
			MedicalAppointmentRepository medicalAppointmentRepository) {
		this.basicUnitRepository = basicUnitRepository;
		this.medicalAppointmentRepository = medicalAppointmentRepository;
	}

	@GetMapping("/reports/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("report", new Report());
		return "reports/findReport";
	}

	@GetMapping("/reports")
	public String processFindForm(Report report, Map<String, Object> model) {
		Calendar calendar = calcWeek(report.getDateInit());
		Date dateStart = new Date(calendar.getTimeInMillis());

		List<MedicalAppointment> medicalAppointmentList = this.medicalAppointmentRepository.findWeek(dateStart,
				report.getDateInit());

		Collection<BasicUnit> basicUnitsList = this.basicUnitRepository.findAll();

		// empty consult
		if (medicalAppointmentList.isEmpty()) {
			return "redirect:/reports/find";
		}

		// Dados numero de atendimentos
		Map<Integer, Integer> numCaseBasicUnitWeek = new HashMap<>();
		Map<Integer, Long> timeMinimunBasicUnitWeek = new HashMap<>();
		Map<Integer, Long> calcTimeAverageBasicUnitWeek = new HashMap<>();
		Map<Integer, Long> timeMaximunBasicUnitWeek = new HashMap<>();
		Map<Integer, List<Long>> timeAverageBasicUnitWeek = new HashMap<>();
		for (BasicUnit basicUnit : basicUnitsList) {
			numCaseBasicUnitWeek.put(basicUnit.getId(), 0);
			timeMinimunBasicUnitWeek.put(basicUnit.getId(), Long.MAX_VALUE);
			calcTimeAverageBasicUnitWeek.put(basicUnit.getId(), Long.valueOf(0));
			timeMaximunBasicUnitWeek.put(basicUnit.getId(), Long.MIN_VALUE);
			timeAverageBasicUnitWeek.put(basicUnit.getId(), new ArrayList<Long>(2));
			for (int i = 0; i < 2; i++) {
				timeAverageBasicUnitWeek.get(basicUnit.getId()).add(Long.valueOf(0));
			}
		}

		// Encontra qtd consulta em um unidade no periodo de uma semana

		for (MedicalAppointment medicalAppointment : medicalAppointmentList) {
			if (numCaseBasicUnitWeek.containsKey(medicalAppointment.getBasicUnit().getId())) {
				int aux = numCaseBasicUnitWeek.get(medicalAppointment.getBasicUnit().getId()) + 1;
				numCaseBasicUnitWeek.put(medicalAppointment.getBasicUnit().getId(), aux);
			}
			if (timeMinimunBasicUnitWeek.containsKey(medicalAppointment.getBasicUnit().getId())) {
				long timeMinimun = timeMinimunBasicUnitWeek.get(medicalAppointment.getBasicUnit().getId());
				if (timeMinimun > calcTime(medicalAppointment.getStartTime(), medicalAppointment.getEndTime())) {
					timeMinimunBasicUnitWeek.put(medicalAppointment.getBasicUnit().getId(),
							calcTime(medicalAppointment.getStartTime(), medicalAppointment.getEndTime()));
				}
			}
			if (timeMaximunBasicUnitWeek.containsKey(medicalAppointment.getBasicUnit().getId())) {
				long timeMinimun = timeMaximunBasicUnitWeek.get(medicalAppointment.getBasicUnit().getId());
				if (timeMinimun < calcTime(medicalAppointment.getStartTime(), medicalAppointment.getEndTime())) {
					timeMaximunBasicUnitWeek.put(medicalAppointment.getBasicUnit().getId(),
							calcTime(medicalAppointment.getStartTime(), medicalAppointment.getEndTime()));
				}
			}
			if (timeAverageBasicUnitWeek.containsKey(medicalAppointment.getBasicUnit().getId())) {
				long auxQtdAtendimentos = timeAverageBasicUnitWeek.get(medicalAppointment.getBasicUnit().getId()).get(0) + 1;
				// timeAverageBasicUnitWeek.get(medicalAppointment.getIdBasicUnit()).add(0,
				// auxQtdAtendimentos);
				long auxTempoMedio = timeAverageBasicUnitWeek.get(medicalAppointment.getBasicUnit().getId()).get(1)
						+ calcTime(medicalAppointment.getStartTime(), medicalAppointment.getEndTime());
				// timeAverageBasicUnitWeek.get(medicalAppointment.getIdBasicUnit()).add(1,
				// auxTempoMedio);
				List<Long> newList = new ArrayList<>(2);
				newList.add(auxQtdAtendimentos);
				newList.add(auxTempoMedio);
				timeAverageBasicUnitWeek.put(medicalAppointment.getBasicUnit().getId(), newList);
			}

		}
		//tratament
		for (int k :  timeAverageBasicUnitWeek.keySet()) {
			long average = 0;
			if(timeAverageBasicUnitWeek.get(k).get(0) != 0) {
				average = timeAverageBasicUnitWeek.get(k).get(1)/timeAverageBasicUnitWeek.get(k).get(0);
			}
			if(timeMinimunBasicUnitWeek.get(k) == Long.MAX_VALUE) {
				timeMinimunBasicUnitWeek.put(k, Long.valueOf(0));
			}
			if(timeMaximunBasicUnitWeek.get(k) == Long.MIN_VALUE) {
				timeMaximunBasicUnitWeek.put(k, Long.valueOf(0));
			}
			calcTimeAverageBasicUnitWeek.put(k, average);
		}
		
		
		//System.out.println(calcTimeAverageBasicUnitWeek);
		// multiple medicalAppointment found
		numCaseBasicUnitWeek = sortByValue(numCaseBasicUnitWeek);
		
		List<BasicUnit> ordenedBasicUnitsList = new ArrayList<>(basicUnitsList.size());
		for (int k :  numCaseBasicUnitWeek.keySet()) {
			ordenedBasicUnitsList.add(this.basicUnitRepository.findById(k));
		}
		
		model.put("calcTimeAverageBasicUnitWeek", calcTimeAverageBasicUnitWeek);
		model.put("timeMaxumunBasicUnitWeek", timeMaximunBasicUnitWeek);
		model.put("timeMinimunBasicUnitWeek", timeMinimunBasicUnitWeek);
		model.put("numCaseBasicUnitWeek", numCaseBasicUnitWeek);
		model.put("basicUnits", ordenedBasicUnitsList);
		return "reports/reportDetails";

	}

	private Calendar calcWeek(Date init) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(init);
		calendarDate.add(Calendar.DATE, -7);
		return calendarDate;
	}

	private long calcTime(Time start, Time end) {
		long diff = (Math.abs(end.getTime() - start.getTime()) / 1000) / 60;
		return diff;
	}
	
	private Map<Integer, Integer> sortByValue(Map<Integer, Integer> wordCounts) {

        return wordCounts.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }
}
