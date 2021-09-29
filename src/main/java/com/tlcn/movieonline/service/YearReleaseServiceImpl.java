package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.ReleaseYear;
import com.tlcn.movieonline.repository.YearReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class YearReleaseServiceImpl implements YearReleaseService{

    @Autowired
    YearReleaseRepository YearReleaseRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ReleaseYear addYearRelease(ReleaseYear g) {
        ReleaseYear YearRelease= YearReleaseRepository.save(g);
        return YearRelease;
    }

    @Override
    public List<ReleaseYear> findAll() {
        List<ReleaseYear> lstYearRelease=YearReleaseRepository.findAll();
        return lstYearRelease;
    }

    @Override
    public ReleaseYear getYearReleaseById(Long id) {
        ReleaseYear YearRelease= YearReleaseRepository.getYearReleaseById(id);
        return YearRelease;
    }

    @Override
    public void deleteYearReleaseById(Long id) {
        YearReleaseRepository.deleteById(id);
    }
}
