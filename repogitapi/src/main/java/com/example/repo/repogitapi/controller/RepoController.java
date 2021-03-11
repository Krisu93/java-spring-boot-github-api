package com.example.repo.repogitapi.controller;

import com.example.repo.repogitapi.model.RepoGitHub;
import com.example.repo.repogitapi.service.RepoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepoController {

    @Autowired
    private RepoService repoService;


    @GetMapping(value = "/{owner}", produces = "application/json")
    public ResponseEntity<List<RepoGitHub>> getRepositoriesByOwner(@PathVariable("owner") String owner, @RequestParam("sort") String sort) throws JsonProcessingException {

        List<RepoGitHub> resultList;
        if(sort.contains("asc"))
            resultList=   repoService.getRepoListASC(owner);
        else if (sort.contains("desc"))
            resultList= repoService.getRepoListDESC(owner);
        else
            resultList = new ArrayList<>();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
