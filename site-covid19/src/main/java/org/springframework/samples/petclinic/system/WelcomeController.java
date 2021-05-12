/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.samples.petclinic.basicUnit.BasicUnit;
import org.springframework.samples.petclinic.basicUnit.BasicUnitRepository;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointment;
import org.springframework.samples.petclinic.medicalAppointment.MedicalAppointmentRepository;
import org.springframework.samples.petclinic.patient.Patient;
import org.springframework.samples.petclinic.patient.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {
	private final BasicUnitRepository basicUnitRepository;
	private final MedicalAppointmentRepository medicalAppointmentRepository;
	private final PatientRepository patientRepository;

	public WelcomeController(BasicUnitRepository basicUnitRepository,
			MedicalAppointmentRepository medicalAppointmentRepository, PatientRepository patientRepository) {
		this.basicUnitRepository = basicUnitRepository;
		this.medicalAppointmentRepository = medicalAppointmentRepository;
		this.patientRepository = patientRepository;
	}

	@GetMapping("/")
	public String welcome(Model model) {
		List<BasicUnit> basicUnitsList = new ArrayList<BasicUnit>();
		basicUnitsList.addAll(this.basicUnitRepository.findAll());
		
		//Dados para tabela numero de casos 
		Map<Integer, Integer> numCaseBasicUnit = new HashMap<>();
		for (BasicUnit basicUnit : basicUnitsList) {
			numCaseBasicUnit.put(basicUnit.getId(), 0);
		}
		
		//Dados para tabela numero de obitos
		Map<Integer, Integer> numDeathBasicUnit = new HashMap<>();
		
		//Pesquisa no repositorio de atendimentos quantos aconteceram para tal unidade 
		for (BasicUnit basicUnit : basicUnitsList) {
			
			List<MedicalAppointment> medicalAppointmentList =  this.medicalAppointmentRepository.findByIdBasicUnit(basicUnit.getId());
			for (MedicalAppointment medicalAppointment : medicalAppointmentList) {
				if(medicalAppointment.isTestResult() == true) {
					int cont = numCaseBasicUnit.get(basicUnit.getId());
					numCaseBasicUnit.put(basicUnit.getId(), ++cont);
				}
			}
			
			numDeathBasicUnit.put(basicUnit.getId(), 0);
		}
		
		List<Patient> patientList = new ArrayList<>();
		patientList.addAll(this.patientRepository.findAll());

		for (Patient patient : patientList) {//Percorreto todos pacientes
			if(patient.getDateDeath() != null) {//se morreu pega o ultima unidade basica que esteve
				List<MedicalAppointment> medicalAppointmentList = this.medicalAppointmentRepository.findByIdPatient(patient.getId());
				int aux = numDeathBasicUnit.get(medicalAppointmentList.get(medicalAppointmentList.size()-1).getBasicUnit().getId());
				numDeathBasicUnit.put(medicalAppointmentList.get(medicalAppointmentList.size()-1).getBasicUnit().getId(), ++aux);
			}
		}
		
		//Dados do grafico
		Map<Integer, Integer> surveyMap = new LinkedHashMap<>();
		Map<Integer, String> lethalityRate = new HashMap<>();  
		
		
		for (int key : numDeathBasicUnit.keySet()) {
			surveyMap.put(key, numDeathBasicUnit.get(key));
			//Taxa de letalidade
			if(numDeathBasicUnit.get(key) == 0) {
				lethalityRate.put(key, "0,0 %");
			}else {
			    double calcLethalityRate = (double) numDeathBasicUnit.get(key)/numCaseBasicUnit.get(key) * 100;
				lethalityRate.put(key, String.format("%.1f ", calcLethalityRate)+" %");
			}
		}
		
		model.addAttribute("lethalityRate", lethalityRate);
		model.addAttribute("numDeathBasicUnit", numDeathBasicUnit);
		model.addAttribute("numCaseBasicUnit", numCaseBasicUnit);
		model.addAttribute("basicUnits", basicUnitsList);
		model.addAttribute("surveyMap", surveyMap);

		return "welcome";
	}

}
