package com.example.demojpa.controller;

import org.json.JSONObject;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
     * http://localhost:8080/app/getTabelaTeste?id=1
     */
    @CrossOrigin
    @RequestMapping(value = "/getTabelaTeste", produces = { "application/json" }, method = RequestMethod.GET, params = {
            "id" })
    public @ResponseBody ResponseEntity<Object> getTabelaTeste(@RequestParam(value = "id") int id) {

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

    /*
     * http://localhost:8080/app/updateTabelaTeste
     * POST
     * {
     * "id": 1,
     * "nome": "Teste 1"
     * }
     */
    @CrossOrigin
    @RequestMapping(value = "/updateTabelaTeste", produces = {
            "application/json" }, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> updateTabelaTeste(@RequestBody TabelaTeste req) {

        try {

            int id = req.getId();
            String nome = req.getNome();

            tabelaTesteRepository.updateTabelaTeste(nome, id);

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("nome", nome);

            return ResponseEntity.ok(json.toString());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO Auto-generated method stub

    }

}
