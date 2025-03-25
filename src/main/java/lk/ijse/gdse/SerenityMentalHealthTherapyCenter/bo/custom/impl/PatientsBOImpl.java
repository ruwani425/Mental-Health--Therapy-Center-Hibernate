package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.PatientsBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.DAOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.PatientsDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Patient;

import java.sql.SQLException;

public class PatientsBOImpl implements PatientsBO {

    private final PatientsDAO patientsDAO = (PatientsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);

    @Override
    public boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAddress(patientDTO.getAddress());
        patient.setGender(patientDTO.getGender());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setEmail(patientDTO.getEmail());
        patient.setPhoneNumber(String.valueOf(patientDTO.getPhoneNumber()));
        return patientsDAO.save(patient);
    }
}
