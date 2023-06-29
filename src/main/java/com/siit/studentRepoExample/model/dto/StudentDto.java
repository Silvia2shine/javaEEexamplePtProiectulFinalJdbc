package com.siit.studentRepoExample.model.dto;

import com.siit.studentRepoExample.model.Student;
import lombok.Data;

@Data
public class StudentDto {
    private int id;
    private String name;
    private String email;
    public static StudentDto toDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }

}
