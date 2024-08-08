package com.rafaelswr.SprindDataJPA.Service;

import com.rafaelswr.SprindDataJPA.Model.School.School;
import com.rafaelswr.SprindDataJPA.Model.School.SchoolRecordDTO;
import com.rafaelswr.SprindDataJPA.Repository.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }


    public List<SchoolRecordDTO> getAllSchools() {
        return schoolRepository.findAll().stream()
                .map(this::toSchoolDTO)
                .toList();
    }

    public ResponseEntity<String> saveNewSchool(SchoolRecordDTO schoolDTO) {
        School s = toSchoolObject(schoolDTO);
        try {
            schoolRepository.save(s);
            log.info("School players we have to win.");
            return new ResponseEntity<>("Saved", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return  new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private SchoolRecordDTO toSchoolDTO(School school) {
        return new SchoolRecordDTO(school.getName());
    }

    private School toSchoolObject(SchoolRecordDTO schoolDTO) {
        return new School(schoolDTO.name());
    }
}
