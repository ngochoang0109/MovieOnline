package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.YearRelease;
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
    @Transactional
    public YearRelease addYearRelease(YearRelease g) {
        YearRelease YearRelease= YearReleaseRepository.save(g);
        return YearRelease;
    }

    @Override
    public List<YearRelease> findAll() {
        List<YearRelease> lstYearRelease=YearReleaseRepository.findAll();
        return lstYearRelease;
    }

    @Override
    public YearRelease getYearReleaseById(Long id) {
        YearRelease YearRelease= YearReleaseRepository.getYearReleaseById(id);
        return YearRelease;
    }

    @Override
    public void deleteYearReleaseById(Long id) {
        YearReleaseRepository.deleteById(id);
    }
}
