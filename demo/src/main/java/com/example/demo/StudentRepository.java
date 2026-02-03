package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// Đổi String thành Integer tại đây để khớp với database số
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Hàm tìm kiếm theo tên vẫn giữ nguyên tham số String name
    List<Student> findByNameContainingIgnoreCase(String name);
}