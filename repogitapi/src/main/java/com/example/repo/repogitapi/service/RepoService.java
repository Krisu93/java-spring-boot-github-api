package com.example.repo.repogitapi.service;

import com.example.repo.repogitapi.model.RepoGitHub;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface RepoService {
    List<RepoGitHub> getRepoListASC(String user) throws JsonProcessingException;
    List<RepoGitHub> getRepoListDESC(String user) throws JsonProcessingException;
}
