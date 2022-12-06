package com.example.demojpa.controller;

import org.json.JSONObject;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demojpa.beans.TabelaTeste;
import com.example.demojpa.repository.TabelaTesteRepository;

@RestController()
@RequestMapping("app")
public class AppController implements ApplicationRunner {

    private TabelaTesteRepository tabelaTesteRepository;

    public AppController(TabelaTesteRepository tabelaTesteRepository) {
        super();
        this.tabelaTesteRepository = tabelaTesteRepository;
    }

    @CrossOrigin
    @RequestMapping(value = "/about", produces = { "application/json" }, method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> about() {

        try {
            JSONObject json = new JSONObject();
            json.put("version", "v1.0.0");
            return ResponseEntity.ok(json.toString());
        } catch (Exception e) {
            return null;
        }

    }

    /*
     * http://localhost:8080/app/testejpa?id=1
     */
    @CrossOrigin
    @RequestMapping(value = "/testejpa", produces = { "application/json" }, method = RequestMethod.GET, params = {
            "id" })
    public @ResponseBody ResponseEntity<Object> testejpa(@RequestParam(value = "id") int id) {

        try {
            JSONObject json = new JSONObject();
            TabelaTeste tabelaTeste = tabelaTesteRepository.findById(id);
            json.put("id", tabelaTeste.getId());
            json.put("nome", tabelaTeste.getNome());
            return ResponseEntity.ok(json.toString());
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO Auto-generated method stub

    }

}
