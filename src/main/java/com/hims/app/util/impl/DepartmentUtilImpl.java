package com.hims.app.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hims.app.dto.department.DepartmentDetail;
import com.hims.app.dto.department.DepartmentRegisterForm;
import com.hims.app.dto.department.DepartmentSummary;
import com.hims.app.model.Department;
import com.hims.app.model.Doctor;
import com.hims.app.model.Nurse;
import com.hims.app.model.field.DoctorRank;
import com.hims.app.model.field.NurseRank;
import com.hims.app.util.DepartmentUtil;

@Component
public class DepartmentUtilImpl implements DepartmentUtil {

	@Override
	public Department mapToEntity(DepartmentRegisterForm form) {
		return Department.builder().name(form.name()).description(form.description()).access(form.access()).build();
	}

	@Override
	public DepartmentSummary mapToDepartmentSummary(Department department) {
		return new DepartmentSummary(department.getId(), department.getName(), department.getDescription());
	}

	@Override
	public DepartmentDetail mapToDepartmentDetail(Department department) {
		long doctors = department.getDoctors().stream().filter(doctor -> doctor.isActive()).count();
		long nurses = department.getNurses().stream().filter(nurse -> nurse.isActive()).count();
		return new DepartmentDetail(department.getId(), department.getName(), department.getDescription(),
				department.isAccess(), doctors, nurses, separateDoctorsByRank(department.getDoctors()), separateNursesByRank(department.getNurses()));
	}

	private Map<String, Long> separateDoctorsByRank(List<Doctor> doctors) {
		List<DoctorRank> ranks = List.of(DoctorRank.ATTENDING_PHYSICIAN, DoctorRank.FELLOW, DoctorRank.CHIEF_RESIDENT,
				DoctorRank.SENIOR_RESIDENT, DoctorRank.JUNIOR_RESIDENT, DoctorRank.INTERN);
		Map<DoctorRank, String> ranksAbbr = Map.of(DoctorRank.ATTENDING_PHYSICIAN, "ATP", DoctorRank.FELLOW, "FEL", DoctorRank.CHIEF_RESIDENT, "CFR",
				DoctorRank.SENIOR_RESIDENT, "SRR", DoctorRank.JUNIOR_RESIDENT, "JRR", DoctorRank.INTERN, "INT");
		Map<String, Long> doctorsByRank = new HashMap<>();
		for(DoctorRank rank : ranks) {
			long count = doctors.stream().filter(doctor -> doctor.getDoctorRank() == rank).count();
			doctorsByRank.put(ranksAbbr.get(rank), count);
		}
		return doctorsByRank;
	}
	
	private Map<String, Long> separateNursesByRank(List<Nurse> nurses) {
		List<NurseRank> ranks = List.of(NurseRank.SUPERVISOR, NurseRank.ASSISTANT, NurseRank.INTERN);
		Map<NurseRank, String> ranksAbbr = Map.of(NurseRank.SUPERVISOR, "SUV", NurseRank.ASSISTANT, "ASS", NurseRank.INTERN, "INT");
		Map<String, Long> nursesByRank = new HashMap<>();
		for(NurseRank rank: ranks) {
			long count = nurses.stream().filter(nurse -> nurse.getNurseRank() == rank).count();
			nursesByRank.put(ranksAbbr.get(rank), count);
		}
		return nursesByRank;
	}

}
