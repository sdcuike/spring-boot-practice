package com.sdcuike.practice.mapper;

import com.sdcuike.practice.domain.Student;
import com.sdcuike.practice.domain.StudentWithBLOBs;

public interface StudentMapper {
    int deleteByPrimaryKey(Long studId);

    int insert(StudentWithBLOBs record);

    int insertSelective(StudentWithBLOBs record);

    StudentWithBLOBs selectByPrimaryKey(Long studId);

    int updateByPrimaryKeySelective(StudentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(StudentWithBLOBs record);

    int updateByPrimaryKey(Student record);
}