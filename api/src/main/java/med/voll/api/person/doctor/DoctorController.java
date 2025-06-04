package med.voll.api.person.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("")
    public List<Doctor> getAll(){
        return doctorService.getAllDoctors();
    }
}
