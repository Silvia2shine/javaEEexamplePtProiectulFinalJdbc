package com.siit.studentRepoExample.service;

import com.siit.studentRepoExample.model.Student;
import com.siit.studentRepoExample.model.dto.CreateStudentDto;
import com.siit.studentRepoExample.repository.JpaStudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class StudentService {
    private final JpaStudentRepository jpaStudentRepository;

    public void addStudent(CreateStudentDto createStudentDto) {
        if (!validateStudentData(createStudentDto)) {
            throw new RuntimeException("invalid data for student: name was {}, email was{}".formatted(createStudentDto.getName(), createStudentDto.getEmail()));
        }

        Student student = new Student();
        student.setName(createStudentDto.getName());
        student.setEmail(createStudentDto.getEmail());
        jpaStudentRepository.addStudent(student);
    }

    public boolean validateStudentData(CreateStudentDto studentDto) {
        String emailValidationPattern = "^(.+)@(.+)$";
        boolean emailIsOK = studentDto.getEmail() != null && Pattern.compile(emailValidationPattern)
                .matcher(studentDto.getEmail())
                .matches();
        boolean nameIsOk = studentDto.getName() != null && studentDto.getName().length() < 50;

        return emailIsOK && nameIsOk;
    }


}
