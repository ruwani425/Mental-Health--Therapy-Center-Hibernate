package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.TherapistsBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.DAOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapistDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.TherapistDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Patient;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Therapist;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistsBOImpl implements TherapistsBO {

    private final TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    private final TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPYPROGRAM);

    @Override
    public boolean saveTherapist(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException {

        Therapist therapist = new Therapist();
        therapist.setTherapistId(therapistDTO.getTherapistId());
        therapist.setTherapistName(therapistDTO.getTherapistName());
        therapist.setEmail(therapistDTO.getEmail());
        therapist.setPhone(therapistDTO.getPhone());
        therapist.setAddress(therapistDTO.getAddress());
        therapist.setDateOfBirth(therapistDTO.getDateOfBirth().toString());
        therapist.setStatus(therapistDTO.getStatus());
        therapist.setProgramID(therapistDTO.getProgramID());

        return therapistDAO.save(therapist);
    }

    @Override
    public List<TherapistDTO> getAllTherapists() throws SQLException, ClassNotFoundException {
        ArrayList<TherapistDTO> therapistDTOArrayList = new ArrayList<>();
        ArrayList<Therapist> therapists = therapistDAO.getAllData();

        for (Therapist therapist : therapists) {
            TherapistDTO therapistDTO = new TherapistDTO();
            therapistDTO.setTherapistName(therapist.getTherapistName());
            therapistDTO.setAddress(therapist.getAddress());
            therapistDTO.setDateOfBirth(Date.valueOf(therapist.getDateOfBirth()));
            therapistDTO.setEmail(therapist.getEmail());
            therapistDTO.setPhone(therapist.getPhone());
            therapistDTO.setTherapistId(Integer.parseInt(String.valueOf(therapist.getTherapistId())));
            therapistDTO.setStatus(therapist.getStatus());
            therapistDTO.setProgramID(therapist.getProgramID());
            therapistDTOArrayList.add(therapistDTO);
        }
        return therapistDTOArrayList;
    }

    @Override
    public boolean DeleteTherapist(String id) throws SQLException, ClassNotFoundException {
        Therapist therapist = new Therapist();
        therapist.setTherapistId(Integer.parseInt(id));
        return therapistDAO.delete(therapist);
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException {
        Therapist therapist = new Therapist();
        therapist.setTherapistId(therapistDTO.getTherapistId());
        therapist.setTherapistName(therapistDTO.getTherapistName());
        therapist.setEmail(therapistDTO.getEmail());
        therapist.setPhone(therapistDTO.getPhone());
        therapist.setAddress(therapistDTO.getAddress());
        therapist.setDateOfBirth(therapistDTO.getDateOfBirth().toString());
        therapist.setStatus(therapistDTO.getStatus());
        therapist.setProgramID(therapistDTO.getProgramID());

        return therapistDAO.update(therapist);
    }

    @Override
    public List<Integer> getAllTherapyProgramIds() {
        return therapyProgramDAO.getAllIds();
    }
}
