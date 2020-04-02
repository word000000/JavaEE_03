package org.example.spring.mvc.model;

/**
 * @Author:GQM
 * @Date:created in 23:05 2020/3/7
 * @Description:
 * @Modifyed_By:
 */
public class TeacherHomework {
    private long homeworkId;
    private String homeworkTitle;

    public long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }
}
