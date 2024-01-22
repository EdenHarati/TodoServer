//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.TodoServerController;

import java.util.ArrayList;
import java.util.List;

import com.Todo.TODO;
import com.Todos.TODOs;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoServerController {
    public static int STATIC_ID_COUNTER = 1;
    public static String ALL_STATUS = "ALL";
    public static String PENDING_STATUS = "PENDING";
    public static String LATE_STATUS = "LATE";
    public static String DONE_STATUS = "DONE";
    public static List<TODO> todosList = new ArrayList();

    public TodoServerController() {
    }

    @GetMapping({"/todo/health"})
    @ResponseBody
    public String health() {
        return "OK";
    }

    @PostMapping({"/todo"})
    @ResponseBody
    public ResponseEntity<String> creatNewTODO(@RequestBody TODO givenTODO) {
        JSONObject response = new JSONObject();
        if (TODOs.checkTitle(givenTODO.getTitle())) {
            response.put("errorMessage", "Error: TODO with the title [" + givenTODO.getTitle() + "] already exists in the system");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response.toString());
        } else if (TODOs.checkTimestamp(givenTODO.getDueDate())) {
            response.put("errorMessage", "Error: Can't create new TODO that its due date is in the past");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response.toString());
        } else {
            TODOs.addNewTODO(givenTODO);
            response.put("result", STATIC_ID_COUNTER);
            ++STATIC_ID_COUNTER;
            return ResponseEntity.ok().body(response.toString());
        }
    }

    @GetMapping({"/todo/size"})
    @ResponseBody
    public ResponseEntity<String> getTodoSize(@RequestParam String status) {
        JSONObject response = new JSONObject();
        if ((status.equals(ALL_STATUS) || status.equals(PENDING_STATUS) || status.equals(DONE_STATUS) || status.equals(LATE_STATUS)) && status != null) {
            int number = TODOs.calculateTOTOsNum(status);
            response.put("result", number);
            return ResponseEntity.ok().body(response.toString());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping({"/todo"})
    @ResponseBody
    public ResponseEntity<String> updateTODOStatus(@RequestParam int id, @RequestParam String status) {
        JSONObject response = new JSONObject();
        if ((status.equals(ALL_STATUS) || status.equals(PENDING_STATUS) || status.equals(DONE_STATUS) || status.equals(LATE_STATUS)) && status != null) {
            int index = TODOs.checkIfTODOExist(id);
            if (index > 0) {
                response.put("result", ((TODO) TODOs.TODOsList.get(index)).getStatus());
                TODOs.updateStatus(id, status);
                return ResponseEntity.ok().body(response.toString());
            } else {
                response.put("errorMessage", "Error: no such TODO with id " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.toString());
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
