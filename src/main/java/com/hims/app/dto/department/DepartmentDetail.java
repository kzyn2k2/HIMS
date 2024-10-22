package com.hims.app.dto.department;

import java.util.Map;

public record DepartmentDetail(long id, String name, String description, boolean access, long doctors, long nurses,
		java.util.Map<String, Long> doctorsByRank, Map<String, Long> nursesByRank) {

}
