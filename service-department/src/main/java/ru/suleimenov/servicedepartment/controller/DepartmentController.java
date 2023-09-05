package ru.suleimenov.servicedepartment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.suleimenov.servicedepartment.model.Department;
import ru.suleimenov.servicedepartment.repo.DepartmentRepository;
import ru.suleimenov.servicedepartment.service.DepartmentProducer;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final DepartmentProducer departmentProducer;

    @GetMapping("/departments/{id}")
    public String department(@PathVariable("id") Long id){
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isEmpty()){
            return String.format("Department with id %s not exist", id);
        }

        System.out.println("department = " + department.get());
        departmentProducer.sendMessage(department.get());
        return "Message successfully sent";
    }
}
