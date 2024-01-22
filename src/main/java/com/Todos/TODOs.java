//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.Todos;

import com.Todo.TODO;
import com.TodoServerController.TodoServerController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TODOs {
    public static final List<TODO> TODOsList = new ArrayList();

    public TODOs() {
    }

    public static void addNewTODO(TODO newTodo) {
        newTodo.setStatus("PENDING");
        TODOsList.add(newTodo);
    }

    public static boolean checkTitle(String TODOtitle) {
        return TODOsList.stream().anyMatch((todo) -> {
            return todo.getTitle().equals(TODOtitle);
        });
    }

    public static int checkIfTODOExist(int id) {
        int result = -1;

        for(int i = 0; i < TODOsList.size(); ++i) {
            if (((TODO)TODOsList.get(i)).getId() == id) {
                result = i;
            }
        }

        return result;
    }

    public static void updateStatus(int id, String status) {

        for(int i = 0; i < TODOsList.size(); ++i) {
            if (((TODO)TODOsList.get(i)).getId() == id) {
                ((TODO)TODOsList.get(i)).setStatus(status);
            }
        }

    }

    public static int calculateTOTOsNum(String status) {
        int result = 0;
        //int i = false;
        if (status.equals(TodoServerController.ALL_STATUS)) {
            result = TODOsList.size();
        } else {
            for(int i = 0; i < TODOsList.size(); ++i) {
                if (((TODO)TODOsList.get(i)).getStatus().equals(status)) {
                    ++result;
                }
            }
        }

        return result;
    }

    public static boolean checkTimestamp(long TODOtimestamp) {
        Date date = new Date(TODOtimestamp);
        Date today = new Date();
        return date.before(today);
    }
}
