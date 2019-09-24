package com.attack.service;

import java.util.Map;

import com.attack.entity.exam.ExamGroup;
import com.attack.entity.user.Student;

public interface ExamGroupService {

	public Map<String, Object> getExamGroupInfo(int limit, int offset, String searchType, String searchText, Student student);

	public Map<String, Object> getExamGroupForTeacherInfo(int limit, int offset, String searchType, String searchText);



	public Map<String, Object> queryAllSubjectAndExam();

	public void saveExamGroup(ExamGroup examGroup);

	public void updateExamGroupById(ExamGroup examGroup);

	public void deleteExamGroupById(int examGroup_id);

	public Map<String, Object> getExamGroupForAdminInfo(int limit, int offset, String searchText);



}
