package med.voll.api.person.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){
        List<Doctor> listDoctors = doctorRepository.findAll();
        return listDoctors;
    }
}
