//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.Todo;

import com.TodoServerController.TodoServerController;

public class TODO {
    public int id;
    private String title;
    private String content;
    private long dueDate;
    private String status;

    public TODO(String title, String content, long dueDate) {
        this.id = TodoServerController.STATIC_ID_COUNTER;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.status = TodoServerController.PENDING_STATUS;
    }

    public TODO() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
