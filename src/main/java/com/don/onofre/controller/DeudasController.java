package com.don.onofre.controller;

import com.don.onofre.model.Clientes;
import com.don.onofre.model.Deudas;
import com.don.onofre.repository.DeudasRepository;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author roque
 */
@Controller
@RequestMapping("/api/v1")
public class DeudasController {

    @Autowired
    private DeudasRepository repositoryDeuda;

    @PostMapping("/createDebt")
    public String createDebt(@RequestParam(value = "total", required = false) String total,
            @RequestParam(value = "item", required = false) String item) throws JSONException {

        Deudas debt = new Deudas();
        Clientes cli = new Clientes();
        cli.setId(3);
        cli.setDoc(4012906);
        cli.setEmail("perezsilva@roquegabriel.com");
        cli.setNombre("Roque Perez");
        cli.setPayTime(new Date());

        debt.setCliente(cli);
//        debt.setAdamsDocId(null);
        debt.setPayTime(new Date());
        debt.setId(9999);
    

        LinkedHashMap<String, String> val2 = new LinkedHashMap<>();
        val2.put("currency", "PYG");
        val2.put("value", total);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssxxxx");
        ZonedDateTime zd1 = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime zd2 = zd1.plusDays(1);
        String now = zd1.format(dtf);
        String nowPlus1Day = zd2.format(dtf);
        
        LinkedHashMap<String, String> val3 = new LinkedHashMap<>();
        val3.put("start", now);
        val3.put("end", nowPlus1Day);

        repositoryDeuda.save(debt);

        LinkedHashMap<String, Object> debt1 = new LinkedHashMap<>();
        debt1.put("docId", debt.getId().toString());
        debt1.put("label", "Venta de bebida alcoholica: " + item);
        debt1.put("amount", val2);
        debt1.put("validPeriod", val3);

        JSONObject debtJson = new JSONObject();
        debtJson.put("debt", debt1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", "ap-0ab15593a59d06785a82094d");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(debtJson.toString(), headers);

        String uri = "https://staging.adamspay.com/api/v1/debts";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:https://staging.adamspay.com/pay/don-onofre599/debt/" + debt.getId().toString();

        } else {
            return "error";
        }

    }

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/checkout")
    public String viewCheckout() {
        return "checkout";
    }

}
