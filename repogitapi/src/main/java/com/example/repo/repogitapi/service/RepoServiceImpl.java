package com.example.repo.repogitapi.service;

import com.example.repo.repogitapi.component.RepoCreator;
import com.example.repo.repogitapi.model.RepoGitHub;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepoServiceImpl implements RepoService {

    @Autowired
    RepoCreator repoCreator;


    @Override
    public List<RepoGitHub> getRepoListASC(String user) throws JsonProcessingException {

        return repoCreator.getRepoList(user).stream()
                .sorted(Comparator.comparing(RepoGitHub::getStars))
                .collect(Collectors.toList());
    }


    @Override
    public List<RepoGitHub> getRepoListDESC(String user) throws JsonProcessingException {
        return repoCreator.getRepoList(user).stream()
                .sorted(Comparator.comparing(RepoGitHub::getStars).reversed())
                .collect(Collectors.toList());
    }



}
