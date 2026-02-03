package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // Lấy toàn bộ danh sách sinh viên từ SQL Server
    public List<Student> getAllStudents() {
        return repository.findAll(); 
    }

    // Tìm sinh viên theo ID số (Integer) - Khớp với 106010, 106423...
    public Student getStudentById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Xóa sinh viên theo ID số
    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }

    // Lưu sinh viên (Thêm mới hoặc Cập nhật)
    public void saveStudent(Student student) {
        // Hibernate sẽ tự động kiểm tra: 
        // Nếu student.getId() == null -> INSERT mới (SQL tự tăng ID)
        // Nếu student.getId() != null -> UPDATE bản ghi cũ
        repository.save(student);
    }

    // Tìm kiếm theo tên (Không phân biệt hoa thường)
    public List<Student> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}