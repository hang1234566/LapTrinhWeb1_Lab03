package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/") 
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 1. HIỂN THỊ DANH SÁCH
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; 
    }

    // 2. XEM CHI TIẾT
    @GetMapping("/student/{id}")
    public String viewStudent(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student-detail"; 
        }
        ra.addFlashAttribute("errorMessage", "Không tìm thấy sinh viên với ID: " + id);
        return "redirect:/students";
    }

    // 3. MỞ FORM THÊM MỚI
    @GetMapping("/student/add")
    public String showAddForm(Model model) {
        if (!model.containsAttribute("student")) {
            model.addAttribute("student", new Student());
        }
        model.addAttribute("pageTitle", "Thêm mới sinh viên");
        return "add-student"; 
    }

    // 4. MỞ FORM CHỈNH SỬA
    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Chỉnh sửa sinh viên");
            return "add-student"; 
        }
        ra.addFlashAttribute("errorMessage", "ID không hợp lệ!");
        return "redirect:/students";
    }

    // 5. XỬ LÝ LƯU (Dùng cho cả thêm mới và cập nhật)
    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute("student") Student student, RedirectAttributes ra) {
        try {
            // Nếu id là 0 thì set về null để DB tự sinh ID mới (106010...)
            if (student.getId() != null && student.getId() == 0) {
                student.setId(null);
            }
            studentService.saveStudent(student);
            ra.addFlashAttribute("message", "Lưu thông tin thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/students";
    }

    // 6. XỬ LÝ XÓA (Đã sửa từ @DeleteMapping thành @GetMapping để chạy được với nút bấm HTML)
    @GetMapping("/student/delete/{id}") 
    public String deleteStudent(@PathVariable("id") Integer id, RedirectAttributes ra) { 
        try {
            studentService.deleteStudent(id);
            ra.addFlashAttribute("message", "Đã xóa sinh viên mã số " + id + " thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Lỗi: Không thể xóa sinh viên này!");
        }
        return "redirect:/students"; 
    }
}