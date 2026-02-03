package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // Integer (I viết hoa) giúp ID nhận giá trị null khi thêm mới
    private Integer id; 

    @Column(name = "name", columnDefinition = "NVARCHAR(255)") 
    private String name;

    // Chỉ định rõ kiểu dữ liệu INT trong SQL để tránh lỗi 500
    @Column(name = "age")
    private Integer age; 
    
    @Column(name = "email", length = 255)
    private String email;
    
    // NVARCHAR giúp lưu tiếng Việt "Nam/Nữ" không bị lỗi font
    @Column(name = "gender", columnDefinition = "NVARCHAR(10)")
    private String gender; 

    // Constructor mặc định (bắt buộc)
    public Student() {
    }

    // --- Getter và Setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}