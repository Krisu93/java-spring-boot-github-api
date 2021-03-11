package com.example.repo.repogitapi.component;

import com.example.repo.repogitapi.model.RepoGitHub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RepoCreator {


    public List<RepoGitHub> getRepoList(String user) throws JsonProcessingException {

        String json = this.getDataFromUrl(user);

        return this.createRepoList(json);
    }


    private String getDataFromUrl(String user){
        String json;
        try {
            URL url = new URL("https://api.github.com/users/"+user+"/repos");
            try {
                URLConnection connection = url.openConnection();
                connection.addRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 5.1)");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                json = in.readLine();
                in.close();
            } catch (IOException ioEx) {
                System.out.println(ioEx);
                return "[]";
            }
        } catch (MalformedURLException malEx) {
            System.out.println(malEx);
            return "[]";
        }
        return json;
    }

    private  List<RepoGitHub> createRepoList(String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(json);

        List<RepoGitHub> repoList = new ArrayList<>();
        for (Iterator<JsonNode> it = rootNode.elements(); it.hasNext(); ) {
            Object o = it.next();
            JSONObject object = (JSONObject) JSONValue.parse(o.toString());

            repoList.add(createRepo(object));
        }


        return repoList;
    }

    private RepoGitHub createRepo(JSONObject object){

        try{
            String fullname = String.valueOf(object.get("full_name"));
            String description = String.valueOf(object.get("description"));
            String clone_url = String.valueOf(object.get("clone_url"));
            Integer stars = (Integer) object.get("stargazers_count");
            LocalDateTime created_at = LocalDateTime.parse(String.valueOf(object.get("created_at")).replace("Z",""));

            return  new RepoGitHub(fullname, description, clone_url, stars, created_at);
        } catch (Exception e){
            return new RepoGitHub();
        }

    }
}
