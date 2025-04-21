package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.TherapyProgramBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.DAOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgramBOImpl implements TherapyProgramBO {

    private final TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPYPROGRAM);

    @Override
    public boolean saveProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException, PatientPersistException {
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setProgramName(therapyProgramDTO.getProgramName());
        therapyProgram.setProgramFee(therapyProgramDTO.getProgramFee());
        therapyProgram.setDuration(therapyProgramDTO.getDuration());
        return therapyProgramDAO.save(therapyProgram);
    }

    @Override
    public List<TherapyProgramDTO> getAllPrograms() throws SQLException, ClassNotFoundException {
        List<TherapyProgram> therapyPrograms = therapyProgramDAO.getAllData();
        List<TherapyProgramDTO> programDTOs = new ArrayList<>();
        for (TherapyProgram program : therapyPrograms) {
            programDTOs.add(new TherapyProgramDTO(
                    program.getProgramId(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getProgramFee()
            ));
        }
        return programDTOs;
    }

    @Override
    public boolean deleteTherapyProgram(String id) throws SQLException, ClassNotFoundException {
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setProgramId(Integer.parseInt(id));
        return therapyProgramDAO.delete(therapyProgram);
    }

    @Override
    public boolean updateProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException {
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setProgramId(therapyProgramDTO.getProgramId());
        therapyProgram.setProgramName(therapyProgramDTO.getProgramName());
        therapyProgram.setProgramFee(therapyProgramDTO.getProgramFee());
        therapyProgram.setDuration(therapyProgramDTO.getDuration());
        return therapyProgramDAO.update(therapyProgram);
    }
}
