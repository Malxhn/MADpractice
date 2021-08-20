package com.aim.sliitquizapp.model;

public class desc {

    private String question;
    private String answer;


    public desc(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public desc() {

    }

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
